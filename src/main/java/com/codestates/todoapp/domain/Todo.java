package com.codestates.todoapp.domain;

import lombok.*;

import javax.persistence.*;


@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(nullable = false)
    private int todoOrder;

    @Column(nullable = false)
    private boolean completed;

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateTodoOrder(int todoOrder) {
        this.todoOrder = todoOrder;
    }

    public void updateCompleted(boolean completed) {
        this.completed = completed;
    }
}
