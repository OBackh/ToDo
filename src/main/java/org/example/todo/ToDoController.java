package org.example.todo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ToDoController {

    private final ToDoService toDoService;

    //Inject
    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    //Fetch all the todos through the service layer
    @GetMapping("/api/todo")
    public List<ToDoEntry> getAllToDos() {
        return toDoService.findAll();
    }

    @PostMapping("/api/todo")
    public void addToDo(@RequestBody ToDoDTO toDoDTO) {
        //Map ToDoDTO to ToDoEntry
        ToDoEntry toDoEntry = toDoService.mapToDoDTOToEntry(toDoDTO);
        //Save the mapped ToDoEntry
        toDoService.createToDo(toDoEntry);
    }

    @GetMapping("/api/todo/{id}")
    public ResponseEntity<ToDoEntry> getToDoById(@PathVariable String id) {
        Optional<ToDoEntry> toDoEntry = toDoService.findToDoById(id);
        if (toDoEntry.isPresent()) {
            return ResponseEntity.ok(toDoEntry.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
