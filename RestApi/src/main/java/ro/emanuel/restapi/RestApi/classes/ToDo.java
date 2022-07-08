package ro.emanuel.restapi.RestApi.classes;

import lombok.Data;

@Data
public class ToDo {

    private Long id;
    private String title;
    private  Boolean completed;

    public ToDo(Long id, String title) {
        this.id = id;
        this.title = title;
        this.completed = false;
    }

    public ToDo() {

    }
}
