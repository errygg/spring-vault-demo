package com.hashicorp.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;

@Controller
public class AppController {

  @Value("${name}")
  public String name;

  @Value("${email}")
  public String email;

  @RequestMapping("/getkvdata")
  public String getKvData(Model model) {
    model.addAttribute("name", name);
    model.addAttribute("email", email);
    return "getkvdata";
  }

  @Autowired
  DataSource dataSource;

  @RequestMapping("/getdbcredentials")
  private String getDbCredentials(Model model) throws Exception {
    try (
      Connection connection = dataSource.getConnection();
      Statement statement = connection.createStatement()
    ) {

      ResultSet resultSet = statement.executeQuery("SELECT USER();");
      resultSet.next();

      model.addAttribute("user", resultSet.getString(1));
      return "getdbcredentials";
    }
  }

  // @RequestMapping("/getdbdata")
  // private String getDbData() throws Exception {
  //   try (
  //     Connection 
  //   )
  // }

  @PostMapping("/restart")
  public void restart() {
    Application.restart();
  }
}
