package com.whirlpool.webhook.filter;

import com.whirlpool.webhook.common.WebhHookConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
public class MDCLoggingFilter extends OncePerRequestFilter {

    @Override
    public void doFilterInternal(HttpServletRequest request,
                                 HttpServletResponse response,
                                 FilterChain filterChain) throws ServletException, IOException {
        try {
            setInitialValuesInMDC(request);
            filterChain.doFilter(request,response);
        }finally {
            MDC.clear();
        }
    }

    private void setInitialValuesInMDC(HttpServletRequest request){
        final String correlationId = request.getHeader("x-messageid");
        MDC.put(WebhHookConstants.REQUEST,request.getQueryString());
        MDC.put(WebhHookConstants.RESPONSE,"");
        MDC.put(WebhHookConstants.CORRELATION_ID,correlationId);
        MDC.put(WebhHookConstants.IS_ERROR,"false");
        MDC.put(WebhHookConstants.ERROR_MESSAGE,"");
        MDC.put(WebhHookConstants.REQUEST_STATUS,"SUCCESS");
        MDC.put(WebhHookConstants.ERROR_TYPE,"");
        MDC.put(WebhHookConstants.STATUS_CODE,"200");
        MDC.put(WebhHookConstants.SOURCE_OF_DATA,"MS_SAP");
        MDC.put(WebhHookConstants.IS_SAP_ONLINE,WebhHookConstants.STATUS_TRUE);

    }
}
