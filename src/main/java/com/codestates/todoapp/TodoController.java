package com.codestates.todoapp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/todos")
public class TodoController {

    private final TodoService todoService;
    private final TodoMapper mapper;

    public TodoController(TodoService todoService, TodoMapper mapper) {
        this.todoService = todoService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<TodoDto.Response> postTodo(@RequestBody TodoDto.Post requestBody) {
        Todo todo = mapper.todoPostToTodo(requestBody);
        Todo createdTodo = todoService.createTodo(todo);

        return new ResponseEntity<>(mapper.todoToTodoResponse(createdTodo), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TodoDto.Response>> getTodos() {

        return new ResponseEntity<>(mapper.todosToTodoResponses(todoService.getTodos()), HttpStatus.OK);
    }

    @GetMapping("/{todo-id}")
    public ResponseEntity<TodoDto.Response> getTodo(@PathVariable("todo-id") Long id) {

        return new ResponseEntity<>(mapper.todoToTodoResponse(todoService.getTodo(id)), HttpStatus.OK);
    }

    @PatchMapping("/{todo-id}")
    public ResponseEntity<TodoDto.Response> patchTodo(@PathVariable("todo-id") Long id,
                                                      @RequestBody TodoDto.Patch requestBody) {
        requestBody.setId(id);

        Todo getTodo = mapper.todoPatchToTodo(requestBody);

        return new ResponseEntity<>(mapper.todoToTodoResponse(todoService.updateTodo(getTodo)), HttpStatus.OK);
    }

    @DeleteMapping("/{todo-id}")
    public ResponseEntity<HttpStatus> deleteTodo(@PathVariable("todo-id") Long id) {
        todoService.deleteTodo(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllTodos() {
        todoService.deleteAllTodos();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
