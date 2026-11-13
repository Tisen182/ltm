/**
 * Cấu hình dùng chung cho 6 chương trình Client.
 * Hãy thay STUDENT_CODE và từng QCODE bằng thông tin thực tế trên đề thi.
 * PORT=1099 là port RMI Registry mặc định; đổi lại nếu hệ thống thi cung cấp port khác.
 */
public final class RMIConfig {
    private RMIConfig() { }

    public static final String HOST = "36.50.135.242";
    public static final int PORT = 1099;
    public static final String STUDENT_CODE = "B23DCCN724";

    public static final String QCODE_BAI1 = "4QGBGHF4"; //Datasốthực
    public static final String QCODE_BAI2 = "hyWddyCe"; //Datađỉnhcb
    public static final String QCODE_BAI3 = "HM0XSiRi"; //CharRoman
    public static final String QCODE_BAI4 = "HVqId2DA"; //CharEncode
    public static final String QCODE_BAI5 = "V9m67rk8"; //Byte2
    public static final String QCODE_BAI6 = "QBznt5YW"; //Byte1

    public static String lookupUrl(String registryName) {
        return "rmi://" + HOST + ":" + PORT + "/" + registryName;
    }
}
