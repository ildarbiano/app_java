package edu.innotech.unittest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentEqualsHashCodeTest {

    private Student student;
    private Student sameStudent;
    private Student differentName;
    private Student differentGrades;

    @BeforeEach
    void setUp() {
        student = new Student("Nikola");
        student.addGrade(4);
        student.addGrade(5);

        sameStudent = new Student("Nikola");
        sameStudent.addGrade(4);
        sameStudent.addGrade(5);

        differentName = new Student("Petr");
        differentName.addGrade(4);
        differentName.addGrade(5);

        differentGrades = new Student("Nikola");
        differentGrades.addGrade(4);
        differentGrades.addGrade(3);
    }

    // ========== equals TESTS ==========

    @Test
    void equalsShouldReturnTrueForSameObject() {
        assertEquals(student, student);
    }

    @Test
    void equalsShouldReturnTrueForEqualStudents() {
        assertEquals(student, sameStudent);
    }

    @Test
    void equalsShouldReturnFalseForNull() {
        assertNotEquals(student, null);
    }

    @Test
    void equalsShouldReturnFalseForDifferentClass() {
        assertNotEquals(student, "string");
    }

    @Test
    void equalsShouldReturnFalseForDifferentNames() {
        assertNotEquals(student, differentName);
    }

    @Test
    void equalsShouldReturnFalseForDifferentGrades() {
        assertNotEquals(student, differentGrades);
    }

    @Test
    void equalsShouldBeSymmetric() {
        assertEquals(student, sameStudent);
        assertEquals(sameStudent, student);
    }

    // ========== hashCode TESTS ==========

    @Test
    void hashCodeShouldBeEqualForEqualObjects() {
        assertEquals(student.hashCode(), sameStudent.hashCode());
    }

    @Test
    void hashCodeShouldBeDifferentForDifferentNames() {
        assertNotEquals(student.hashCode(), differentName.hashCode());
    }

    @Test
    void hashCodeShouldBeDifferentForDifferentGrades() {
        assertNotEquals(student.hashCode(), differentGrades.hashCode());
    }

    @Test
    void hashCodeShouldBeConsistent() {
        int first = student.hashCode();
        int second = student.hashCode();
        int third = student.hashCode();

        assertEquals(first, second);
        assertEquals(first, third);
    }

    // ========== EDGE CASES ==========

    @Test
    void equalsWithEmptyGrades() {
        Student s1 = new Student("Test");
        Student s2 = new Student("Test");

        assertEquals(s1, s2);
    }

    @Test
    void equalsWithNullName() {
        Student s1 = new Student(null);
        Student s2 = new Student(null);

        assertEquals(s1, s2);

        Student s3 = new Student("Name");
        assertNotEquals(s1, s3);
    }
}