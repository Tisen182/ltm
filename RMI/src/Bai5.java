import RMI.ByteService;

import java.rmi.Naming;
import java.util.LinkedHashMap;
import java.util.Map;

/** Bài 5: Tìm byte xuất hiện nhiều nhất; nếu hòa, lấy giá trị xuất hiện trước. */
public class Bai5 {
    public static void main(String[] args) {
        try {
            ByteService service = (ByteService) Naming.lookup(
                    RMIConfig.lookupUrl("RMIByteService")
            );

            byte[] input = service.requestData(
                    RMIConfig.STUDENT_CODE, RMIConfig.QCODE_BAI5
            );
            byte[] result = findMostFrequent(input);

            // Mảng gửi về gồm [giá trị phổ biến nhất, số lần xuất hiện].
            service.submitData(
                    RMIConfig.STUDENT_CODE, RMIConfig.QCODE_BAI5, result
            );
            //System.out.println("Ket qua [gia tri, tan suat]: ["
            //        + result[0] + ", " + Byte.toUnsignedInt(result[1]) + "]");

        } catch (Exception e) {
            //System.err.println("Bai 5 that bai: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static byte[] findMostFrequent(byte[] numbers) {
        //if (numbers == null || numbers.length == 0) {
            //throw new IllegalArgumentException("Mảng byte đầu vào rỗng hoặc null.");
        //}

        // LinkedHashMap giữ thứ tự phần tử được thêm lần đầu tiên.
        Map<Byte, Integer> frequency = new LinkedHashMap<>();
        for (byte value : numbers) {
            frequency.put(value, frequency.getOrDefault(value, 0) + 1);
        }

        byte bestValue = 0;
        int maxFrequency = -1;

        for (Map.Entry<Byte, Integer> entry : frequency.entrySet()) {
            // Dùng > (không dùng >=): khi bằng tần suất, giữ phần tử gặp trước.
            if (entry.getValue() > maxFrequency) {
                bestValue = entry.getKey();
                maxFrequency = entry.getValue();
            }
        }

        // Giao diện yêu cầu byte[] nên tần suất cũng được ép về byte.
        // Bài thi thường đảm bảo tần suất phù hợp phạm vi byte không dấu (0..255).
        return new byte[] { bestValue, (byte) maxFrequency };
    }
}
