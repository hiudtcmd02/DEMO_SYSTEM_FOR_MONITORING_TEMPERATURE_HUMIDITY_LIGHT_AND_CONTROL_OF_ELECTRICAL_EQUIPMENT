package demo.demo_btl_iot.Repository;

import demo.demo_btl_iot.Entity.Data_ESP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DataESPRepository extends JpaRepository<Data_ESP, Long> {
    Data_ESP findFirstByOrderByDateTimeDesc(); //SELECT * FROM iot.data_esp order by date_time desc limit 1;

    List<Data_ESP> findAllByOrderByDateTimeDesc();
}
