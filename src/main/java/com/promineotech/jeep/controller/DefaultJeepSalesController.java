package com.promineotech.jeep.controller;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;

//This tells Spring that this is a restController 
//This restcontroller tells Spring boot that this class is special--
//it is a controller of JeepSalesController so it implements that interface 
//while finds that the class is mapped to /jeeps; get verb needs two parameters
@RestController
public class DefaultJeepSalesController implements JeepSalesController {

  
  @Override
  public List<Jeep> fetchJeeps(JeepModel model, String trim) {
    // TODO Auto-generated method stub
    return null;
  }

}
