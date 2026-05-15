package edu.innotech.unittest;

public class StudentRepositiryMock implements StudentRepo {  // ← добавьте implements
    public int getRaitingForGradeSum(int sum) {
        return 10;
    }
    public long count() {return 0;}
    public void delete(Student entity) {}
    public void deleteAll(Iterable<Student> entities) {}  // ← исправлено
    public Iterable<Student> findAll() {return null;}  // ← исправлено
    public Student save(Student entity) {return null;}  // ← исправлено
    public Iterable<Student> saveAll(Iterable<Student> entities) {return null;}  // ← исправлено
}
