package com.sstu.carservice.request;

import com.sstu.carservice.model.ResponseModel;
import com.sstu.carservice.request.handler.RequestHandler;
import com.sstu.carservice.task.ExecuteTask;
import com.sstu.carservice.task.Task;
import com.sstu.carservice.task.distribution.TaskDistributor;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExecuteTaskRequestHandler implements RequestHandler {

    @NonNull TaskDistributor taskDistributor;

    @Override
    public ResponseModel handle(Task task) {
        return taskDistributor.send((ExecuteTask) task);
    }
}
