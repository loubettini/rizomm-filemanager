package com.rizomm.filemanager.controllers.UploadFileController;

import com.rizomm.filemanager.business.entities.ConnexionImpl.AwsConnexion;
import com.rizomm.filemanager.business.services.AwsConnexionService;
import com.rizomm.filemanager.business.services.impl.AwsUploadFileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/connections/aws/{connectionId}")
public class AwsUploadFileController {
    @Autowired
    private AwsConnexionService awsConnexionService;

    @Autowired
    private AwsUploadFileServiceImpl awsUploadFileService;

    @PostMapping("/file")
    public ResponseEntity uploadFile(@RequestPart(value = "file") MultipartFile file, @PathVariable long connectionId, Principal principal) throws IOException {
        Optional<AwsConnexion> awsConnexion = awsConnexionService.findByid(connectionId);
        if (awsConnexion.isPresent()) {
            this.awsUploadFileService.connection = awsConnexion.get();
            if (!this.awsUploadFileService.connection.getUserEmail().equals(principal.getName())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            this.awsUploadFileService.initConnexion();
            return ResponseEntity.status(HttpStatus.CREATED).body(this.awsUploadFileService.AwsUploadFile(file.getName(),file));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteFile")
    public ResponseEntity deleteFile(@PathVariable long connectionId, @RequestBody String fileName, Principal principal) {
        Optional<AwsConnexion> awsConnexion = awsConnexionService.findByid(connectionId);

        if (awsConnexion.isPresent()) {
            this.awsUploadFileService.connection = awsConnexion.get();
            if (!this.awsUploadFileService.connection.getUserEmail().equals(principal.getName())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            this.awsUploadFileService.initConnexion();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(this.awsUploadFileService.deleteFile(fileName));
        }
        return ResponseEntity.notFound().build();
    }
}
