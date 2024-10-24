package org.example.todo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ToDoController {

    private final ToDoService toDoService;
    private final ToDoRepository toDoRepository;

    //Inject
    public ToDoController(ToDoService toDoService, ToDoRepository toDoRepository) {
        this.toDoService = toDoService;
        this.toDoRepository = toDoRepository;
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
            //Only for Test:
            //return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/api/todo/{id}")
    public ResponseEntity<ToDoEntry> updateToDo(@PathVariable String id, @RequestBody ToDoDTO updatedToDoDTO) {
        //Check if 2Do exists
        Optional<ToDoEntry> existingToDoOpt = toDoRepository.findById(id);

        if (existingToDoOpt.isPresent()) {
            ToDoEntry existingToDo = existingToDoOpt.get();

            //New instance of ToDoEntry with actual data
            ToDoEntry updatedToDo = new ToDoEntry(existingToDo.id(), updatedToDoDTO.getDescription(),updatedToDoDTO.getStatus());


            //Save actual entity in DB
                toDoRepository.save(updatedToDo);

            //Return actual 2do-entity
            return ResponseEntity.ok(updatedToDo);
            } else {
                //If couldn't find 2do
                return ResponseEntity.notFound().build();
            }
    }

}
