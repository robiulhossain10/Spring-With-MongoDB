package com.oauth2.springwithmongodb.controller;

import com.oauth2.springwithmongodb.dtos.*;
import com.oauth2.springwithmongodb.entity.User;
import com.oauth2.springwithmongodb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

    @GetMapping
 public Map<String,String> index(){
     Map<String,String> map = new HashMap<>();

     map.put("Message", "API is Running");
     map.put("Author","Robiul Hossain");

     return map;
 }
}
