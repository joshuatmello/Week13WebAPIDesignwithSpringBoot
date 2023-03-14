package com.promineotech.jeep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Tomcat is how it runs and it handles all the requests
//Snapshot is a maven convention 
//Initiates the Spring Boot application
@SpringBootApplication
public class JeepSales {

  //This is my main method and what I run my Spring Boot App from
  
  public static void main(String[] args) {
    SpringApplication.run(JeepSales.class, args);
  }

}
