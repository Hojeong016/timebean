package com.hj.timebean.controller;

import com.hj.timebean.auth.PrincipalDetails;
import com.hj.timebean.dto.TodoDTO;
import com.hj.timebean.entity.Todo;
import com.hj.timebean.repository.TodoRepository;
import com.hj.timebean.service.todo.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TodoController {

    private final TodoService todoService;
    private final TodoRepository todoRepository;

    @Autowired
    public TodoController(TodoService todoService, TodoRepository todoRepository) {
        this.todoService = todoService;
        this.todoRepository = todoRepository;
    }

    @GetMapping("/todos")
    public List<TodoDTO> getTodos(Authentication authentication) {
        long memberId = 0L;
        List<TodoDTO> todoList = new ArrayList<>();

        if (authentication != null) {
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            memberId = principalDetails.getMember().getId();
            todoList = todoService.findByMemberIdAndStatus(memberId, true);
        }

        return todoList;
    }

    @PostMapping("/addTodo")
    public ResponseEntity<String> addTodo(@RequestParam("text") String text, Authentication authentication) {
        TodoDTO todoDTO = new TodoDTO();

        if (authentication != null) {
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            Long memberId = principalDetails.getMember().getId();
            todoDTO.setMemberId(memberId);
        }
        todoDTO.setText(text);

        todoService.saveTodo(todoDTO);  // 저장

        return new ResponseEntity<>("Todo added successfully", HttpStatus.CREATED);
    }

    @PutMapping("/todos/{id}")
    public void updateTodoStatus(@PathVariable Long id, @RequestParam("completed") boolean completed) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));
        todo.setCompleted(completed);
        todoRepository.save(todo);
    }

    @DeleteMapping("/todos/{id}")
    public void deleteTodo(@PathVariable Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));
        todo.setStatus(false);
        todoRepository.save(todo);
    }
}
