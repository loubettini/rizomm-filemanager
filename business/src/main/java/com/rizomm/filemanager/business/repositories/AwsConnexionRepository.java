package com.rizomm.filemanager.business.repositories;

import com.rizomm.filemanager.business.entities.ConnexionImpl.AwsConnexion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AwsConnexionRepository extends JpaRepository<AwsConnexion, Long> {
}
