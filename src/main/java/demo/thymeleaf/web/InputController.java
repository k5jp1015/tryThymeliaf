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


    @Autowired //←@Autowired アノテーションを定義することでrepositoryプロパティにInjectする。
	//↓ここがキモ Spring DATA JPAの Repository
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
//      ↓指定されたEntity(ここではDesired)に対する永続操作をEntitiyManagerに蓄積した後に、
//        蓄積された（INSERT/UPDATE/DELETE）などをDB（永続層）に反映するためのメソッド
        repositoryDesired.saveAndFlush(Desired);
        Iterable<DesiredThing> list = repositoryDesired.findAll();
        model.addAttribute("resultsDesired", list);
        return "onedari";

    }

    @RequestMapping(value = "/find",method = RequestMethod.POST)
    public String find(Model model,@RequestParam("id") Integer Id){
    	DesiredThing list = null;
    	//findByメソッド・シグネチャ
    	list = repositoryDesired.findById(Id);

    	model.addAttribute("resultsDesiredFind",list);
    	return "onedari";
    }

    //更新処理
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(Model model,@RequestParam("id") Integer Id,@RequestParam("reason") String Rea){
    	DesiredThing list = null;
    	//findByメソッド・シグネチャ
    	list = repositoryDesired.findById(Id);
    	list.setReason(Rea);
    	 repositoryDesired.saveAndFlush(list);

    	model.addAttribute("resultsDesiredUpdate",list);
    	return "onedari";
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public String delete(Model model,@RequestParam("id") Integer Id){
    	DesiredThing DT = null;
    	DT = repositoryDesired.findById(Id);
    	repositoryDesired.delete(DT);

    	model.addAttribute("resultsDesiredDelete","ID:"+Id+"は削除されました");
    	return "onedari";
    }

}
