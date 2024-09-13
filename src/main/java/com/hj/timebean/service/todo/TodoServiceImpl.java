package com.hj.timebean.service.todo;

import com.hj.timebean.dto.TodoDTO;
import com.hj.timebean.entity.Member;
import com.hj.timebean.entity.Todo;
import com.hj.timebean.repository.MemberRepository;
import com.hj.timebean.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService{
    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public TodoServiceImpl(TodoRepository todoRepository, MemberRepository memberRepository) {
        this.todoRepository = todoRepository;
        this.memberRepository = memberRepository;
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
    public List<TodoDTO> findByMemberIdAndStatus(Long memberId, boolean status) {
        return todoRepository.findByMemberIdAndStatus(memberId, status);
    }

    @Override
    public void saveTodo(TodoDTO todoDTO) {
        // TodoDTO를 엔티티로 변환
        Todo todo = new Todo();
        todo.setText(todoDTO.getText());
        todo.setCompleted(false);
        todo.setStatus(true);
        todo.setRecordedDate(LocalDate.now());

        // Member ID를 통해 Member 엔티티를 조회하고 설정
        Member member = memberRepository.findById(todoDTO.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
        todo.setMember(member);

        // 엔티티 저장
        todoRepository.save(todo);
    }
}
