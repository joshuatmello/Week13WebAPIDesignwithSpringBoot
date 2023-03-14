package com.promineotech.jeep.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

//This is an INTERFACE, not a class, and is implemented by DefaultJeepSalesController

//The Request Mapping allows anything /jeeps in the uri port number to be mapped 
//to this class; the title will show on the swagger API as the heading 

@RequestMapping("/jeeps")
@OpenAPIDefinition(info= @Info(title= "Jeep Sales Service"), servers = {
    @Server(url= "http://localhost:8080", description = "Local server.")})

public interface JeepSalesController {

  //@formatter:off
  @Operation(
      summary = "Returns a list of Jeeps", 
      description = "Returns list of Jeeps given an optional model and/or trim",
      responses = { 
          @ApiResponse(responseCode = "200", //success
              description = "Success! A list of Jeeps is returned", 
              content = @Content(mediaType = "application/json", 
              schema = @Schema(implementation = Jeep.class))), 
          @ApiResponse(responseCode = "400", //bad or illegal input
              description = "Request parameters are invalid", 
              content = @Content(mediaType = "application/json")), 
          @ApiResponse(responseCode = "404", //not found error
              description = "No Jeeps were found with input criteria", 
              content = @Content(mediaType = "application/json")), 
          @ApiResponse(responseCode = "500", //unplanned exception
              description = "An unplanned error occurred.", 
              content = @Content(mediaType = "application/json"))
      }, 
      parameters = {
        //entering a bunch of characters in results in a null 
          @Parameter(name = "model", allowEmptyValue = false, required = false, 
                 description = "The model name (i.e. 'WRANGLER')"), 
         @Parameter(name = "trim", allowEmptyValue = false, required = false,
                 description = "The trim level (i.e. 'Sport')")
      }
   )
  
 //Spring will map the get request above of /jeeps to the fetchJeeps method below
  @GetMapping
  //then it returns the status update 
  @ResponseStatus(code = HttpStatus.OK)
  //Spring needs annotation to read, it will map the param model based on name of
  //of the param within the method call
  List<Jeep> fetchJeeps(
      @RequestParam (required = false) JeepModel model, 
      @RequestParam (required = false) String trim);
  
  
 //@formatter:on
  
 //connect to: http://localhost:8080/swagger-ui.html 
}
