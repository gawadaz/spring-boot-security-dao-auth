package com.jawadaz.springsecurity01.controller;

import com.jawadaz.springsecurity01.entity.User;
import com.jawadaz.springsecurity01.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String getHelloPage() {
        return "hello";
    }

    @PostMapping("/user")
    @ResponseBody
    public ResponseEntity<?> adddNewUser(@RequestBody User user) throws Exception {
        logger.info(">>>>> user request: " + user.toString());
        userService.save(user);
        return ResponseEntity.ok("done");
    }
}
