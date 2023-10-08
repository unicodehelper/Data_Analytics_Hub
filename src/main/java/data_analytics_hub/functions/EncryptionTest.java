package data_analytics_hub.functions;

import static org.junit.jupiter.api.Assertions.*;

class EncryptionTest {

    @org.junit.jupiter.api.Test
    public void testAESEncrypt() {
        String test = "Hello World";
        String encrypted = new Encryption().AESEncrypt(test);
        String decrypted = new Encryption().AESDecrypt(encrypted);
        assertEquals(test, decrypted);
        assertNotEquals(test, encrypted);
    }
}