package com.codestates.todoapp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class TodoDto {

    private TodoDto() {
    }

    @Getter
    @AllArgsConstructor
    public static class Post {
        private String title;
        private int todoOrder;
        private boolean completed;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Patch {
        private Long id;
        private String title;
        private int todoOrder;
        private boolean completed;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private String title;
        private int todoOrder;
        private boolean completed;
    }
}


