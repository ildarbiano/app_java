package edu.innotech.unittest;

public class Starter
{
    public static void main( String[] args ) {
        System.out.println("------метка:");
        Student st= new Student("Nikola");
        st.addMark(3);
        System.out.println(st);
    }
}
