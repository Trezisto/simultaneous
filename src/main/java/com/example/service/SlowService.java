package com.example.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

@Service
public class SlowService {
    private int i = 0;


    @Async
    public CompletableFuture<Void> performHardwork() {
        Runnable r = () -> {
            try {
                System.out.println("Running inside SlowService...");
                Thread.currentThread().sleep(5_000);
                System.out.println("Result is " + i++ + "!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        return CompletableFuture.runAsync(r);
    }
}
