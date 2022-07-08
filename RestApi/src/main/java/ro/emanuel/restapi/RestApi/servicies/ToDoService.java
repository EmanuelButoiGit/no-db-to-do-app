package ro.emanuel.restapi.RestApi.servicies;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import ro.emanuel.restapi.RestApi.classes.ToDo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;


@Service
public class ToDoService {

    private List<ToDo> toDoList = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    public void streamMethod(ToDo toDo){
        boolean weHaveTheSameElement = toDoList.stream()
                .anyMatch(it -> Objects.equals(it.getId(), toDo.getId()));
        if(weHaveTheSameElement) throw new IllegalStateException("Element with the same id!");
    }

    public String createToDoBody(ToDo toDo){
        for (ToDo aDo : toDoList) {
            if(Objects.equals(toDo.getId(), aDo.getId())) throw new IllegalStateException();
        }
        toDoList.add(toDo);
        return "Item added!";
    }

    public ToDo createToDoParam(String title){
        ToDo toDo = new ToDo(counter.incrementAndGet(), title);

        for (ToDo aDo : toDoList) {
            if(Objects.equals(toDo.getId(), aDo.getId())) throw new IllegalStateException();
        }

        toDoList.add(toDo);
        return toDo;
    }

    public List<ToDo> getAllToDos(){
        return toDoList;
    }

    public ToDo getToDoById(Long id){
        for (ToDo toDo : toDoList){
            if(Objects.equals(toDo.getId(), id)){
                return toDo;
            }
        }
        throw new IllegalStateException("No item found with this id: "+ id);
    }

    public ToDo updateToDo(Long id, @NotNull ToDo toDo){
        ToDo aDo = getToDoById(id);
        aDo.setTitle(toDo.getTitle());
        aDo.setCompleted(toDo.getCompleted());

        return aDo;
    }

    public ToDo deleteToDo(Long id){
        ToDo toDo = getToDoById(id);
        toDoList.remove(toDo);

        return toDo;
    }

}
