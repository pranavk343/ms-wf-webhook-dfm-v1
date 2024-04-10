package com.whirlpool.webhook.service;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoFunction;
import com.whirlpool.webhook.common.Helper;
import com.whirlpool.webhook.dto.WebhookRequestDto;
import com.whirlpool.webhook.dto.WebhookResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static com.whirlpool.webhook.common.SAPConfigLoader.getRfcDestination;
@Component
public class WebhookService {

    @Autowired
    private Helper helper;

    public WebhookResponseDto callNotificationFunction(WebhookRequestDto request)
    {
        WebhookResponseDto webhookResponseDto = new WebhookResponseDto();
        try
        {
            System.out.println("getRfcDestination()="+getRfcDestination());
            JCoDestination destination = helper.getJCoDestination(getRfcDestination());
            JCoFunction function = destination.getRepository().getFunction("Z_NSD_WELLS_WEBHOOK_NTFCN");
            populateImportParameterList(request, function);
            function.execute(destination);
            webhookResponseDto.setResult(function.getExportParameterList().getString("STATUS"));
            System.out.println("Status:"+function.getExportParameterList().getString("STATUS"));
        }
        catch( Exception e)
        {
            e.printStackTrace();
        }
        finally {

        }
        return webhookResponseDto;
    }
    private static void populateImportParameterList(WebhookRequestDto input, JCoFunction function)
    {
        function.getImportParameterList().setValue("CPU_ID", input.getData().getCpu_id());
        function.getImportParameterList().setValue("MFG_CODE", input.getData().getMfg_code());
        function.getImportParameterList().setValue("CUSTOMER_DEALER_NUMBER", input.getData().getCustomer_dealer_number());
        function.getImportParameterList().setValue("WELLS_FARGO_DEALER_NUMBER", input.getData().getWells_fargo_dealer_number());
        function.getImportParameterList().setValue("DEALER_NAME", input.getData().getDealer_name());
        function.getImportParameterList().setValue("APPROVAL_NUMBER", input.getData().getApproval_number());
        function.getImportParameterList().setValue("REQUESTED_AMOUNT", input.getData().getRequested_amount());
        function.getImportParameterList().setValue("APPROVED_AMOUNT", input.getData().getApproved_amount());
        function.getImportParameterList().setValue("APPROVAL_STATUS", input.getData().getApproval_status());
        function.getImportParameterList().setValue("PENDING_APPROVAL_REFERENCE_NUM", input.getData().getPending_approval_reference_number());
        function.getImportParameterList().setValue("PURCHASE_ORDER_NUMBER", input.getData().getPurchase_order_number());
        function.getImportParameterList().setValue("SALES_ORDER_NUMBER", input.getData().getSales_order_number());
        function.getImportParameterList().setValue("APPROVAL_DATETIME", input.getData().getApproval_datetime());
    }
}
