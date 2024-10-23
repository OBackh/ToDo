package org.example.todo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
