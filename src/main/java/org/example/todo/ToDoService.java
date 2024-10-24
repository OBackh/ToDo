package org.example.todo;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {
    private final ToDoRepository toDoRepository;

    //Inject the ToDoRepository via constructor
    public ToDoService(ToDoRepository toDoRepository){
        this.toDoRepository = toDoRepository;
    }

    // Method to create a new TwoDo-Entry
    public void createToDo(ToDoEntry toDoEntry) {
        toDoRepository.save(toDoEntry);
    }

    //find/List all ToDos
    public List<ToDoEntry> findAll(){
        return toDoRepository.findAll();
    }

    //Mapping from ToDoDTO to ToDoEntry
    public ToDoEntry mapToDoDTOToEntry(ToDoDTO toDoDTO) {
        // Validation
        return new ToDoEntry(null, toDoDTO.getDescription(), toDoDTO.getStatus());
    }

    //Find twoDo by ID
    public Optional<ToDoEntry> findToDoById(String id){
        return toDoRepository.findById(id);
    }

}
