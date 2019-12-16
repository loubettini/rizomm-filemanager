package com.rizomm.filemanager.business.services;

import com.rizomm.filemanager.business.entities.ConnexionImpl.AwsConnexion;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AwsConnexionService {

    AwsConnexion create(AwsConnexion awsConnexion);
    List<AwsConnexion> findAll();
    Optional<AwsConnexion> findByid(long id);
    void delete(AwsConnexion awsConnexion);
}
