package edu.innotech.unittest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class Tests {

    @Test
        //Аннотация для Junit
    void marksInRange() {
        List<Integer> lst = List.of(2, 3, 4, 5);
        Student stud = new Student("Ivan");
        stud.addMark(lst.get(0));
        stud.addMark(lst.get(1));
        stud.addMark(lst.get(2));
        stud.addMark(lst.get(3));
        Assertions.assertEquals(stud.getMark(), lst);
    }

    @Test
        //Аннотация для Junit
    void markNotInRange() {
        Student stud = new Student("Vasil");

        assertThrows(IllegalArgumentException.class, () -> stud.addMark(0));
        assertThrows(IllegalArgumentException.class, () -> stud.addMark(1));
        assertThrows(IllegalArgumentException.class, () -> {
            stud.addMark(6);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            stud.addMark(7);
        });
    }
}