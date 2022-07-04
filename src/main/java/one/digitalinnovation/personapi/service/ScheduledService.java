package one.digitalinnovation.personapi.service;

import one.digitalinnovation.personapi.repository.DeviceRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ScheduledService {
    private final DeviceRepository deviceRepository;
    private final DeviceService deviceService;

    public ScheduledService(DeviceRepository deviceRepository, DeviceService deviceService) {
        this.deviceRepository = deviceRepository;
        this.deviceService = deviceService;
    }

    @Scheduled(fixedDelay = 10000)
    public void setDevicesInformation() {
        deviceRepository.findAll().forEach(deviceService::setDeviceTemperatureHumidityStateAndSpeedToDevice);
    }
}
