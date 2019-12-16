package com.rizomm.filemanager.business.entities.ConnexionImpl;


import com.rizomm.filemanager.business.entities.Connexion;
import lombok.Data;

import javax.persistence.Entity;


@Entity
@Data
public class SftpConnexion extends Connexion
{
    private int port = 21;
    private String directory = "";
    private String username;
    private String password;

}

