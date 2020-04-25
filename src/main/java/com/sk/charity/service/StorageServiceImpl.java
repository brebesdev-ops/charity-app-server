package com.sk.charity.service;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("storageService")
public class StorageServiceImpl implements StorageService {

	private final String base_dir = Paths.get("").toAbsolutePath().toString();
	private final String campaign_dir = "/src/main/resources/static/images/campaign/";
	
	@Override
	public void store(MultipartFile file, int campaignId, Boolean isLogo) throws IOException {
		
		if (file.isEmpty()) {
			throw new RuntimeException("Please provide a file to upload.");
		}
		
		//Create folder for each campaignId if not exists
		Path path = Paths.get(base_dir + campaign_dir + campaignId);
		
		if (!Files.exists(path)) {
			Files.createDirectory(path);
		}
		
		try {
			byte[] bytes = file.getBytes();
			
			Path imgPath;
			
			if ( isLogo ) {
				imgPath = Paths.get(path + "/logo" );
			}else {
				imgPath = Paths.get(path + "/" + file.getOriginalFilename());
			}
			
			Files.write(imgPath, bytes);
		} catch( IOException e) {
			throw new IOException("Failed to upload file " + file.getOriginalFilename());
		}
	}

	@Override
	public byte[] loadFileByCampaignIdAndName(int campaignId, String fileName) throws IOException {

		Path path = Paths.get(base_dir + campaign_dir + campaignId + "/" + fileName);

		try {
			return Files.readAllBytes(path);
		} catch (IOException e) {
			throw new IOException("Failed to load file " + fileName);
		}
	}

	@Override
	public List<byte[]> loadAllFilesByCampaignId(int campaignId) throws IOException {
		//check if campaignId folder exists
		//check if folder has files
		List<byte[]> fileList = new ArrayList<byte[]>();
		
		Path path = Paths.get(base_dir + campaign_dir + campaignId );
		try {
			Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
				
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					if (!Files.isDirectory(file)) {
						fileList.add(Files.readAllBytes(file.toFile().toPath()));
					}
					return FileVisitResult.CONTINUE;
				}
			});				
		} catch (IOException e) {
			throw new IOException("Failed to load files.");
		}
		
		return fileList;	
	}
}
