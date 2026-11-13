import java.rmi.Naming;
import java.util.Arrays;
import java.util.Locale;

import RMI.DataService;

/** Bài 1: Tính trung bình, độ lệch chuẩn tổng thể và phân vị 95 (P95). */
public class Bai1 {
    public static void main(String[] args) {
        try {
            // 1. Kết nối tới Remote Object đã đăng ký trên Registry.
            DataService service = (DataService) Naming.lookup(
                    RMIConfig.lookupUrl("RMIDataService")
            );

            // 2. Nhận dữ liệu CSV. Theo đề, requestData trả về Object
            // nhưng nội dung thực tế của bài này là một String.
            Object rawData = service.requestData(
                    RMIConfig.STUDENT_CODE, RMIConfig.QCODE_BAI1
            );
            //if (rawData == null) {
                //throw new IllegalStateException("Server trả về dữ liệu null.");
            //}
            String csv = rawData.toString().trim();

            // 3. Tách CSV và chuyển từng giá trị thành double.
            String[] tokens = csv.split(",");
            //if (tokens.length == 0) {
                //throw new IllegalArgumentException("Dãy số đầu vào rỗng.");
            //}

            double[] numbers = new double[tokens.length];
            for (int i = 0; i < tokens.length; i++) {
                numbers[i] = Double.parseDouble(tokens[i].trim());
            }

            // 4. Tính trung bình cộng.
            double sum = 0.0;
            for (double x : numbers) {
                sum += x;
            }
            double average = sum / numbers.length;

            // 5. Độ lệch chuẩn của toàn bộ dãy (population standard deviation).
            // Vì đề nói trên toàn bộ dãy nên mẫu số là n, không phải n - 1.
            double squaredDifferenceSum = 0.0;
            for (double x : numbers) {
                double difference = x - average;
                squaredDifferenceSum += difference * difference;
            }
            double stddev = Math.sqrt(squaredDifferenceSum / numbers.length);

            // 6. Sắp xếp tăng dần và lấy phần tử tại ceil(n * 0.95) - 1.
            Arrays.sort(numbers);
            int p95Index = (int) Math.ceil(numbers.length * 0.95) - 1;
            double p95 = numbers[p95Index];

            // 7. Định dạng đúng mẫu, luôn dùng dấu chấm thập phân.
            String result = String.format(
                    Locale.US,
                    "average=%.2f;stddev=%.2f;p95=%.2f",
                    average, stddev, p95
            );

            // 8. Gửi kết quả về Server.
            service.submitData(
                    RMIConfig.STUDENT_CODE, RMIConfig.QCODE_BAI1, result
            );
            //System.out.println("Da gui: " + result);

        } catch (Exception e) {
            //System.err.println("Bai 1 that bai: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
