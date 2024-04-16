package com.whirlpool.webhook.dto;

import lombok.Data;

@Data
public class WebhookResponseDto {
    private String status_code="";
    private String status_message="";
}
