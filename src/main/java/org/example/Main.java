package org.example;


import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class Main {
    static BlockingQueue<String> blockingQueueA = new ArrayBlockingQueue<>(100);
    static BlockingQueue<String> blockingQueueB = new ArrayBlockingQueue<>(100);
    static BlockingQueue<String> blockingQueueC = new ArrayBlockingQueue<>(100);
    static int textLength = 10_000;
    static int numberOfTexts = 100;
    static int maxA = 0;
    static int maxB = 0;
    static int maxC = 0;

    public static void main(String[] args) {
        System.out.println("Поехали");
        new Thread(() -> {//заполняем очереди

            for (int i = 0; i < numberOfTexts; i++) {
                String text = generateText("abc", textLength);
                try {
                    blockingQueueA.put(text);
                    blockingQueueB.put(text);
                    blockingQueueC.put(text);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(Thread.currentThread().getName() + ":PUT finished");
        }).start();
        new Thread(() -> {//счетчик а

            int maxCurrent = bqCount(blockingQueueA, 'a');
            if (maxCurrent > maxA) {
                maxA = maxCurrent;
            }
            System.out.println("maxA = " + maxA);
            System.out.println(Thread.currentThread().getName() + ":count A finished");
        }).start();
        new Thread(() -> {//счетчик b

            int maxCurrent = bqCount(blockingQueueA, 'b');
            if (maxCurrent > maxB) {
                maxB = maxCurrent;
            }
            System.out.println("maxB = " + maxB);
            System.out.println(Thread.currentThread().getName() + ":count B finished");

        }).start();
        new Thread(() -> {//счетчик c

            int maxCurrent = bqCount(blockingQueueA, 'c');
            if (maxCurrent > maxC) {
                maxC = maxCurrent;
            }
            System.out.println("maxC = " + maxC);
            System.out.println(Thread.currentThread().getName() + ":count C finished");
        }).start();


    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    public static int countLetter(String inputString, char charForCount) {
        int result = 0;
        for (int i = 0; i < inputString.length(); i++) {
            if (inputString.charAt(i) == charForCount) {
                result++;
            }

        }
        return result;
    }
    public static int bqCount (BlockingQueue blockingQueue, char charForCount) {
        int maxCurrent = 0;
        while (blockingQueue.iterator().hasNext()) {
            String text;
            try {
                text = (String) blockingQueue.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            maxCurrent = countLetter(text, charForCount);
        }
        return maxCurrent;
    }
}