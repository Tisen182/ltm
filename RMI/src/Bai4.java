import java.net.URI;
import java.rmi.Naming;

import RMI.CharacterService;

/** Bài 4: Mã hóa chuỗi theo quy tắc URI path (không dùng URLEncoder). */
public class Bai4 {
    public static void main(String[] args) {
        try {
            CharacterService service = (CharacterService) Naming.lookup(
                    RMIConfig.lookupUrl("RMICharacterService")
            );

            String input = service.requestCharacter(
                    RMIConfig.STUDENT_CODE, RMIConfig.QCODE_BAI4
            );

            String encoded = encodeUriPath(input);

            service.submitCharacter(
                    RMIConfig.STUDENT_CODE, RMIConfig.QCODE_BAI4, encoded
            );
            //System.out.println("Chuoi sau ma hoa: " + encoded);

        } catch (Exception e) {
            //System.err.println("Bai 4 that bai: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Làm đúng theo đề: new URI(null, null, s, null).toASCIIString().
     * Tham số thứ ba là path, vì vậy dấu cách được mã hóa thành %20.
     */
    private static String encodeUriPath(String input) throws Exception {
        //if (input == null) {
            //throw new IllegalArgumentException("Chuỗi đầu vào là null.");
        //}
        return new URI(null, null, input, null).toASCIIString();
    }
}
