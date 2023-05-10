package com.example.demo.uploadingfiles.storage;

public class StorageFileNotFoundException extends RuntimeException {
    StorageFileNotFoundException() {

    }

    StorageFileNotFoundException(String message) {
        super(message);
        
    }
}
