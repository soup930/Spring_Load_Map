package org.zeorck.java.stream;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class StreamEx7 {

    public static void main(String[] args) {
        Student2[] stuArr = {
                new Student2("나자바", true, 1, 1, 300),
                new Student2("김자바", false, 1, 1, 300),
                new Student2("이자바", true, 1, 1, 300),
                new Student2("박자바", false, 1, 2, 300),
                new Student2("남자바", false, 1, 3, 300),
                new Student2("오자바", true, 1, 1, 300),
                new Student2("유자바", true, 1, 2, 300),
                new Student2("성자바", false, 1, 3, 300),
                new Student2("황자바", true, 1, 1, 300),

                new Student2("노자바", false, 2, 1, 300),
                new Student2("지자바", false, 2, 2, 300),
                new Student2("천자바", true, 2, 2, 300),
                new Student2("명자바", true, 2, 1, 300),
                new Student2("권자바", false, 2, 3, 300),
                new Student2("김자바", false, 2, 3, 300),
                new Student2("이자바", true, 2, 1, 300),
                new Student2("황자바", true, 2, 2, 300),
                new Student2("최자바", true, 2, 1, 300)
        };

        Map<Boolean, List<Student2>> stuBySex = Stream.of(stuArr)
                .collect(partitioningBy(Student2::isMale));

        List<Student2> maleStudent = stuBySex.get(true);
        List<Student2> femaleStudent2s = stuBySex.get(false);

        /*for (Student2 s : maleStudent)  System.out.println(s);
        for (Student2 s : femaleStudent2s)  System.out.println(s);*/

        System.out.println("%n2. 단순분할 + 통계 (성별 학생수)%n");
        Map<Boolean, Long> stuNumBySex = Stream.of(stuArr)
                .collect(partitioningBy(Student2::isMale, counting()));

        System.out.println("남학생 수 :" + stuNumBySex.get(true));
        System.out.println("여학생 수 :" + stuNumBySex.get(false));

        System.out.println("%n3. 단순분할 + 통계(성별 1등)%n");
        Map<Boolean, Optional<Student2>> topScopeBySex = Stream.of(stuArr)
                .collect(partitioningBy(Student2::isMale,
                        Collectors.maxBy(Comparator.comparingInt(Student2::getScore))
                ));

        System.out.println("남학생 1등 :" + topScopeBySex.get(true));
        System.out.println("여학생 1등 :" + topScopeBySex.get(false));

        Map<Boolean, Student2> topScopeBySex2 = Stream.of(stuArr)
                .collect(partitioningBy(Student2::isMale,
                        collectingAndThen(
                                maxBy(Comparator.comparingInt(Student2::getScore)) , Optional::get
                        )
                ));

        System.out.println("남학생 1등 :" + topScopeBySex2.get(true));
        System.out.println("여학생 1등 :" + topScopeBySex2.get(false));

        System.out.printf("%n4. 다중분할(성별 불합격자, 100점 이하) %n");

        Map<Boolean, Map<Boolean, List<Student2>>> failedStuBySex =
                Stream.of(stuArr).collect(partitioningBy(Student2::isMale,
                        partitioningBy(s -> s.getScore() <= 100))
                );
        List<Student2> failedMaleStu = failedStuBySex.get(true).get(true);
        List<Student2> failedFemaleStu = failedStuBySex.get(false).get(false);

        for (Student2 s : failedMaleStu)    System.out.println(s);
        for (Student2 s : failedFemaleStu)  System.out.println(s);
    }
}
