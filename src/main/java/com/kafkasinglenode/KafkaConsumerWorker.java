package com.kafkasinglenode;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class KafkaConsumerWorker implements Runnable {
    // request queue for worker threads
    private static BlockingQueue<String> requestQueue = new ArrayBlockingQueue<String>(100);
    // atomic counter
    private static final AtomicInteger pendingItems = new AtomicInteger();
    private String consumeWorkerId;

    public KafkaConsumerWorker(String consumeWorkerId) {
        super();
        System.out.println("Creating KafkaConsumerWorker for " + consumeWorkerId);
        this.consumeWorkerId = consumeWorkerId;
    }

    /**
     * @param item
     */
    public static void addItemsToQueue(String item) {
        pendingItems.incrementAndGet();
        try {
            requestQueue.put(item);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return
     */
    public static int getPendingItemsCount() {
        return pendingItems.get();
    }

    @Override
    public void run() {
        while (true) {
            try {
                String item = requestQueue.take();
                System.out.println("Worker " + consumeWorkerId
                        + " Processing : " + item);

                //Simulate some work
                Thread.sleep(100);

                //Decrement counter after all work is done
                pendingItems.decrementAndGet();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
