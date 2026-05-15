package edu.innotech.unittest;

import java.util.List;

public class Tests {

    public void marksInRange() {
        List<Integer> lst = List.of(2, 3, 4, 5);

        Student stud = new Student("Ivan");

        stud.addMark(lst.get(0));
        stud.addMark(lst.get(1));
        stud.addMark(lst.get(2));
        stud.addMark(lst.get(3));

        if (!stud.getMark().equals(lst)) {
            throw new RuntimeException("test error");
        }
    }

    public void markNotInRange() {
        List<Integer> lst = List.of(0, 1, 6, 7);

        Student stud = new Student("Vasil");

        try {
            stud.addMark(lst.get(0));
            stud.addMark(lst.get(1));
            stud.addMark(lst.get(2));
            stud.addMark(lst.get(3));
        } catch (IllegalArgumentException e) {
            return;
        }

        throw new RuntimeException("test error");
    }
}


