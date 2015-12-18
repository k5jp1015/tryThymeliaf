package demo.thymeleaf.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@EnableAutoConfiguration
public class InputController {
	@RequestMapping("/input")
	public String input() {
	    return "input"; // input form
	}


	@RequestMapping("/jikken")
	public String jikken() {
	    return "input"; // input form
	}

	// inputフォームから受け取ってhello.htmlへ
	@RequestMapping("/send")
	public String send(Model model, @RequestParam("name") String name) {
	  model.addAttribute("name", name);
	  return "hello";    // View file is templates/hello.html
	}

	@Autowired
	//↓ここがキモ
    PersonRepository repository;

    @RequestMapping("/person-view")
    public String personView(Model model) {
      Iterable<Person> list = repository.findAll();
      model.addAttribute("results", list);
      return "person-view";
    }

    @RequestMapping(value="/post", method=RequestMethod.POST)
    public String personSearch(Model model,
      @RequestParam("name") String name,
      @RequestParam("tel") String tel,
      @RequestParam("mail") String mail,
      @RequestParam("description") String description) {

        Person person = new Person(name, tel, mail, description);
        repository.saveAndFlush(person);
        Iterable<Person> list = repository.findAll();
        model.addAttribute("results", list);
        return "person-view";
    }


    @Autowired
	//↓ここがキモ
    DesiredRepository repositoryDesired;


    @RequestMapping("/onedari")
    public String onedariList(Model model) {
      Iterable<DesiredThing> list = repositoryDesired.findAll();
      model.addAttribute("resultsDesired", list);
      return "onedari";
    }

    @RequestMapping(value="/onedariPost", method=RequestMethod.POST)
    public String desiredSearch(Model model,
      @RequestParam("name") String name,
      @RequestParam("desired") String desired,

      @RequestParam("reason") String reason) {

        DesiredThing Desired = new DesiredThing(name, desired, reason);
        repositoryDesired.saveAndFlush(Desired);
        Iterable<DesiredThing> list = repositoryDesired.findAll();
        model.addAttribute("resultsDesired", list);
        return "onedari";

    }

}
