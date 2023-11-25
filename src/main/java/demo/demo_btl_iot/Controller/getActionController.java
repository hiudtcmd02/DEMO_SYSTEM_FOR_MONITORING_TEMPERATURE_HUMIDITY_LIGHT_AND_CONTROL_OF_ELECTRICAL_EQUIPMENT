package demo.demo_btl_iot.Controller;

import demo.demo_btl_iot.Entity.Action;
import demo.demo_btl_iot.Service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class getActionController {
    @Autowired
    private ActionService actionService;

    @GetMapping("/getAction")
    public List<Action> showAction(){
        return actionService.getAllAction();
    }

    @GetMapping("/lights")
    public Map<String, Long> getLightCounts() {
        long onCount = actionService.getCountState("Đèn", "ON");
        long offCount = actionService.getCountState("Đèn", "OFF");

        Map<String, Long> counts = new HashMap<>();
        counts.put("onCount", onCount);
        counts.put("offCount", offCount);

        return counts;
    }

    @GetMapping("/fans")
    public Map<String, Long> getFanCounts() {
        long onCount = actionService.getCountState("Quạt", "ON");
        long offCount = actionService.getCountState("Quạt", "OFF");

        Map<String, Long> counts = new HashMap<>();
        counts.put("onCount", onCount);
        counts.put("offCount", offCount);

        return counts;
    }
}
