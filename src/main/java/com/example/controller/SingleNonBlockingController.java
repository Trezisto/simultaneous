package com.example.controller;

import com.example.service.SlowService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;

@RestController
public class SingleNonBlockingController {

    private final SlowService service;
    private CompletableFuture<Void> technicalDebtFuture;

    public SingleNonBlockingController(SlowService service) {
        this.service = service;
    }

    @Async
    @GetMapping("/meaning")
    public CompletableFuture<String> startProcess() {
        String response;
        System.out.println("\n================\n");
        System.out.println("Calling SingleNonBlockingController");

        if (technicalDebtFuture != null && !technicalDebtFuture.isDone()) {
            response = "Please be patient, the Job is not Done!";
        } else {
            technicalDebtFuture = service.performHardwork();
            Runnable delegatedWork = () -> {
                try {
                    technicalDebtFuture.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            };
            new Thread(delegatedWork).start();
            response = "Started the hardwork!";
        }
        System.out.println("END: " + response);
        return CompletableFuture.completedFuture(response);
    }
}
