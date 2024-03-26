package org.example;


import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class Main {
    static BlockingQueue <String> blockingQueueA = new ArrayBlockingQueue<>(100);
    static BlockingQueue <String> blockingQueueB = new ArrayBlockingQueue<>(100);
    static BlockingQueue <String> blockingQueueC = new ArrayBlockingQueue<>(100);
    public static void main(String[] args) {
        System.out.println("Поехали");
        Random random = new Random();



    }
    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

}