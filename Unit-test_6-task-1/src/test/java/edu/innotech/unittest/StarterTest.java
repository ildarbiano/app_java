package edu.innotech.unittest;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StarterTest {

    @Test
    void mainShouldWorkCorrectly() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Starter.main(new String[]{});

        String output = outContent.toString();
        assertTrue(output.contains("------метка:"));
        assertTrue(output.contains("Student{name=Nikola, marks=[3]}"));
    }

    @Test
    void encapsulationShouldWorkInStarterScenario() {
        Student st = new Student("Nikola");
        st.addGrade(3);

        // Пытаемся нарушить инкапсуляцию
        List<Integer> grades = st.getGrades();

        // Должны получить исключение при попытке модификации
        assertThrows(UnsupportedOperationException.class, () -> grades.add(2));

        // Исходный объект не должен измениться
        assertEquals("Student{name=Nikola, marks=[3]}", st.toString());
    }
}