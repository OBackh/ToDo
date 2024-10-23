package org.example.todo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Generates Getter, Setter and so on...
@NoArgsConstructor
@AllArgsConstructor
public class ToDoDTO {
    private String description;
    private String status;
}
