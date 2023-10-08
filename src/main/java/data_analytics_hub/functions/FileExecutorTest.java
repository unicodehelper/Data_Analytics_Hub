package data_analytics_hub.functions;

import static org.junit.jupiter.api.Assertions.*;

public class FileExecutorTest {

    @org.junit.jupiter.api.Test
    public void testIsFileReadable() {
        FileExecutor fileExecutor = new FileExecutor();
        assertTrue(fileExecutor.isFileReadable("src\\main\\resources\\data_analytics_hub\\data\\users.txt"));
        assertFalse(fileExecutor.isFileReadable("src\\main\\resources\\data_analytics_hub\\data\\users1.txt"));
    }
}
