package com.rizomm.filemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.rizomm.filemanager.business")
public class FilemanagerApplication {

  public static void main(String[] args) {
    SpringApplication.run(FilemanagerApplication.class, args);
  }

}
