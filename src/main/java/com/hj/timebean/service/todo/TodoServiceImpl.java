package com.hj.timebean.service.todo;

import com.hj.timebean.entity.Todo;
import com.hj.timebean.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService{
    private final TodoRepository todoRepository;

    @Autowired
    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<Todo> findByMemberId(Long memberId) {
        return todoRepository.findByMemberId(memberId);
    }

    @Override
    public List<Todo> findByStatus(boolean status) {
        return todoRepository.findByStatus(status);
    }

    @Override
    public List<Todo> findByMemberIdAndStatus(Long memberId, boolean status) {
        return todoRepository.findByMemberIdAndStatus(memberId, status);
    }
}
