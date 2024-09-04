package com.hj.timebean.repository;

import com.hj.timebean.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findByMemberId(Long memberId);

    List<Todo> findByStatus(boolean status);

    List<Todo> findByMemberIdAndStatus(Long memberId, boolean status);
}
