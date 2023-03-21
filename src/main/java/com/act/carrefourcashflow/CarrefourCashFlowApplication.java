package com.act.carrefourcashflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class CarrefourCashFlowApplication {

  public static void main(String[] args) {
    SpringApplication.run(CarrefourCashFlowApplication.class, args);
  }
}
