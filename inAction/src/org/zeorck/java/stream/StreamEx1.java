package org.zeorck.java.stream;

import java.util.Comparator;
import java.util.stream.Stream;

public class StreamEx1 {
    public static void main(String[] args) {
        Stream<Student> studenStream = Stream.of(
                new Student("이자바", 3, 300),
                new Student("김자바", 3, 200),
                new Student("박자바", 1, 100),
                new Student("정자바", 4, 150),
                new Student("나자바", 5, 400),
                new Student("현자바", 6, 450)
                );

        studenStream.sorted(Comparator.comparing(Student::getBan)   // 반별 정렬
            .thenComparing(Comparator.naturalOrder()))   // 기본 정렬
            .forEach(System.out::println);

    }
}
