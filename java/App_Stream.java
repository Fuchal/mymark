package com.example.fu;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Getter;
import lombok.ToString;

/**
 * java stream学习的一些例子
 *
 */
@ToString()
@Getter
class Student {
    private int id;
    private String name;
    private String sex;
    private int age;
    private int score;

    Student(int id, String name, String sex, int age, int score) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.score = score;
    }
}

public class App_Stream {
    public static void main(String[] args) {
        Student[] stus = {
                new Student(1, "张三", "男", 23, 100),
                new Student(2, "李四", "女", 31, 121),
                new Student(3, "王五", "男", 28, 98),
                new Student(4, "老六", "女", 46, 75),
                new Student(5, "陈七", "女", 56, 66)
        };
        Stream<Student> stmstu = Stream.of(stus);
        stmstu.forEach(System.out::println);

        System.out.println("res2: 从0开始，步长为2，生成一个x+3的集合list");
        List<Integer> res2 = Stream.iterate(0, x -> x + 3).skip(2).limit(10).collect(Collectors.toList());
        String stream2 = res2.stream().map(x -> x.toString()).collect(Collectors.joining(", "));
        System.out.println(stream2);

        System.out.println("res3: 随时生成0-20的随机数，返回前10集合list");
        List<Integer> res3 = Stream.generate(() -> Math.random() * 20).map(x -> x.intValue()).distinct().limit(10).collect(Collectors.toList());
        String stream3 = res3.stream().map(x -> x.toString()).collect(Collectors.joining(", "));
        System.out.println(stream3);

        System.out.println("合并res2 || res3 并去重");
        Stream<Integer> concat = Stream.concat(res2.stream(), res3.stream()).distinct();
        System.out.println(concat.sorted(Integer::compareTo).map(x -> x.toString()).collect(Collectors.joining(", ")));

        System.out.println("差值res2 - res3");
        Stream<Integer> substr = res2.stream().filter(x -> res3.contains(x));
        System.out.println(substr.map(x -> x.toString()).collect(Collectors.joining(", ")));

        System.out.println("过滤学生分数大等于100的");
        List<Student> stream4 = Stream.of(stus).filter(x -> x.getScore() >= 100).collect(Collectors.toList());
        stream4.forEach(System.out::println);
        Long stu_cnt = Stream.of(stus).count();
        int stu_max = Stream.of(stus).max(Comparator.comparingInt(Student::getScore)).orElse(stus[0]).getScore();
        int stu_min = Stream.of(stus).min(Comparator.comparingInt(Student::getScore)).orElse(stus[0]).getScore();
        System.out.println("stus: count = " + stu_cnt + " max = " + stu_max + " min = " + stu_min);
        Stream<Student> stream5 = Stream.of(stus).sorted(Comparator.comparingInt(Student::getScore).reversed());
        stream5.forEach(System.out::println);
        Optional<Integer> optVal = Stream.of(stus).map(x -> x.getScore()).reduce((x, y) -> x + y);
        System.out.println("stus score sum = " + optVal.orElse(0));
        Double res = Stream.of(stus).collect(Collectors.averagingInt(Student::getScore));
        System.out.println("stus score avg = " + res);

        Map<Object, List<Student>> mapRes = Stream.of(stus).collect(Collectors.groupingBy(x -> x.getSex()));
        mapRes.forEach((x, y) -> {
            System.out.println(x);
            y.forEach(data -> System.out.print(data + " | "));
            System.out.println();
        });
    }
}