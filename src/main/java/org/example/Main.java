package org.example;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class Main {
    static BlockingQueue <String> blockingQueueA = new ArrayBlockingQueue<>(100);
    static BlockingQueue <String> blockingQueueB = new ArrayBlockingQueue<>(100);
    static BlockingQueue <String> blockingQueueC = new ArrayBlockingQueue<>(100);
    public static void main(String[] args) {
        System.out.println("Hello world!");


    }
}