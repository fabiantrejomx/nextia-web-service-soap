package com.fabian.todo.soap.service;

import com.fabian.todo.soap.task.TaskRequest;
import com.fabian.todo.soap.task.TaskResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TaskService {

    public TaskResponse creteNewTask(final TaskRequest taskRequest){
        TaskResponse taskResponse = new TaskResponse();
        log.info("Request {} -- {} -- {}", taskRequest.getSubject(),
                taskRequest.isIsDone(), taskRequest.getUserId());

        taskRequest.setUserId(taskRequest.getUserId());
        taskRequest.setIsDone(taskRequest.isIsDone());
        taskRequest.setSubject(taskRequest.getSubject());

        return taskResponse;
    }
}
