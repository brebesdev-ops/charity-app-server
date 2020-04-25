package com.sk.charity.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sk.charity.service.StorageService;
import com.sk.charity.util.Response;

@RestController
public class ImageController {
	@Autowired
	private StorageService storageService;
	
	@Autowired
	private Response res;
	
	@PostMapping("/campaign/{campaignId}/logo")
	public Response uploadLogo(@PathVariable int campaignId, 
			@RequestParam MultipartFile file) throws IOException {

			storageService.store(file, campaignId, true);
		
		res.setStatus(200);
		res.setMessage("File uploaded successfully");
		return res;
	}
	
	@PostMapping("/campaign/{campaignId}/image/{logo}")
	public Response uploadImages(@PathVariable int campaignId, 
			@PathVariable(required = false) Boolean logo,
			@RequestParam List<MultipartFile> files) throws IOException {
		for (MultipartFile file: files) {
			System.out.println("Uploading");

			storageService.store(file, campaignId, logo);
		}
		
		res.setStatus(200);
		res.setMessage("File uploaded successfully");
		return res;
	}
	
	//Returns a single image in bytes
	@GetMapping("/campaign/{campaignId}/image/{imageName}")
	public byte[] getImage(@PathVariable int campaignId, @PathVariable String imageName) throws IOException {
		return storageService.loadFileByCampaignIdAndName(campaignId, imageName);	
	}
	
	@GetMapping("/campaign/{campaignId}/images")
	public List<byte[]> allImagesByCampaign(@PathVariable int campaignId) throws IOException{
		return storageService.loadAllFilesByCampaignId(campaignId);
	}
}
