package com.SpringUygulama.ToDo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;
    private String title;
    private String description;
    private boolean completed;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user-task")
    private User user;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference(value = "category-task")
    private Category category;
    @OneToMany(mappedBy = "task",cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonManagedReference(value = "task-comment")
    private List<Comment> comments;
}
