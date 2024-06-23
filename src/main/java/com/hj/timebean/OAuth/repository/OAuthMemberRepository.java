package com.hj.timebean.OAuth.repository;

import com.hj.timebean.OAuth.domain.OAuthMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OAuthMemberRepository extends JpaRepository<OAuthMember, Long> {
    Optional<OAuthMember> findByEmail(String email);
}
