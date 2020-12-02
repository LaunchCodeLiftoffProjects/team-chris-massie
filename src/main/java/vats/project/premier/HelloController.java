package vats.project.premier;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
class HelloController {

    @GetMapping("hello")
    @ResponseBody
    public String helloWorld(){

        return "Hello, World!";
    }
}
