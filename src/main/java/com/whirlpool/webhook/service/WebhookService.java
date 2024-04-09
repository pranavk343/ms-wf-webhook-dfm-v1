package com.whirlpool.webhook.service;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoFunction;
import com.whirlpool.webhook.common.Helper;
import com.whirlpool.webhook.dto.WebhookRequestDto;
import com.whirlpool.webhook.dto.WebhookResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.whirlpool.webhook.common.SAPConfigLoader.getRfcDestination;
@Component
public class WebhookService {

    @Autowired
    private Helper helper;

    public String callNotificationFunction(WebhookRequestDto request)
    {
        WebhookResponseDto webhookResponseDto = new WebhookResponseDto();
        try
        {
            System.out.println("getRfcDestination()="+getRfcDestination());
            JCoDestination destination = helper.getJCoDestination(getRfcDestination());
            JCoFunction function = destination.getRepository().getFunction("Z_NSD_WELLS_WEBHOOK_NTFCN");
            populateImportParameterList(request, function);
            function.execute(destination);
            webhookResponseDto.setResult(function.getExportParameterList().getString("status"));
            System.out.println("Status:"+function.getExportParameterList().getString("status"));
        }
        catch( Exception e)
        {
            e.printStackTrace();
        }
        finally {

        }
        return "";
    }
    private static void populateImportParameterList(WebhookRequestDto input, JCoFunction function)
    {
        function.getImportParameterList().setValue("cpu_id", input.getData().getCpu_id());
        function.getImportParameterList().setValue("mfg_code", input.getData().getMfg_code());
        function.getImportParameterList().setValue("customer_dealer_number", input.getData().getCustomer_dealer_number());
        function.getImportParameterList().setValue("wells_fargo_dealer_number", input.getData().getWells_fargo_dealer_number());
        function.getImportParameterList().setValue("dealer_name", input.getData().getDealer_name());
        function.getImportParameterList().setValue("approval_number", input.getData().getApproval_number());
        function.getImportParameterList().setValue("requested_amount", input.getData().getRequested_amount());
        function.getImportParameterList().setValue("approved_amount", input.getData().getApproved_amount());
        function.getImportParameterList().setValue("approval_status", input.getData().getApproval_status());
        function.getImportParameterList().setValue("pending_approval_reference_num", input.getData().getPending_approval_reference_number());
        function.getImportParameterList().setValue("purchase_order_number", input.getData().getPurchase_order_number());
        function.getImportParameterList().setValue("sales_order_number", input.getData().getSales_order_number());
        function.getImportParameterList().setValue("approval_datetime", input.getData().getApproval_datetime());
    }
}
