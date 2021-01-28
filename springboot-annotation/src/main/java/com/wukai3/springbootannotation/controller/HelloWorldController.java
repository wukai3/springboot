package com.wukai3.springbootannotation.controller;

import com.wukai3.springbootannotation.annotation.NotNull;
import com.wukai3.springbootannotation.annotation.Range;
import com.wukai3.springbootannotation.annotation.Report;
import com.wukai3.springbootannotation.config.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kaiwu3
 * @since 2020-12-08
 */
@Slf4j
@RestController
@RequestMapping(value = "/v1")
public class HelloWorldController {
    /**
     * GET http://localhost:11001/v1/hello-world?name=wukai
     * GET http://localhost:11001/v1/hello-world?name=wu
     * GET http://localhost:11001/v1/hello-world?name=
     *
     * @param name query string
     * @return OKOKOK
     */
    @Report(type = 10, level = "debug")
    @GetMapping(value = "/hello-world")
    public ResponseEntity<String> helloWorld(@NotNull @Range(min=3, max=20) @RequestParam String name) {
        try {
            log.info("query parameters name: {}", name);
            return ResponseEntity.ok().body("OKOKOK");
        } catch (CommonException ex) {
            return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
        }
    }
}
