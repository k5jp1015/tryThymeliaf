package demo.thymeleaf.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello2")
public class HelloContrioller2 {

    @RequestMapping(method=RequestMethod.GET)
    public String getMethod() {
        return "get";
    }

    @RequestMapping(method=RequestMethod.POST)
    public String postMethod1() {
        return "post";
    }

    @RequestMapping(value="/hey", method=RequestMethod.POST)
    public String postMethod2() {
        return "hey post";
    }
}
