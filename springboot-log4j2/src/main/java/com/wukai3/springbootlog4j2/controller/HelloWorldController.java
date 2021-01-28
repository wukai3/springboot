package com.wukai3.springbootlog4j2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/v1")
public class HelloWorldController {
    @GetMapping(value = "/hello-world")
    public ResponseEntity<String> helloWorld() {
        log.info("This is info log from hello-world API.");
        log.debug("This is debug log from hello-world API.");
        log.error("This is error log from hello-world API.");
        return ResponseEntity.ok().body("OKOKOK");
    }
}
