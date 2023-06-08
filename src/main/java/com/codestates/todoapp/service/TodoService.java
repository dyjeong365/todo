package com.codestates.todoapp.service;

import com.codestates.todoapp.domain.Todo;
import com.codestates.todoapp.repository.TodoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }


    public Todo updateTodo(Todo todo) {
        Optional<Todo> optionalTodo = todoRepository.findById(todo.getId());

        if (optionalTodo.isPresent()) {
            Todo existingTodo = optionalTodo.get();

            existingTodo.updateTitle(todo.getTitle());
            existingTodo.updateTodoOrder(todo.getTodoOrder());
            existingTodo.updateCompleted(todo.isCompleted());
            return todoRepository.save(existingTodo);
        }

        return null;
    }

    public Todo getTodo(Long id) {
        return todoRepository.findById(id).orElse(null);
    }

    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }

    public boolean deleteTodo(Long id) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        if (optionalTodo.isPresent()) {
            todoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void deleteAllTodos() {
        todoRepository.deleteAll();
    }
}

