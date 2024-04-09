package com.whirlpool.webhook.controller;

import com.whirlpool.webhook.dto.WebhookRequestDto;
import com.whirlpool.webhook.service.WebhookService;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class WebhookController {
    Logger logger = LoggerFactory.getLogger(WebhookController.class);

    @Autowired
    private WebhookService webhookService;


    @PostMapping(value = "/WF/webHookNotification")
    public ResponseEntity<String> getNotification(@RequestBody WebhookRequestDto request) throws IOException
    {

        System.out.println("request="+ request.toString());
        webhookService.callNotificationFunction(request);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
