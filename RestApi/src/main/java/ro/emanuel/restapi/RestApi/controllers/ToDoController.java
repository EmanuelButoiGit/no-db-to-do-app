package ro.emanuel.restapi.RestApi.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.emanuel.restapi.RestApi.classes.ToDo;
import ro.emanuel.restapi.RestApi.servicies.ToDoService;

import java.util.List;

@RestController
@RequestMapping("todos")
@RequiredArgsConstructor
public class ToDoController {

    private final ToDoService toDoService;

    //To remember to instantiate the service or use @RequiredArgsConstructor
    /*public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }*/


    @PostMapping("postparam")
    public ToDo createToDo(@RequestParam(value="title", defaultValue="Hello!") String title){
        return toDoService.createToDoParam(title);
    }

    @PostMapping("postbody")
    public String createToDo(@RequestBody ToDo toDo){
        return toDoService.createToDoBody(toDo);
    }

    @GetMapping
    public List<ToDo> getAllToDos(){
        return toDoService.getAllToDos();
    }

    @GetMapping("{id}")
    public ToDo getToDoById(@PathVariable("id") Long id){
        return toDoService.getToDoById(id);
    }

    @DeleteMapping("{id}")
    public ToDo deleteToDo(@PathVariable("id") Long id){
        return toDoService.deleteToDo(id);
    }

    @PutMapping("{id}")
    public ToDo updateToDo(@PathVariable("id") Long id, @RequestBody ToDo toDo){
        return toDoService.updateToDo(id, toDo);
    }
}
