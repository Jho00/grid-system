package com.sstu.carservice.car.management;

import com.sstu.carservice.appconfig.ApplicationConfig;
import com.sstu.carservice.car.Car;
import com.sstu.carservice.car.CarStatus;
import com.sstu.carservice.appconfig.ConfigModel;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class CarManager {

    private ConfigModel configModel = ApplicationConfig.getConfig();
    private List<String> addresses = configModel.getCars();

    @Getter
    private static Set<Car> cars = new HashSet<>();

    public void initCars() {
        for (String address : addresses) {
            cars.add(new Car(UUID.randomUUID(), address, CarStatus.UNDEFINED));
        }
        log.info("Initialized cars - {}", cars);
    }

    public Optional<Car> getAvailableCar() {
        return cars.stream().filter(car -> car.getStatus().equals(CarStatus.ACTIVE)).findFirst();
    }

    public Set<Car> getAllAvailableCars() {
        return cars.stream().filter(car -> car.getStatus().equals(CarStatus.ACTIVE)).collect(Collectors.toSet());
    }

    @SneakyThrows
    public void checkCarsHealth() {
        for (Car car : cars) {
            URL address = new URL(car.getAddress());
            try {
                InetSocketAddress socketAddress = new InetSocketAddress(address.getHost(), address.getPort());
                Socket socket = new Socket();
                socket.connect(socketAddress, 1);
                car.setStatus(CarStatus.ACTIVE);
                socket.close();
            } catch (Exception e) {
                car.setStatus(CarStatus.INACTIVE);
            }
        }
        log.info("car statuses - {}", cars);
    }
}