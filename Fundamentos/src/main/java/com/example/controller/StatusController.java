package com.example.controller;

import com.example.model.Status;
import com.example.service.StatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/status")
public class StatusController {

    @Autowired
    private StatusService statusService;

    @GetMapping
    public ResponseEntity<List<Status>> listStatus(){
        List<Status> status = statusService.getStatus();
        if(status.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(status);
    }
}
