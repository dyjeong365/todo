package com.codestates.todoapp;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        Todo findTodo = todoRepository.findById(todo.getId()).orElse(null);

        if (findTodo == null) return null;

        findTodo.setTitle(todo.getTitle());
        findTodo.setTodoOrder(todo.getTodoOrder());
        findTodo.setCompleted(todo.isCompleted());

        return todoRepository.save(findTodo);
    }

    public Todo getTodo(Long id) {
        return todoRepository.findById(id).orElse(null);
    }

    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }

    public void deleteTodo(long id) {
        todoRepository.deleteById(id);
    }

    public void deleteAllTodos() {
        todoRepository.deleteAll();
    }
}

