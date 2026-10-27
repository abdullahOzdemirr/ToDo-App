package com.SpringUygulama.ToDo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponseDTO {
    private String title;
    private String description;
    private boolean completed;
    private Long categoryId;

    public TaskResponseDTO(String title, String description, boolean completed) {
            this.title=title;
            this.description=description;
            this.completed=completed;
    }
}
