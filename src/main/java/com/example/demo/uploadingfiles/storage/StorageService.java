package com.example.demo.uploadingfiles.storage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


@Service
public class StorageService {
    String basicPath = "./media";
    public void init(){

    }

    public void store(MultipartFile file) throws IOException{
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if(fileName.contains("..")) {
                throw new RuntimeException("Filename contains invalid path sequence: " + fileName);
            }
            Path targetLoaction = Paths.get(basicPath).resolve(fileName);

            Files.copy(file.getInputStream(), targetLoaction);
        } catch (IOException e){
            throw new IOException("Could not store file " + fileName, e);
        }
    }

    public Stream<Path> loadAll() throws IOException{ 
        Path dirPath = Paths.get(basicPath);
        return Files.list(dirPath);
    }

    public Resource loadAsResource(String filename){
        Resource resource = null;
        try{
            String filePath = basicPath + File.separator + filename;
            if (new File(filePath).exists()) {
                resource = new FileSystemResource(filePath);
            } else {
                throw new FileNotFoundException("File not found: " + filename);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return resource;
    }

    public void deleteAll(){

    }
}