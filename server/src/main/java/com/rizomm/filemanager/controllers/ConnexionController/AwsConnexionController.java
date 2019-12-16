package com.rizomm.filemanager.controllers.ConnexionController;

import com.rizomm.filemanager.business.entities.ConnexionImpl.AwsConnexion;
import com.rizomm.filemanager.business.services.AwsConnexionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/connections/aws")
public class AwsConnexionController {

    @Autowired
    private AwsConnexionService awsConnectionService;

    @GetMapping
    public ResponseEntity<List<AwsConnexion>> getAllAwsConnections() {
        List<AwsConnexion> awsConnexionList = awsConnectionService.findAll();
        return ResponseEntity.ok(awsConnexionList);
    }

    @PostMapping
    public ResponseEntity<AwsConnexion> createConnectionAws(@RequestBody @Validated AwsConnexion awsConnexion, Principal principal) {
        awsConnexion.setUserEmail(principal.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(awsConnectionService.create(awsConnexion));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteConnection(@PathVariable long id, Principal principal) {
        Optional<AwsConnexion> connectionToDelete = this.awsConnectionService.findByid(id);
        if (connectionToDelete.isPresent()) {
            if (!connectionToDelete.get().getUserEmail().equals(principal.getName())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            this.awsConnectionService.delete(connectionToDelete.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
