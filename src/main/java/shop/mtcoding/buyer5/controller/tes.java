package shop.mtcoding.buyer5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class tes {

    @GetMapping("/")
    public String tes() {
        return "tes";
    }
}
