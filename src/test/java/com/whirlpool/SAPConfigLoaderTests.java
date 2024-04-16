package com.whirlpool;

import com.whirlpool.webhook.common.SAPConfigLoader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import uk.org.webcompere.systemstubs.environment.EnvironmentVariables;
import uk.org.webcompere.systemstubs.jupiter.SystemStub;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;

import static com.whirlpool.webhook.common.WebhHookConstants.CONFIG_FILE;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SystemStubsExtension.class)
public class SAPConfigLoaderTests {
    public static final String APPLICATION_QA_PROPERTIES = "src/main/resources/application-dev.properties";
    @SystemStub
    private static EnvironmentVariables environmentVariables;

    @BeforeAll
    static void init() {
        environmentVariables.set(CONFIG_FILE, APPLICATION_QA_PROPERTIES);
    }

    @Test
    void testPropertiesInitialization(){
        assertEquals("ABAP_AS1_DEV", SAPConfigLoader.getRfcDestination());
    }
}
