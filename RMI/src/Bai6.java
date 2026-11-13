import java.rmi.Naming;
import java.util.LinkedHashMap;
import java.util.Map;

import RMI.ByteService;

/** Bài 6: Tìm byte xuất hiện ít nhất; nếu hòa, lấy giá trị xuất hiện trước. */
public class Bai6 {
    public static void main(String[] args) {
        try {
            ByteService service = (ByteService) Naming.lookup(
                    RMIConfig.lookupUrl("RMIByteService")
            );

            byte[] input = service.requestData(
                    RMIConfig.STUDENT_CODE, RMIConfig.QCODE_BAI6
            );
            byte[] result = findLeastFrequent(input);

            // Mảng gửi về gồm [giá trị ít phổ biến nhất, số lần xuất hiện].
            service.submitData(
                    RMIConfig.STUDENT_CODE, RMIConfig.QCODE_BAI6, result
            );
            //System.out.println("Ket qua [gia tri, tan suat]: ["
            //        + result[0] + ", " + Byte.toUnsignedInt(result[1]) + "]");

        } catch (Exception e) {
            //System.err.println("Bai 6 that bai: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static byte[] findLeastFrequent(byte[] numbers) {
        //if (numbers == null || numbers.length == 0) {
            //throw new IllegalArgumentException("Mảng byte đầu vào rỗng hoặc null.");
        //}

        // LinkedHashMap giữ thứ tự xuất hiện đầu tiên của từng giá trị.
        Map<Byte, Integer> frequency = new LinkedHashMap<>();
        for (byte value : numbers) {
            frequency.put(value, frequency.getOrDefault(value, 0) + 1);
        }

        byte bestValue = 0;
        int minFrequency = Integer.MAX_VALUE;

        for (Map.Entry<Byte, Integer> entry : frequency.entrySet()) {
            // Dùng < (không dùng <=): khi bằng tần suất, giữ phần tử gặp trước.
            if (entry.getValue() < minFrequency) {
                bestValue = entry.getKey();
                minFrequency = entry.getValue();
            }
        }

        return new byte[] { bestValue, (byte) minFrequency };
    }
}
