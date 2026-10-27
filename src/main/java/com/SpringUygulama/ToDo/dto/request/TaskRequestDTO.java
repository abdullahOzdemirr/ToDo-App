package com.SpringUygulama.ToDo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequestDTO {
    private String title;
    private String description;
    private boolean completed;
    private Long categoryId;

    public TaskRequestDTO(String title, String description, boolean completed) {
            this.title=title;
            this.description=description;
            this.completed=completed;
    }
}
