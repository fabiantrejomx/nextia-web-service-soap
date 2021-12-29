package com.fabian.todo.soap.service;

import com.fabian.todo.soap.entity.Task;
import com.fabian.todo.soap.entity.User;
import com.fabian.todo.soap.repository.TaskRepository;
import com.fabian.todo.soap.repository.UserRepository;
import com.fabian.todo.soap.task.DeleteTaskRequest;
import com.fabian.todo.soap.task.DeleteTaskResponse;
import com.fabian.todo.soap.task.TaskRequest;
import com.fabian.todo.soap.task.TaskResponse;
import com.fabian.todo.soap.task.UpdateTaskRequest;
import com.fabian.todo.soap.task.UpdateTaskResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskResponse creteNewTask(final TaskRequest taskRequest){
        final User user = userRepository.findById(taskRequest.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        final Task task = new Task(taskRequest.getSubject(),taskRequest.isIsDone(), user);
        taskRepository.save(task);
        log.info("A new task was save");

        final TaskResponse taskResponse = new TaskResponse();
        taskResponse.setId(task.getId());
        taskResponse.setIsDone(taskRequest.isIsDone());
        taskResponse.setSubject(taskRequest.getSubject());

        return taskResponse;
    }

    public DeleteTaskResponse deleteTask(final DeleteTaskRequest deleteTaskRequest){
        final Task task = taskRepository.findById(deleteTaskRequest.getTaskId())
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));

        taskRepository.delete(task);
        log.info("Task with ID {} was deleted", deleteTaskRequest.getTaskId());

        final DeleteTaskResponse response = new DeleteTaskResponse();
        response.setStatus(true);
        return response;
    }

    public UpdateTaskResponse update(final UpdateTaskRequest request){
        final Task task = taskRepository.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));

        task.update(request.getSubject(), request.isIsDone());
        taskRepository.save(task);
        log.info("Task with ID {} was updated", request.getId());

        final UpdateTaskResponse response = new UpdateTaskResponse();
        response.setStatus(true);
        return  response;
    }


}
