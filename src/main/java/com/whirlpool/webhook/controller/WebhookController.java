package com.whirlpool.webhook.controller;

import com.whirlpool.webhook.dto.WebhookRequestDto;
import com.whirlpool.webhook.dto.WebhookResponseDto;
import org.owasp.encoder.Encode;
import org.slf4j.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class WebhookController {
    Logger logger = LoggerFactory.getLogger(WebhookController.class);

    WebhookRequestDto requestDto = null;

//    @RequestMapping(value = "/WF/Webhook/Notification/", method = RequestMethod.POST)
//    public ResponseEntity<WebhookResponseDto> sendNotifcation(){
//        ResponseEntity<String> response = null;
//        return new ResponseEntity<>(Encode.forCDATA("Body"), response.getHeaders(), response.getStatusCode());
//    }

    @PostMapping(value = "/WF/webHookNotification")
    public ResponseEntity<String> getNotification(@RequestBody WebhookRequestDto request) throws IOException
    {

        System.out.println("request="+ request.toString());
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
