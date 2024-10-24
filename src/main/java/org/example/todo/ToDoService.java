package org.example.todo;

import org.springframework.stereotype.Service;

import java.util.List;

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

    //HIER WEITER AM DONNERSTAG!!!
    public ToDoEntry findToDoById(String id){
        for(ToDoEntry toDoEntry : toDoRepository){
            if(toDoEntry.id() == id){
                ToDoRepository.remove(toDoEntry);
            }
        }
        return toDoRepository.toString();
    }

}
