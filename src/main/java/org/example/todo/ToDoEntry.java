package org.example.todo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("toDoCollection")
public record ToDoEntry (@Id String id, String description, String status) {
}
