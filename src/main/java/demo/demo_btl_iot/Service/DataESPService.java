package demo.demo_btl_iot.Service;

import demo.demo_btl_iot.Entity.Data_ESP;
import demo.demo_btl_iot.Repository.DataESPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataESPService {
    @Autowired
    private DataESPRepository dataESPRepository;

    public Data_ESP getNewestData(){
        return dataESPRepository.findFirstByOrderByDateTimeDesc();
    }
    public List<Data_ESP> getAllData(){
        return dataESPRepository.findAllByOrderByDateTimeDesc();
    }
}
