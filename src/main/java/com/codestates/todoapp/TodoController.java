package com.codestates.todoapp;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/todos")
public class TodoController {

    private TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @PostMapping
    public Todo postTodo(@RequestBody Todo todo) {
        return todoRepository.save(todo);
    }

    @GetMapping
    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }

    @GetMapping("/{todo-id}")
    public Todo getTodo(@PathVariable("todo-id") Long id) {
        return todoRepository.findById(id).orElse(null);
    }

    @PatchMapping("/{todo-id}")
    public Todo patchTodo(@PathVariable("todo-id") Long id, @RequestBody Todo todo) {
        Todo findTodo = todoRepository.findById(id).orElse(null);
        if (findTodo == null) return null;

        findTodo.setTitle(todo.getTitle());
        findTodo.setTodoOrder(todo.getTodoOrder());
        findTodo.setCompleted(todo.isCompleted());
        return todoRepository.save(findTodo);
    }

    @DeleteMapping("/{todo-id}")
    public void deleteTodo(@PathVariable("todo-id") Long id) {
        todoRepository.deleteById(id);
    }

    @DeleteMapping
    public void deleteAllTodos() {
        todoRepository.deleteAll();
    }
}
