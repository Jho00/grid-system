package com.sstu.carservice.request.handler;

import com.sstu.carservice.model.ResponseModel;
import com.sstu.carservice.task.Task;

public interface RequestHandler {
    ResponseModel handle(Task task);
}
