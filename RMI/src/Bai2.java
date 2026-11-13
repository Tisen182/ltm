import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;

import RMI.DataService;

/** Bài 2: Tìm các đỉnh cục bộ và gửi danh sách vị trí (đánh số từ 1). */
public class Bai2 {
    public static void main(String[] args) {
        try {
            DataService service = (DataService) Naming.lookup(
                    RMIConfig.lookupUrl("RMIDataService")
            );

            // Đề khai báo kiểu trả về là Object. Thông thường dữ liệu bài này
            // là int[]. Hàm convertToIntArray cũng hỗ trợ List<Integer> để
            // tránh lỗi nếu Server đóng gói dữ liệu dưới dạng List.
            Object rawData = service.requestData(
                    RMIConfig.STUDENT_CODE, RMIConfig.QCODE_BAI2
            );
            int[] numbers = convertToIntArray(rawData);

            List<Integer> peakPositions = findPeakPositions(numbers);

            // submitData nhận Object, theo đề phải gửi List<Integer>.
            service.submitData(
                    RMIConfig.STUDENT_CODE, RMIConfig.QCODE_BAI2, peakPositions
            );
            //System.out.println("Cac vi tri dinh cuc bo: " + peakPositions);

        } catch (Exception e) {
            //System.err.println("Bai 2 that bai: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /** Chuyển Object nhận từ RMI thành int[]. */
    private static int[] convertToIntArray(Object data) {
        if (data == null) {
            throw new IllegalArgumentException("Server trả về dữ liệu null.");
        }

        if (data instanceof int[]) {
            return (int[]) data;
        }

        if (data instanceof Integer[]) {
            Integer[] source = (Integer[]) data;
            int[] result = new int[source.length];
            for (int i = 0; i < source.length; i++) {
                result[i] = source[i];
            }
            return result;
        }

        if (data instanceof List<?>) {
            List<?> source = (List<?>) data;
            int[] result = new int[source.size()];
            for (int i = 0; i < source.size(); i++) {
                Object value = source.get(i);
                if (!(value instanceof Number)) {
                    throw new IllegalArgumentException(
                            "Phần tử trong List không phải kiểu số: " + value
                    );
                }
                result[i] = ((Number) value).intValue();
            }
            return result;
        }

        throw new IllegalArgumentException(
                "Kiểu dữ liệu không được hỗ trợ: " + data.getClass().getName()
        );
    }

    /**
     * Đỉnh cục bộ: lớn hơn phần tử liền kề bên trái và bên phải.
     * Ở hai đầu chỉ cần so sánh với phần tử duy nhất bên cạnh.
     * Vị trí trả về được đánh số từ 1 như đề yêu cầu.
     */
    private static List<Integer> findPeakPositions(int[] numbers) {
        List<Integer> peaks = new ArrayList<>();

        if (numbers == null || numbers.length == 0) {
            return peaks;
        }

        // Quy ước với mảng chỉ có một phần tử: phần tử đó là đỉnh vì
        // không có phần tử liền kề nào vi phạm điều kiện.
        if (numbers.length == 1) {
            peaks.add(1);
            return peaks;
        }

        for (int i = 0; i < numbers.length; i++) {
            boolean isPeak;

            if (i == 0) {
                isPeak = numbers[i] > numbers[i + 1];
            } else if (i == numbers.length - 1) {
                isPeak = numbers[i] > numbers[i - 1];
            } else {
                isPeak = numbers[i] > numbers[i - 1]
                        && numbers[i] > numbers[i + 1];
            }

            if (isPeak) {
                peaks.add(i + 1); // Chuyển chỉ số Java (0-based) thành vị trí (1-based).
            }
        }
        return peaks;
    }
}
