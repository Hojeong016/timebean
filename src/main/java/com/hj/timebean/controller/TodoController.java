package com.hj.timebean.controller;

import com.hj.timebean.entity.Todo;
import com.hj.timebean.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping("/todos")
    public List<Todo> getTodos() {
        return todoRepository.findByStatus(true); // status가 true인 것만 반환
    }

    @PostMapping("/todos")
    public void addTodo(@RequestBody Todo todoRequest) {
        todoRequest.setStatus(true); // 새로운 할 일은 목록에 보이도록 status를 true로 설정
        todoRepository.save(todoRequest);
    }

    @PutMapping("/todos/{id}")
    public void updateTodoStatus(@PathVariable Long id, @RequestBody Todo todoRequest) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));
        todo.setCompleted(todoRequest.isCompleted());
        todoRepository.save(todo);
    }

    @DeleteMapping("/todos/{id}")
    public void deleteTodo(@PathVariable Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));
        todo.setStatus(false); // 삭제된 할 일은 목록에 보이지 않도록 status를 false로 설정
        todoRepository.save(todo);
    }
}

class TodoRequest {
    private String text;
    private boolean completed;

    public TodoRequest(String text, boolean completed) {
        this.text = text;
        this.completed = completed;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
