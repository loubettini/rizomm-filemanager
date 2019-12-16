package com.rizomm.filemanager.business.services.impl;

import com.rizomm.filemanager.business.entities.ConnexionImpl.AwsConnexion;
import com.rizomm.filemanager.business.repositories.AwsConnexionRepository;
import com.rizomm.filemanager.business.repositories.UserRepository;
import com.rizomm.filemanager.business.services.AwsConnexionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public class AwsConnexionServiceImpl implements AwsConnexionService {
    @Autowired
    private AwsConnexionRepository awsConnexionRepository;

    @Override
    public AwsConnexion create(AwsConnexion awsConnexion) {
        return awsConnexionRepository.save(awsConnexion);
    }

    @Override
    public List<AwsConnexion> findAll() {
        return awsConnexionRepository.findAll();
    }

    @Override
    public Optional<AwsConnexion> findByid(long id) {
        return awsConnexionRepository.findById(id);
    }

    @Override
    public void delete(AwsConnexion awsConnexion) {
        awsConnexionRepository.delete(awsConnexion);
    }
}
