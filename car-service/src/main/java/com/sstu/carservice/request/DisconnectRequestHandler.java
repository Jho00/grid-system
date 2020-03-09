package com.sstu.carservice.request;

import com.sstu.carservice.car.management.CarManager;
import com.sstu.carservice.model.ResponseModel;
import com.sstu.carservice.request.handler.RequestHandler;
import com.sstu.carservice.task.ConnectionTask;
import com.sstu.carservice.task.Task;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DisconnectRequestHandler implements RequestHandler {

    @NonNull CarManager carManager;

    @Override
    public ResponseModel handle(Task task) {
        carManager.disconnect((ConnectionTask) task);
        return new ResponseModel("ok", "disconnected");
    }
}
