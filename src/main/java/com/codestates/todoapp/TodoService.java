package com.codestates.todoapp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }


    public Todo updateTodo(Todo todo) {
        Optional<Todo> optionalTodo = todoRepository.findById(todo.getId());

        if (optionalTodo.isPresent()) {
            Todo existingTodo = optionalTodo.get();

            existingTodo.setTitle(todo.getTitle());
            existingTodo.setTodoOrder(todo.getTodoOrder());
            existingTodo.setCompleted(todo.isCompleted());
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

