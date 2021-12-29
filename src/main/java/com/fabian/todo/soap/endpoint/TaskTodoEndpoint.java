package com.fabian.todo.soap.endpoint;

import com.fabian.todo.soap.service.TaskService;
import com.fabian.todo.soap.task.TaskRequest;
import com.fabian.todo.soap.task.TaskResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@Slf4j
public class TaskTodoEndpoint {

    private static final String NAMESPACE = "http://www.fabian.com/todo/soap/task";

    @Autowired
    private TaskService taskService;

    @PayloadRoot(namespace = NAMESPACE, localPart = "TaskRequest")
    @ResponsePayload
    public TaskResponse createTask(@RequestPayload final TaskRequest taskRequest){
        return taskService.creteNewTask(taskRequest);
    }

}