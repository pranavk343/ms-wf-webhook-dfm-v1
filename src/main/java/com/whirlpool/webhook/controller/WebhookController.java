package com.whirlpool.webhook.controller;

import com.whirlpool.webhook.dto.WebhookRequestDto;
import com.whirlpool.webhook.dto.WebhookResponseDto;
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
    public ResponseEntity<String> getNotification(@RequestBody WebhookRequestDto request, @RequestHeader("payload_signature") String payload_signature) throws IOException
    {
        WebhookResponseDto responseEntity =webhookService.callNotificationFunction(request);
        if(responseEntity.getResult().equals("Success")) {
            return new ResponseEntity<String>(responseEntity.getResult(), HttpStatus.OK);
        }else {
            return new ResponseEntity<String>(responseEntity.getResult(), HttpStatus.BAD_REQUEST);
        }
    }
}
