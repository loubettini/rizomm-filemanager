package com.rizomm.filemanager.business.entities.ConnexionImpl;

import com.rizomm.filemanager.business.entities.Connexion;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class AwsConnexion extends Connexion {
    @NotNull
    private String accessKey;
    @NotNull
    private String secretKey;
    @NotNull
    private String bucketname;


}