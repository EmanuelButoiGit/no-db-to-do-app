package ro.emanuel.restapi.RestApi.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ro.emanuel.restapi.RestApi.classes.ToDo;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureTestDatabase
class ToDoControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private TestRestTemplate rest;

    @Test
    void createToDoTest() {
        // assume
        final ToDo toCreate = new ToDo();
        toCreate.setTitle("Test");
        toCreate.setCompleted(true);

        // act
        final ResponseEntity<ToDo> result = rest.postForEntity("/todos", toCreate, ToDo.class);

        // assert
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody().getId()).isNotNull();
        assertThat(result.getBody().getTitle()).isEqualTo("Test");
    }

}