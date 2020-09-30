package com.hashicorp.app;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@RestController
public class Controller {

  @Value("${name}")
  public String name;

  @Value("${email}")
  public String email;

  @RequestMapping("/getkvdata")
  public String getKvData() {
    return "Name: " + name + " and email: " + email;
  }

  @Autowired
  DataSource dataSource;

  @RequestMapping("/getdbcredentials")
  private String getDbCredentials() throws Exception {
    try (
      Connection connection = dataSource.getConnection();
      Statement statement = connection.createStatement()
    ) {

      ResultSet resultSet = statement.executeQuery("SELECT USER();");
      resultSet.next();

      return "Connection works with User: " + resultSet.getString(1) + " and Password: ";
    }
  }

  // @RequestMapping("/getdbdata")
  // private String getDbData() throws Exception {
  //   try (
  //     Connection 
  //   )
  // }
}
