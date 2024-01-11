package com.example.n1m9;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class test {
    @GetMapping("/main/test")
    @ResponseBody
    public String test(){
        return "test";
    }

}
