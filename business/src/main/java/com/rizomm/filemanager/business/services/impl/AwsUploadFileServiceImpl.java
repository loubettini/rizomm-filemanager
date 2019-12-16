package com.rizomm.filemanager.business.services.impl;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.iot.model.CannedAccessControlList;
import com.amazonaws.services.mediastoredata.model.PutObjectRequest;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.rizomm.filemanager.business.Utils.FileUtils;
import com.rizomm.filemanager.business.entities.ConnexionImpl.AwsConnexion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@Service
public class AwsUploadFileServiceImpl  {

    public AwsConnexion connection;
    public AmazonS3 awsClient;


    public void initConnexion() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(this.connection.getAccessKey(), connection.getSecretKey());
        AwsClientBuilder.EndpointConfiguration endpointConfiguration = new AwsClientBuilder.EndpointConfiguration(connection.getHost(), "");
        this.awsClient = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(endpointConfiguration)
                .withPathStyleAccessEnabled(true)
                .withCredentials(new AWSStaticCredentialsProvider(credentials)).build();

    }


    public File AwsUploadFile(String fileName, MultipartFile file) throws IOException {
        File fileToUpload = FileUtils.convertMultiPartToFile(file);
        try {
            awsClient.putObject(
                    this.connection.getBucketname(),
                    fileName,fileToUpload
            );
            return fileToUpload;
        } catch (AmazonServiceException ase) {
            throw new AmazonServiceException("Amazon Error :",ase);
        } catch (AmazonClientException ace) {
            throw new AmazonServiceException("Amazon Error",ace);

        }

    }




    public String deleteFile(String fileName) {
        awsClient.deleteObject(this.connection.getBucketname()+"/",fileName);
        return "delete Success ";
    }
}
