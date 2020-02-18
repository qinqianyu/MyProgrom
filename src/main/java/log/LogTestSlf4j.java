package log;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class LogTestSlf4j {
    @Test
    public void testlog4j() {
        log.info("hello word");
    }
}
