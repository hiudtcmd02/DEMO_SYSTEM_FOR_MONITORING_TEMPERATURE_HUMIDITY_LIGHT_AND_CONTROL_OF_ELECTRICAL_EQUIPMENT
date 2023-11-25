package demo.demo_btl_iot.Controller;

import demo.demo_btl_iot.Entity.Data_ESP;
import demo.demo_btl_iot.Service.DataESPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetDataController {
    @Autowired
    private DataESPService dataESPService;

    @GetMapping("/getData")
    public List<Data_ESP> showData(){
        return dataESPService.getAllData();
    }
}
