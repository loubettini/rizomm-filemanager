package com.rizomm.filemanager.business.services.impl;

import com.rizomm.filemanager.business.entities.ConnexionImpl.AwsConnexion;
import com.rizomm.filemanager.business.entities.ConnexionImpl.SftpConnexion;
import com.rizomm.filemanager.business.repositories.SftpConnexionRepository;
import com.rizomm.filemanager.business.services.SftpConnexionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class SftpConnexionServiceImpl implements SftpConnexionService {

    @Autowired
    private SftpConnexionRepository sftpConnexionRepository;

    @Override
    public SftpConnexion create(SftpConnexion sftpConnexion) {
        return sftpConnexionRepository.save(sftpConnexion);
    }

    @Override
    public List<SftpConnexion> findAll() {
        return sftpConnexionRepository.findAll();
    }

    @Override
    public Optional<SftpConnexion> findByid(long id) {
        return Optional.empty();
    }

    @Override
    public void delete(SftpConnexion sftpConnexion) {
    }
}
