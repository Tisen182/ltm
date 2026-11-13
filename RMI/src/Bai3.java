import java.rmi.Naming;

import RMI.CharacterService;

/** Bài 3: Chuyển số thập phân thành số La Mã. */
public class Bai3 {
    public static void main(String[] args) {
        try {
            CharacterService service = (CharacterService) Naming.lookup(
                    RMIConfig.lookupUrl("RMICharacterService")
            );

            // Nhận chuỗi số từ Server, ví dụ "58".
            String input = service.requestCharacter(
                    RMIConfig.STUDENT_CODE, RMIConfig.QCODE_BAI3
            );
            int number = Integer.parseInt(input.trim());

            String roman = toRoman(number);

            service.submitCharacter(
                    RMIConfig.STUDENT_CODE, RMIConfig.QCODE_BAI3, roman
            );
            //System.out.println("So La Ma: " + roman);

        } catch (Exception e) {
            //System.err.println("Bai 3 that bai: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /** Chuyển số nguyên dương sang số La Mã theo thuật toán tham lam. */
    private static String toRoman(int number) {
        //if (number <= 0) {
            //throw new IllegalArgumentException(
            //        "Số La Mã trong bài này cần là số nguyên dương."
            //);
        //}

        // Các giá trị đặc biệt như 900, 400, 90, 40, 9, 4
        // phải được xét trước các giá trị nhỏ hơn tương ứng.
        int[] values = {
                1000, 900, 500, 400,
                100, 90, 50, 40,
                10, 9, 5, 4, 1
        };
        String[] symbols = {
                "M", "CM", "D", "CD",
                "C", "XC", "L", "XL",
                "X", "IX", "V", "IV", "I"
        };

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            while (number >= values[i]) {
                result.append(symbols[i]);
                number -= values[i];
            }
        }
        return result.toString();
    }
}
