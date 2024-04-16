package com.whirlpool;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoException;
import com.whirlpool.webhook.common.Helper;
import com.whirlpool.webhook.controller.WebhookController;
import com.whirlpool.webhook.dto.NotificationDataDto;
import com.whirlpool.webhook.dto.WebhookRequestDto;
import com.whirlpool.webhook.dto.WebhookResponseDto;
import com.whirlpool.webhook.service.WebhookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(WebhookController.class)
public class WebhookControllerTests {

    @Autowired
    private WebhookController controller;

    @MockBean
    private WebhookService service;

    @Autowired
    public MockMvc mockMvc;

    @Test
    void testGetNotification() throws Exception {
        String jsonString="{\n" +
                "    \"notification_id\": \"123456789\",\n" +
                "    \"additional_data_available\": false,\n" +
                "    \"create_datetime\": \"2021-09-22T14:49:41+0000\",\n" +
                "    \"event\": {\n" +
                "      \"name\": \"distribution-finance.purchase.approval-status-changed.v1\",\n" +
                "      \"description\": \"Event to notify manufacturer of purchase approval status changed.\",\n" +
                "      \"category\": \"Distribution-Finance-Manufacturer-Webhooks\"\n" +
                "    },\n" +
                "    \"data\": {\n" +
                "      \"cpu_id\": \"0032\",\n" +
                "      \"mfg_code\": \"W\",\n" +
                "      \"customer_dealer_number\": \"001282771\",\n" +
                "      \"wells_fargo_dealer_number\": 79709,\n" +
                "      \"dealer_name\": \"TIGHTEN FIREARM ?O? LLC\",\n" +
                "      \"approval_number\": \"102421\",\n" +
                "      \"requested_amount\": 50001.99,\n" +
                "      \"approved_amount\": 50001.99,\n" +
                "      \"approval_status\": \"APPROVED\",\n" +
                "      \"pending_approval_reference_number\": 93,\n" +
                "      \"purchase_order_number\": \"BACD\",\n" +
                "      \"sales_order_number\": \"ABCDEFG\",\n" +
                "      \"approval_datetime\": \"2023-03-22T18:00:00Z\"\n" +
                "    },\n" +
                "    \"links\": [\n" +
                "      {\n" +
                "        \"method\": \"GET\",\n" +
                "        \"rel\": \"SELF\",\n" +
                "        \"href\": \"https://api-certification.wellsfargo.com/webhook-services/v1/notifications/{notification_id}\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"method\": \"GET\",\n" +
                "        \"rel\": \"GUIDE\",\n" +
                "        \"href\": \"https://developer.wellsfargo.com/portal/documentation/webhooks/user-guide\"\n" +
                "      }]\n" +
                "}";

        WebhookResponseDto output = new WebhookResponseDto();

        Mockito.doReturn(output).when(service).callNotificationFunction(Mockito.any());

        mockMvc.perform(
                        post("/WF/webHookNotification")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonString)

                ).andExpect(
                        MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
                .andDo(MockMvcResultHandlers.print());
    }


}
