package com.ark.zoomapiclient.controller;

import com.ark.zoomapiclient.service.ZoomApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZoomController {

    @Autowired
    private ZoomApiService zoomApiService;

    @GetMapping("/my-meetings")
    public ResponseEntity<String> getAllMeetings() {
        return zoomApiService.getAllMeetings();
    }

    @GetMapping("/service-check")
    public String serviceCheck() {
        return "This is ping-pong from ZoomapiclientApllication";
    }

}
