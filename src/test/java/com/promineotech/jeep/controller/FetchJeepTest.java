package com.promineotech.jeep.controller;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;
import lombok.Getter;

//We are saying we want the tests run in a web environment. To make sure tests are not
//run on top of each other, we specify the .RANDOM_PORT for each class
//Local host means it will stay local to the machine

@SpringBootTest (webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles ("test")
@Sql(scripts = {
    "classpath:flyway/migrations/V1.0_Jeep_Schema.sql", 
    "classpath:flyway/migrations/V1.1_Jeep_Data.sql"}, 
    config = @SqlConfig(encoding = "utf-8")
)


class FetchJeepTest {

  @LocalServerPort
  private int serverPort;
  
  //TestRestTemplate sends the HTTP requests
  @Autowired
  @Getter
  private TestRestTemplate restTemplate;
    
  @Test
  void testThatJeepsAreReturnedWhenAValidModelAndTrimAreSupplied() {
    //Given a valid model, trim pass it to the URI
    JeepModel model = JeepModel.WRANGLER;
    String trim = "Sport";
    String uri = String.format("http://localhost:%d/jeeps?model=%s&trim=%s", 
        serverPort, model, trim);
   
    //When a connection is made to the URI
    ResponseEntity<List<Jeep>> response = restTemplate.exchange(uri,  
        HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
    
    //Then a success (200) status code is returned
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }
}
