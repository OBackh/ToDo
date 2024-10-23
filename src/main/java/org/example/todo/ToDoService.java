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

    // Method to create a new 2Do-Entry
    public void createToDo(ToDoEntry toDoEntry) {
        toDoRepository.save(toDoEntry);
    }

    //find/List all ToDos
    public List<ToDoEntry> findAll(){
        return toDoRepository.findAll();
    }

}
