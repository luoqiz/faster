package org.wolf.security1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/test")
@RestController
public class TestController {

    @GetMapping(value = "test1")
    public String test1(){
        return "test111111111";
    }
}
