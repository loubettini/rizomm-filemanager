package com.rizomm.filemanager.business.services;

import com.rizomm.filemanager.business.entities.ConnexionImpl.SftpConnexion;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface SftpConnexionService {
    SftpConnexion create(SftpConnexion sftpConnexion);

    List<SftpConnexion> findAll();

    Optional<SftpConnexion> findByid(long id);

    void delete(SftpConnexion sftpConnexion);
}
