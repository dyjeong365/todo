package com.codestates.todoapp.controller;

import com.codestates.todoapp.domain.Todo;
import com.codestates.todoapp.dto.TodoDto;
import com.codestates.todoapp.mapper.TodoMapper;
import com.codestates.todoapp.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;
    private final TodoMapper mapper;

    @PostMapping
    public ResponseEntity<TodoDto.Response> postTodo(@RequestBody TodoDto.Post requestBody) {
        Todo createdTodo = todoService.createTodo(mapper.todoPostToTodo(requestBody));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.todoToTodoResponse(createdTodo));
    }

    @GetMapping
    public ResponseEntity<List<TodoDto.Response>> getAllTodos() {
        List<Todo> todos = todoService.getTodos();
        return ResponseEntity.status(HttpStatus.OK).body(mapper.todosToTodoResponses(todos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoDto.Response> getTodoById(@PathVariable("id") Long id) {
        Todo todo = todoService.getTodo(id);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.todoToTodoResponse(todo));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TodoDto.Response> patchTodoById(@PathVariable("id") Long id,
                                                          @RequestBody TodoDto.Patch requestBody) {
        requestBody.setId(id);
        Todo updatedTodo = todoService.updateTodo(mapper.todoPatchToTodo(requestBody));
        return ResponseEntity.status(HttpStatus.OK).body(mapper.todoToTodoResponse(updatedTodo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodoById(@PathVariable("id") Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllTodos() {
        todoService.deleteAllTodos();
        return ResponseEntity.noContent().build();
    }
}
