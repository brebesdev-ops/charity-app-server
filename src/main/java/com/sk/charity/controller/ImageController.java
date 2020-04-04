package com.sk.charity.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@PostMapping("/image/campaign/{campaignId}")
	public Response uploadImage(@PathVariable int campaignId, @RequestParam MultipartFile file) throws IOException {
		storageService.store(file);
		
		res.setStatus(200);
		res.setMessage("File uploaded successfully");
		return res;
	}
}
