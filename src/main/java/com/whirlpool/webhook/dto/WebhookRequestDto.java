package com.whirlpool.webhook.dto;

import lombok.Data;

@Data
public class WebhookRequestDto {
    private String notification_id;
    private String additional_data_available;
    private String create_datetime;

    NotificationEventDto event;
    NotificationDataDto data;
    NotificationLinksDto links[];

}
