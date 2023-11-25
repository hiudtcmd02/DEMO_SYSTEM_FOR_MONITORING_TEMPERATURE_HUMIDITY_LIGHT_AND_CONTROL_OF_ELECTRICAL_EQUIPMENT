package demo.demo_btl_iot.Controller;

import demo.demo_btl_iot.Entity.Data_ESP;
import demo.demo_btl_iot.Service.DataESPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }
}