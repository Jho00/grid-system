package com.sstu.carservice.task.distribution;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.sstu.carservice.car.Car;
import com.sstu.carservice.car.CarStatus;
import com.sstu.carservice.car.management.CarManager;
import com.sstu.carservice.model.ResponseModel;
import com.sstu.carservice.task.ExecuteTask;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;

import java.util.Optional;

@Slf4j
public class TaskDistributor {

    private ObjectMapper objectMapper = new ObjectMapper();
    private CarManager carManager = new CarManager();

    @SneakyThrows
    public ResponseModel send(ExecuteTask task) {
        Optional<Car> availableCar = carManager.getAvailableCar();
        boolean isAvailable = availableCar.isPresent();
        if (isAvailable) {
            Car car = availableCar.get();
            String data;
            try {
                data = objectMapper.writeValueAsString(task);
            } catch (JsonProcessingException e) {
                log.info("Error while mapping object to json", e);
                return new ResponseModel("fail", "Cannot parse task from broker.");
            }
            URL address = new URL(car.getAddress());
            InetSocketAddress socketAddress = new InetSocketAddress(address.getHost(), address.getPort());
            Socket socket = new Socket();
            socket.connect(socketAddress);

            car.setStatus(CarStatus.BUSY);

            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(data);
            dataOutputStream.flush();

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            String inputData = dataInputStream.readUTF();
            socket.close();

            ResponseModel response;
            try {
                response = objectMapper.readValue(inputData, ResponseModel.class);
            } catch (IOException e) {
                return new ResponseModel("fail", "Cannot parse response from client.");
            }

            car.setStatus(CarStatus.ACTIVE);
            return response;
        }
        return new ResponseModel("fail", "There is no available cars.");
    }
}
