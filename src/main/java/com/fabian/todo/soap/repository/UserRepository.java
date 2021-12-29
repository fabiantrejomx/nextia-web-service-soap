package com.fabian.todo.soap.repository;

import com.fabian.todo.soap.entity.Task;
import com.fabian.todo.soap.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
}
