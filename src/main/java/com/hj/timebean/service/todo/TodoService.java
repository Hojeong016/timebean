package com.hj.timebean.service.todo;

import com.hj.timebean.dto.TodoDTO;
import com.hj.timebean.entity.Todo;

import java.util.List;

public interface TodoService {
    // 해당 memberId인 todoList 가져오기
    public List<Todo> findByMemberId(Long memberId);
    // status에 따라 todoList 가져오기
    public List<Todo> findByStatus(boolean status);
    // 해당 memberId로 status가 true인 todoList 가져오기
    public List<TodoDTO> findByMemberIdAndStatus(Long memberId, boolean status);
    // DTO를 받아서 엔티티로 변환한 후 저장하는 메서드
    public void saveTodo(TodoDTO todoDTO);
}
