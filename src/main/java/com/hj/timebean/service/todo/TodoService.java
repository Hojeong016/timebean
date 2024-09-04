package com.hj.timebean.service.todo;

import com.hj.timebean.entity.Todo;

import java.util.List;

public interface TodoService {
    // 해당 memberId인 todoList 가져오기
    public List<Todo> findByMemberId(Long memberId);
    // status에 따라 todoList 가져오기
    public List<Todo> findByStatus(boolean status);
    // 해당 memberId로 status가 true인 todoList 가져오기
    public List<Todo> findByMemberIdAndStatus(Long memberId, boolean status);
}
