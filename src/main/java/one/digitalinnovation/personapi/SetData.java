package one.digitalinnovation.personapi;

import one.digitalinnovation.personapi.enums.DeviceState;
import one.digitalinnovation.personapi.enums.DeviceType;
import one.digitalinnovation.personapi.model.Device;
import one.digitalinnovation.personapi.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

//@Service
public class SetData implements CommandLineRunner {
    private final DeviceRepository deviceRepository;

    public SetData(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public void run(String... args) throws Exception {
//        Device luz_da_sala = Device.builder().id(12345678L).name("Luz da Sala").ip("192.168.1.200").type(DeviceType.light).state(DeviceState.OFF).build();
        Device luz_do_quarto = Device.builder().name("Ventilador do Quarto das Crianças").ip("192.168.1.200").type(DeviceType.toys).state(DeviceState.OFF).build();
        Device luz_da_suite = Device.builder().name("Ventilador da Suite").ip("192.168.1.201").type(DeviceType.toys).state(DeviceState.OFF).build();

        deviceRepository.save(luz_da_suite);
        deviceRepository.save(luz_do_quarto);
    }
}
