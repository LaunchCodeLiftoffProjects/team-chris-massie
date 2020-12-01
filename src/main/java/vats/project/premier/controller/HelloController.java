package vats.project.premier.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {

    //localhost:8080
    @GetMapping
    @ResponseBody
    public String hello() {
        return "Hello World!";
    }

    //localhost:8080/hello?name=World
    @GetMapping("hello")
    @ResponseBody
    public String helloWithQueryParm(@RequestParam String name) {
        return "Hello, " + name + "!";
    }

    //localhost:8080/hello/World
    @GetMapping("hello/{name}")
    @ResponseBody
    public String helloWithPathVariable(@PathVariable String name){
        return "Hello, " + name+ "!";
    }

    //localhost:8080/form
    @GetMapping("form")
    @ResponseBody
    public String helloForm(){
        return "<html>" +
                "<body>" +
                "<form action = 'hello'>" +
                "<input type = 'text' name='name'>" +
                "<input type = 'submit' value = 'Greet me!'>" +
                "</form>" +
                "</body>" +
                "</html>";
    }

}