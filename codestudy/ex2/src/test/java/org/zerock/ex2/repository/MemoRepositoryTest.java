package org.zerock.ex2.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.ex2.entity.Memo;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemoRepositoryTest {

    @Autowired
    MemoRepository memoRepository;

    //@Test
    public void testClass(){

        System.out.println(memoRepository.getClass().getName());

    }

    //@Test
    public void testInsertDummies(){

        IntStream.rangeClosed(1,100).forEach(i -> {
            Memo memo = Memo.builder().memoText("Sample..." + i).build();
            memoRepository.save(memo);
        });
    }

    @Transactional
    //@Test
    public void testSelect(){

        // 데이터베이스에 존재하는 mno
        Long mno = 100L;

        Optional<Memo> result = memoRepository.findById(mno);

        System.out.println("=====================================");

        if (result.isPresent()) {
            Memo memo = result.get();
            System.out.println("memo = " + memo);
        }

    }


    //@Test
    public void testUpdate() {

        Memo memo = Memo.builder().mno(100L).memoText("Update Test").build();

        System.out.println(memoRepository.save(memo));

    }

    //@Test
    public void testDelete() {

        Long mno = 100L;

        memoRepository.deleteById(mno);
    }

    //@Test
    public void testPageDefault() {

        // 1페이지 10
        Pageable pageable = PageRequest.of(0, 10);

        Page<Memo> result = memoRepository.findAll(pageable);

        System.out.println("result = " + result);

        System.out.println("---------------------------------------");

        for (Memo memo : result.getContent()) {
            System.out.println(memo);
        }

        System.out.println("total pages : " + result.getTotalPages());

        System.out.println("total count : " + result.getTotalElements());

        System.out.println("page number : " + result.getNumber());

        System.out.println("page size : " + result.getSize());

        System.out.println("has next page ? : " + result.hasNext());

        System.out.println("first page ? : " + result.isFirst());

    }

    //@Test
    public void testSort() {

        Sort sort1 = Sort.by("mno").descending();

        PageRequest pageable = PageRequest.of(0, 10, sort1);

        Page<Memo> result = memoRepository.findAll(pageable);

        result.get().forEach(memo -> {
            System.out.println("memo = " + memo);
        });
    }

    //@Test
    public void testQueryMethods() {

        List<Memo> list = memoRepository.findByMnoBetweenOrderByMnoDesc(70L, 80L);

        for (Memo memo :
                list) {
            System.out.println("memo = " + memo);
        }
    }

    @Test
    public void testQueryMethodWithPageable() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());

        Page<Memo> result = memoRepository.findByMnoBetween(10L, 50L, pageable);

        result.get().forEach(memo -> System.out.println(memo));
    }


}