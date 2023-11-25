package demo.demo_btl_iot.Service;

import demo.demo_btl_iot.Entity.Action;
import demo.demo_btl_iot.Repository.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActionService {
    @Autowired
    private ActionRepository actionRepository;

    public void saveAction(Action a){
        actionRepository.save(a);
    }

    public List<Action> getAllAction(){
        return actionRepository.findAllByOrderByDateTimeDesc();
    }

    public Long getCountState(String device, String state){
        return actionRepository.countByDeviceNameAndState(device, state);
    }
}
