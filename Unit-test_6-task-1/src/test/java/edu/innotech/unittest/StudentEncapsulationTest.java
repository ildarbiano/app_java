package edu.innotech.unittest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentEncapsulationTest {

    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student("Nikola");
        student.addGrade(4);
        student.addGrade(5);
        student.addGrade(3);
    }

    // ========== ТЕСТЫ НА НЕИЗМЕНЯЕМОСТЬ ВОЗВРАЩАЕМОГО СПИСКА ==========

    @Test
    void getGradesShouldReturnUnmodifiableList() {
        List<Integer> grades = student.getGrades();

        assertThrows(UnsupportedOperationException.class, () -> grades.add(2));
        assertThrows(UnsupportedOperationException.class, () -> grades.remove(0));
        assertThrows(UnsupportedOperationException.class, () -> grades.clear());

        assertEquals(3, student.getGrades().size());
    }

    @Test
    void getGradesShouldReturnUnmodifiableListForSetOperation() {
        List<Integer> grades = student.getGrades();
        assertThrows(UnsupportedOperationException.class, () -> grades.set(0, 2));
    }

    @Test
    void getGradesShouldReturnUnmodifiableListForAddAllOperation() {
        List<Integer> grades = student.getGrades();
        assertThrows(UnsupportedOperationException.class,
                () -> grades.addAll(List.of(4, 5)));
    }

    // ========== ТЕСТЫ НА НЕВОЗМОЖНОСТЬ ОБОЙТИ ВАЛИДАЦИЮ ==========

    @Test
    void shouldNotAllowToAddInvalidGradeViaDirectListModification() {
        List<Integer> grades = student.getGrades();

        assertThrows(UnsupportedOperationException.class, () -> grades.add(1));
        assertThrows(UnsupportedOperationException.class, () -> grades.add(6));

        assertEquals(List.of(4, 5, 3), student.getGrades());
    }

    @Test
    void shouldNotAllowToRemoveGradesViaDirectListModification() {
        List<Integer> grades = student.getGrades();
        assertThrows(UnsupportedOperationException.class, () -> grades.remove(0));
        assertEquals(3, student.getGrades().size());
    }

    // ========== ТЕСТЫ НА ПРАВИЛЬНОСТЬ РАБОТЫ addGrade ==========

    @Test
    void addGradeShouldWorkCorrectly() {
        student.addGrade(5);
        assertEquals(4, student.getGrades().size());
        assertEquals(5, student.getGrades().get(3));
    }

    @Test
    void addGradeShouldValidateInput() {
        assertThrows(IllegalArgumentException.class, () -> student.addGrade(1));
        assertThrows(IllegalArgumentException.class, () -> student.addGrade(6));
        assertThrows(IllegalArgumentException.class, () -> student.addGrade(-1));
        assertThrows(IllegalArgumentException.class, () -> student.addGrade(10));

        assertEquals(3, student.getGrades().size());
    }

    @Test
    void addGradeShouldAcceptValidGrades() {
        assertDoesNotThrow(() -> student.addGrade(2));
        assertDoesNotThrow(() -> student.addGrade(3));
        assertDoesNotThrow(() -> student.addGrade(4));
        assertDoesNotThrow(() -> student.addGrade(5));

        assertEquals(7, student.getGrades().size());
    }

    // ========== НОВЫЕ ТЕСТЫ ДЛЯ setName, getName, toString ==========

    @Test
    void testSetName() {
        student.setName("Alexey");
        assertEquals("Alexey", student.getName());
    }

    @Test
    void testGetName() {
        assertEquals("Nikola", student.getName());
    }

    @Test
    void testToString() {
        String expected = "Student{name=Nikola, marks=[4, 5, 3]}";
        assertEquals(expected, student.toString());
    }

    @Test
    void testToStringWithEmptyGrades() {
        Student emptyStudent = new Student("Empty");
        String expected = "Student{name=Empty, marks=[]}";
        assertEquals(expected, emptyStudent.toString());
    }

    // ========== ТЕСТ ДЛЯ getGradesCopy ==========

    @Test
    void getGradesCopyShouldReturnIndependentCopy() {
        // Получаем копию через метод getGradesCopy()
        List<Integer> copy = student.getGradesCopy();

        // Проверяем, что копия совпадает с оригиналом
        assertEquals(student.getGrades(), copy);

        // Изменяем копию
        copy.add(2);

        // Оригинал не должен измениться
        assertEquals(3, student.getGrades().size());
        assertEquals(List.of(4, 5, 3), student.getGrades());

        // В копии теперь 4 элемента
        assertEquals(4, copy.size());

        // Копия - это другой объект
        assertNotSame(student.getGrades(), copy);
    }

    // ========== ТЕСТ НА РЕФЛЕКСИЮ ==========


}