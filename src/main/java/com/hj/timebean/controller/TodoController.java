package com.hj.timebean.controller;

import com.hj.timebean.auth.PrincipalDetails;
import com.hj.timebean.entity.Todo;
import com.hj.timebean.repository.TodoRepository;
import com.hj.timebean.service.todo.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
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
    public List<Todo> getTodos(Authentication authentication) {
        long memberId = 0L;
        List<Todo> todoList = new ArrayList<>();

        if (authentication != null) {
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            memberId = principalDetails.getMember().getId();
            todoList = todoRepository.findByMemberIdAndStatus(memberId, true);
        }
        System.out.println("todolist ::: " + todoList);
        return  todoList;
    }

    @PostMapping("/todos")
    public void addTodo(@RequestBody Todo todoRequest, Authentication authentication) {

        System.out.println(todoRequest);
        if (authentication != null) {
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            Long memberId = principalDetails.getMember().getId();
            todoRequest.setMember(principalDetails.getMember());
        }

        todoRequest.setStatus(true); // 새로운 할 일은 목록에 보이도록 status를 true로 설정

        todoRepository.save(todoRequest);
    }

//    @PostMapping("/todos")
//    public void addTodo(@RequestBody Todo todoRequest) {
//        System.out.println("todo 추가");
//        todoRequest.setStatus(true); // 기본 값 설정
//        todoRepository.save(todoRequest);
//    }

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
