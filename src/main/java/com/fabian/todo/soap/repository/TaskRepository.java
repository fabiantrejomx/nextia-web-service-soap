package com.fabian.todo.soap.repository;

import com.fabian.todo.soap.entity.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository  extends CrudRepository<Task, String> {
}
