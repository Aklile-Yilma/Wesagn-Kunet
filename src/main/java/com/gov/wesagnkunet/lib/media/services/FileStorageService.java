package com.gov.wesagnkunet.lib.media.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.gov.wesagnkunet.lib.media.exceptions.StorageException;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FileStorageService {
	
	private final Path rootLocation = Paths.get("media");

	public FileStorageService(){
		init();
	}

	public String store(MultipartFile file){
		
		try {
		
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file.");
			}
		
			String fileName = generateFileName(file);
			Path destinationFile = rootLocation.resolve(fileName);
		
			if (!destinationFile.getParent().toAbsolutePath().equals(this.rootLocation.toAbsolutePath())) 
				throw new StorageException("Cannot store file outside current directory.");

			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, destinationFile,
					StandardCopyOption.REPLACE_EXISTING);
			}
		
			return fileName;
		}
		
		catch (IOException e) {
			throw new StorageException("Failed to store file.");
		}

	}

	private String generateFileName(MultipartFile file){
		return String.format("%d_%s", System.currentTimeMillis(), file.getOriginalFilename());
	}

	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	public Resource loadAsResource(String filename) {
		try {
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			}
			else {
				throw new StorageException("Could not read file: " + filename);

			}
		}
		catch (MalformedURLException e) {
			throw new StorageException("Could not read file: " + filename);
		}
	}

	public String getUrl(String fileName){
		return String.format("/%s/%s", rootLocation.getFileName(), fileName);
	}

	public void init() {
		try{
			if(Files.notExists(rootLocation))
				Files.createDirectories(rootLocation);
		}
		catch (IOException e) {
			throw new StorageException("Could not initialize storage");
		}
	}

}
