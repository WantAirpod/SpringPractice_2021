package jpabook.jpashop;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    /**
     * hello라는 요청이 오면 처리하는 메서드
     * @param model
     * @return resource->template->view(hello.html)
     */
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello!!");
        return "hello"; //retrun "hello.html"
    }
}
