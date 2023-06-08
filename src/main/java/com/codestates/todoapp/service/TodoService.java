package com.codestates.todoapp.service;

import com.codestates.todoapp.domain.Todo;
import com.codestates.todoapp.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public Todo createTodo(Todo todo) {
        validateTodo(todo);
        return todoRepository.save(todo);
    }


    public Todo updateTodo(Todo todo) {
        validateTodo(todo);

        Todo existingTodo = getExistingTodoById(todo.getId());

        existingTodo.updateTitle(todo.getTitle());
        existingTodo.updateTodoOrder(todo.getTodoOrder());
        existingTodo.updateCompleted(todo.isCompleted());
        return todoRepository.save(existingTodo);
    }

    @Transactional(readOnly = true)
    public Todo getTodo(Long id) {
        return getExistingTodoById(id);
    }

    @Transactional(readOnly = true)
    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }

    public void deleteTodo(Long id) {
        Todo todo = getExistingTodoById(id);
        todoRepository.delete(todo);
    }

    public void deleteAllTodos() {
        todoRepository.deleteAll();
    }

    private void validateTodo(Todo todo) {

        if (todo.getTitle() == null || todo.getTitle().isEmpty()) {
            throw new IllegalArgumentException("제목을 입력하지 않았습니다.");
        }

        if (todo.getTodoOrder() < 0) {
            throw new IllegalArgumentException("TodoOrder는 음수일 수 없습니다.");
        }

        if (todo.getTitle().length() > 50) {
            throw new IllegalArgumentException("제한된 제목 길이를 초과했습니다.");
        }
    }

    private Todo getExistingTodoById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id: " + id + " 에 해당하는 Todo를 찾을 수 없습니다."));
    }
}

