package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
    @Rollback(false)
    public void saveMember() throws Exception {
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long saveId = memberService.join(member);

        //then
        em.flush();
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test(expected = IllegalStateException.class)
    public void duplicateMemberException() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("Kim1");

        Member member2 = new Member();
        member2.setName("Kim1");

        //when
        memberService.join(member1);
        try {
            memberService.join(member2);
        } catch (IllegalStateException e) {
            return;
        }

        //then
        Assert.fail("예외가 발생해야 한다.");

    }

}