package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@Controller
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("/")
    @ResponseBody
    public Map<String, Object> root() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Hello World!");
        response.put("object", new HashMap<String, String>() {
            { put("key1", "value1"); }
            { put("key2", "value2"); }
        });
        System.out.println(response.get("message"));
        return response;
    }

    @GetMapping("/{user_name}")
    @ResponseBody
    public Map<String, String> users(
            @PathVariable("user_name") String userName,
            @RequestParam(name = "custom_message", required = false) String customMessage) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello " + userName + " " + customMessage);
        return response;
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }
    @GetMapping("/user_index/{user_name}")
    public String userIndex(@PathVariable("user_name") String userName, Model model) {
        model.addAttribute("userName", userName);
        return "user_index";
    }
}