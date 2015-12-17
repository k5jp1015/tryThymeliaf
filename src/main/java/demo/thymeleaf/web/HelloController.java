package demo.thymeleaf.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping(method=RequestMethod.GET)
    public String hello(Model model) {
        Hoge hoge = new Hoge();
        hoge.id = 10;
        hoge.value = "hoge";

        model.addAttribute("myData", hoge);

        return "hello";
    }
}