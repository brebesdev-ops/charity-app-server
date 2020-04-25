package com.sk.charity.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
	
	void store(MultipartFile file, int campaignId, Boolean isLogo) throws IOException;
	
	byte[] loadFileByCampaignIdAndName(int campaignId, String fileName) throws IOException;
	
	List<byte[]> loadAllFilesByCampaignId(int campaignId) throws IOException;

	
	//void delete(int campaignId, String fileName);
}
