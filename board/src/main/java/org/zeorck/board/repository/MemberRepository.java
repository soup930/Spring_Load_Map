package org.zeorck.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zeorck.board.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
}
