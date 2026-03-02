package src.ru.inno.lesson;

public class My2Class_4_comment {
    // Два приватных поля типа int
    private int a;
    private int b;

    // КОНСТРУКТОР КЛАССА, с параметрами.
    // КОНСТРУКТОР КЛАССА выполняет инициализацию полей
    public My2Class_4ass(int a, int b) {
        this.a = a; // Инициализация поля a значением параметра a
        this.b = b; // Инициализация поля b значением параметра b
    }

    //  Геттер для поля a
    //  get + ИмяПоля (с большой буквы)
    //  return прекращает ТОЛЬКО метод getA()
    public int getA() {
        return a;           // возвращаем значение поля a
    }

    //  Устанавливаем новое значение
    //  Метод завершается автоматически, можно и не писать return;
    //  Сеттер для поля a
    //  set + ИмяПоля (с большой буквы)
    //  При вызове: obj.setA(10). this.a - поле объекта obj становится равно 10
    public void setA(int a) {
        this.a = a;         // this.a - поле объекта, a - параметр метода
    }

    // Геттер для поля b
    public int getB() {
        return b;
    }

    // Сеттер для поля b
    public void setB(int b) {
        this.b = b;
    }

    // Метод для сложения полей класса
    public int sum() {
        return a + b;
    }

}
