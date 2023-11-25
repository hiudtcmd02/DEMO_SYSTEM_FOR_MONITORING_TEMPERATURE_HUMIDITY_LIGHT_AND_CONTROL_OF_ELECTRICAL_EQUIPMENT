package demo.demo_btl_iot.Repository;

import demo.demo_btl_iot.Entity.Action;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActionRepository extends JpaRepository<Action, Long> {
    List<Action> findAllByOrderByDateTimeDesc();

    long countByDeviceNameAndState(String deviceName, String state);
}
