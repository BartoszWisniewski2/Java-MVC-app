package com.ug.spring.Controllers;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@ComponentScan()
public class HomeController {

    @GetMapping("/")
    public String Index(){
        return "home";
    }

}
