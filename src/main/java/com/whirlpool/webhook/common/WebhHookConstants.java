package com.whirlpool.webhook.common;

import lombok.experimental.UtilityClass;

@UtilityClass
public class WebhHookConstants {
    public static final String CONFIG_FILE = "CONFIG_FILE";
    public static final String SAP_RFC_DESTINATION = "sap.rfcDestination";
    public static final String SOURCE_OF_DATA = "sourceOfData";
    public static final String REQUEST = "request";
    public static final String RESPONSE = "response";
    public static final String CORRELATION_ID = "correlationId";
    public static final String IS_ERROR = "isError";
    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String REQUEST_STATUS = "requestStatus";
    public static final String STATUS_CODE = "statusCode";
    public static final String IS_SAP_ONLINE = "isSapOnline";
    public static final String TIME_TAKEN_BY_SAP = "sapRfcExecution";
    public static final String TOTAL_TIME_TAKEN_BY_SAP = "timeTakenToProcessSapResponse";
    public static final String REQUEST_COMPLETED = "Request Completed";
    public static final String ERROR_TYPE = "errorType";
    public static final String STATUS_TRUE = "true";
    public static final String SAP_EXPIRATION_TIME = "sapExpirationTime";
    public static final String EXPIRATION_CHECK_PERIOD = "expirationCheckPeriod";
    public static final String MAX_GET_CLIENT_TIME = "maxGetClientTime";
}