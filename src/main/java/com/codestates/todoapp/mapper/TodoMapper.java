package com.codestates.todoapp.mapper;

import com.codestates.todoapp.domain.Todo;
import com.codestates.todoapp.dto.TodoDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TodoMapper {

    Todo todoPostToTodo(TodoDto.Post requestBody);

    Todo todoPatchToTodo(TodoDto.Patch requestBody);

    TodoDto.Response todoToTodoResponse(Todo todo);

    List<TodoDto.Response> todosToTodoResponses(List<Todo> todoList);
}
