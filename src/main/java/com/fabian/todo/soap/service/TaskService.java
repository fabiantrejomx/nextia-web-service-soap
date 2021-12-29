package com.fabian.todo.soap.service;

import com.fabian.todo.soap.entity.Task;
import com.fabian.todo.soap.entity.User;
import com.fabian.todo.soap.repository.TaskRepository;
import com.fabian.todo.soap.repository.UserRepository;
import com.fabian.todo.soap.task.TaskRequest;
import com.fabian.todo.soap.task.TaskResponse;
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

    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.DEFAULT,
            rollbackFor = {Exception.class, RuntimeException.class})
    public TaskResponse creteNewTask(final TaskRequest taskRequest){
        final User user = userRepository.findById(taskRequest.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        final Task task = new Task(taskRequest.getSubject(),taskRequest.isIsDone(), user);
        taskRepository.save(task);
        log.info("Task was save");

        final TaskResponse taskResponse = new TaskResponse();
        taskResponse.setId(task.getId());
        taskResponse.setIsDone(taskRequest.isIsDone());
        taskResponse.setSubject(taskRequest.getSubject());

        return taskResponse;
    }
}
