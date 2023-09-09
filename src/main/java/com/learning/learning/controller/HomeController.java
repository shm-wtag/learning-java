package com.learning.learning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
  @GetMapping("/hello")
  public String sayHello() {
    return "hello";
  }
}
