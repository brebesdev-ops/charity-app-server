package com.sk.charity.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("storageService")
public class StorageServiceImpl implements StorageService {

	@Override
	public void store(MultipartFile file) throws IOException {
		byte[] bytes = file.getBytes();
		//Resource imgFile = new ClassPathResource("images/");
		//System.out.println(Paths.get(imgFile.getURI()));
		Path path = Paths.get("/charity/src/main/resources/static/images/" + file.getOriginalFilename());
		System.out.println(path);
		//Files.write(path, bytes);
	}

}
