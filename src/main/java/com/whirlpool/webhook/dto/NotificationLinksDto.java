package com.whirlpool.webhook.dto;

import lombok.Data;

@Data
public class NotificationLinksDto
{
    private String method;
    private String rel;
    private String href;
}
