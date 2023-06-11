package com.codestates.todoapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
    @AllArgsConstructor
    public static class Patch {
        private Long id;
        private String title;
        private int todoOrder;
        private boolean completed;

        public void updateId(Long id) {
            this.id = id;
        }
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


