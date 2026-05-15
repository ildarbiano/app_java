package edu.innotech.unittest;

import lombok.*;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

@ToString
@EqualsAndHashCode
public class Student {
    @Setter
    private StudentRepo repo;
    @Getter
    @Setter
    private String name;

    private List<Integer> marks = new ArrayList<>();

    public Student(String name) {
        this.name = name;
    }

    public void addMark(int mark) {
        if (mark < 2 || mark > 5) throw new IllegalArgumentException("wrong mark " + mark);
        marks.add(mark);
    }

    public List<Integer> getMark() {
        return marks;
    }

    @SneakyThrows
    public int raiting() {
        return repo.getRaitingForGradeSum(
                marks.stream()
                        .mapToInt(x -> x)
                        .sum()
        );
    }
}
