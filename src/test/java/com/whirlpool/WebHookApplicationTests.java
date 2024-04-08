package com.whirlpool;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

/**
 * Unit test for simple App.
 */
@SpringBootTest
public class WebHookApplicationTests{
    @Test
    void testMain() throws IOException{
        WebHookApplication.main(new String[]{});
        assertTrue(true);
    }
}
