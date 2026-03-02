package lesson_5.src.main.ru.inno.lesson;

public class My2Class_4 {

    private int a;
    private int b;


    public My2Class_4(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }


    public void setA(int a) {
        this.a = a;
    }


    public int getB() {
        return b;
    }

    // Сеттер для поля b
    public void setB(int b) {
        this.b = b;
    }

     public int sum() {
        return a + b;
    }

}
