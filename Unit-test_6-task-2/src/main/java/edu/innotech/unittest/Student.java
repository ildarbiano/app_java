// Student.java - изменённая версия
package edu.innotech.unittest;

import lombok.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

@ToString
@EqualsAndHashCode
public class Student {

    @Getter
    @Setter
    private String name;
    private List<Integer> grades = new ArrayList<>();
    private static int mockPort = 5352; // Порт для тестов

    public Student(String name) {
        this.name = name;
    }

    // Для тестов - возможность изменить порт
    public static void setMockPort(int port) {
        mockPort = port;
    }

    public List<Integer> getGrades() {
        return new ArrayList<>(grades);
    }

    @SneakyThrows
    public void addGrade(int grade) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://localhost:" + mockPort + "/checkGrade?grade="+grade);
        CloseableHttpResponse httpResponse = httpClient.execute(request);
        HttpEntity entity = httpResponse.getEntity();
        String response = EntityUtils.toString(entity);
        System.out.println("Response for grade " + grade + ": " + response); // Отладка
        if(!Boolean.parseBoolean(response)){
            throw new IllegalArgumentException(grade + " is wrong grade");
        }
        grades.add(grade);
    }

    @SneakyThrows
    public int raiting() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        int sum = grades.stream().mapToInt(x->x).sum();
        HttpGet request = new HttpGet("http://localhost:" + mockPort + "/educ?sum="+sum);
        CloseableHttpResponse httpResponse = httpClient.execute(request);
        HttpEntity entity = httpResponse.getEntity();
        String response = EntityUtils.toString(entity);
        System.out.println("Response for sum " + sum + ": " + response); // Отладка
        return Integer.parseInt(response);
    }
}