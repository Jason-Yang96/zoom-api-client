package com.ark.zoomapiclient.controller;

import com.ark.zoomapiclient.service.ZoomApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ZoomController {

    @Autowired
    private ZoomApiService zoomApiService;

    @GetMapping("/participants")
    public ResponseEntity<String> getAllParticipants() {
        return zoomApiService.getAllParticipants();
    }

    @GetMapping("/service-check")
    public String serviceCheck() {
        return "This is ping-pong from ZoomapiclientApllication";
    }

}
