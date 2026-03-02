public class My1Class_3 {                           //описание класса с именем
    public static void main(String... args) {       //точка входа, метод main, с жёстко заданной сигнатурой.
            System.out.println("Hello World");      //функция класса PrintStream, по статическим полем out класса System
            for (int i = 0; i < args.length - 1; i++) { //
                System.out.println(args[i] + " - " + "changed to = " + args[i+1]);
        }
    }
}
