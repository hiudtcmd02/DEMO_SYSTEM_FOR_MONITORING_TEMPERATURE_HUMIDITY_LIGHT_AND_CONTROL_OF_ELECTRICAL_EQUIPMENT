package demo.demo_btl_iot.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DataController {
    @GetMapping("/data")
    public String data(Model model){
        return "data";
    }
}
