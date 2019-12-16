package com.rizomm.filemanager.controllers.ConnexionController;


import com.rizomm.filemanager.business.entities.ConnexionImpl.SftpConnexion;
import com.rizomm.filemanager.business.services.SftpConnexionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/connections/sftp")
public class SftpConnexionController {

    @Autowired
    private SftpConnexionService sftpConnexionService;

    @PostMapping
    public ResponseEntity<SftpConnexion> createConnectionSftp(@RequestBody @Validated SftpConnexion sftpConnexion, Principal principal) {
        sftpConnexion.setUserEmail(principal.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(this.sftpConnexionService.create(sftpConnexion));

    }

    @GetMapping
    public ResponseEntity<List<SftpConnexion>> getAllSftpConnections() {
        List<SftpConnexion> result = sftpConnexionService.findAll();
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteConnection(@PathVariable long id, Principal principal) {
        Optional<SftpConnexion> connectionToDelete = this.sftpConnexionService.findByid(id);
        if (connectionToDelete.isPresent()) {
            if (!connectionToDelete.get().getUserEmail().equals(principal.getName())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            this.sftpConnexionService.delete(connectionToDelete.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
