package com.rizomm.filemanager.business.repositories;

import com.rizomm.filemanager.business.entities.ConnexionImpl.SftpConnexion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SftpConnexionRepository extends JpaRepository<SftpConnexion,Long> {
}
