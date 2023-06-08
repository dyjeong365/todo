package com.codestates.todoapp.repository;

import com.codestates.todoapp.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
