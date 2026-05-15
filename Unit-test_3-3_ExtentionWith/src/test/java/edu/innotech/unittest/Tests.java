package edu.innotech.unittest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(EducationExtention.class)
public class Tests {
    @RepeatedTest(value = 4,name="корректные оценки добавляются в список оценок")
    void marksInRange(RepetitionInfo repetitionInfo) {
            Student stud = new Student("Ivan");
            int num=repetitionInfo.getCurrentRepetition()+1;
            stud.addMark(num);
            Assertions.assertEquals(stud.getMark().get(0), num);
    }

    @ParameterizedTest(name="добавление неверных оценок, - возвращает исключение")
    @MethodSource("edu.innotech.unittest.MarksGenerator#ints")
    void markNotInRange(int x) {
        Student stud = new Student("Vasil");
        Assertions.assertThrows(IllegalArgumentException.class, ()->stud.addMark(x));
    }
}

class EducationExtention implements BeforeEachCallback{

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        System.out.println("test extentions");
    };
}