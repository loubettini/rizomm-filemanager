package com.rizomm.filemanager.controllers.UploadFileController;

import com.rizomm.filemanager.business.Utils.FileUtils;
import com.rizomm.filemanager.business.entities.ConnexionImpl.SftpConnexion;
import com.rizomm.filemanager.business.services.SftpConnexionService;
import com.rizomm.filemanager.business.services.impl.SftpUploadServiceImpl;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.Optional;

import static com.rizomm.filemanager.business.Utils.FileUtils.convertMultiPartToFile;

@RestController
@RequestMapping("/connections/sftp/{connectionId}")
public class SftpUploadFileController {

        @Autowired
        private SftpUploadServiceImpl sftpUploadService;
        @Autowired
        private SftpConnexionService sftpConnexionService;

    @PostMapping("/uploadFile")
    public ResponseEntity<String> uploadFile(@RequestPart(value = "file") MultipartFile file, @PathVariable long connectionId, Principal principal) {
        Optional<SftpConnexion> ftpConnection = sftpConnexionService.findByid(connectionId);
        String server = ftpConnection.get().getHost();
        int port = ftpConnection.get().getPort();
        String user = ftpConnection.get().getUsername();
        String pass = ftpConnection.get().getPassword();

        FTPClient ftpClient = new FTPClient();
        try {

            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            File fileToUpload = FileUtils.convertMultiPartToFile(file);
            InputStream inputStream = new FileInputStream(fileToUpload);
            String firstRemoteFile = file.getName();
            System.out.println("Start uploading first file");
            boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
            inputStream.close();
            if (done) {
                System.out.println("The first file is uploaded successfully.");
            }
            } catch (IOException e) {
                e.printStackTrace();
            }
        return ResponseEntity.notFound().build();

        }
    }




