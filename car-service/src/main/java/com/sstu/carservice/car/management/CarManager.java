package com.sstu.carservice.car.management;

import com.sstu.carservice.appconfig.ApplicationConfig;
import com.sstu.carservice.appconfig.ConfigModel;
import com.sstu.carservice.car.Car;
import com.sstu.carservice.car.CarStatus;
import com.sstu.carservice.task.ConnectionTask;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
public class CarManager {

    @Getter
    private static Set<Car> cars = new HashSet<>();
    private ConfigModel configModel = ApplicationConfig.getConfig();

    public void initCars() {
        for (String address : configModel.getCarAddresses()) {
            cars.add(new Car(UUID.randomUUID(), address, CarStatus.UNDEFINED));
        }
        log.info("Initialized cars - {}", cars);
    }

    public Optional<Car> getAvailableCar() {
        return cars.stream().filter(car -> car.getStatus().equals(CarStatus.ACTIVE)).findAny();
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

    public void connect(ConnectionTask connectionTask) {
        String address = getCarAddress(connectionTask);
        Car car = new Car(UUID.randomUUID(), address, CarStatus.REGISTERED);
        cars.add(car);
        log.info("Car - {} successfully registered.", car);
    }

    public void disconnect(ConnectionTask connectionTask) {
        String address = getCarAddress(connectionTask);
        boolean removed = cars.removeIf(car -> car.getAddress().equals(address));
        if (removed) {
            log.info("Car with address - {} successfully deleted.", address);
        } else {
            log.info("Car with address - {} not found.", address);
        }
    }

    private String getCarAddress(ConnectionTask connectionTask) {
        String host = connectionTask.getPayload().getAddress();
        String port = connectionTask.getPayload().getPort();
        return "http://" + host + ":" + port;
    }
}