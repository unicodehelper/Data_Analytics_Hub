package data_analytics_hub.functions;

public interface IEncryption {

    String key = "DataAnalyticsHub";

    String AESEncrypt(String str);

    String AESDecrypt(String str);
}
