BO JAVA RMI - 6 BAI CLIENT
==========================

1. Cau truc
-----------
src/
  RMI/
    DataService.java
    CharacterService.java
    ByteService.java
  RMIConfig.java
  Bai1.java ... Bai6.java

2. Cau hinh truoc khi chay
--------------------------
Mo src/RMIConfig.java va thay:
- STUDENT_CODE = ma sinh vien cua ban.
- QCODE_BAI1 ... QCODE_BAI6 = ma cau hoi tuong ung tren giao dien thi.
- PORT = port RMI Registry ma he thong thi cong bo. 1099 chi la port mac dinh.

Dia chi IP duoc dat mac dinh theo anh de: 36.50.135.242.

3. Bien dich
------------
Mo terminal tai thu muc RMI_Practice va chay:

  javac -encoding UTF-8 -d out src/RMI/*.java src/*.java

4. Chay tung bai
----------------
Windows:
  java -cp out Bai1
  java -cp out Bai2
  java -cp out Bai3
  java -cp out Bai4
  java -cp out Bai5
  java -cp out Bai6

Linux/macOS: lenh tuong tu, neu shell khong ho tro wildcard tren duong dan,
liet ke cac file .java trong src/RMI.

5. Luu y
--------
- Phai co ket noi mang toi Exam Server va dung dung port Registry.
- Package cua 3 Remote Interface la RMI (phan biet chu hoa/thuong).
- Bai 1 nhan Object nhung noi dung thuc te la String CSV.
- Bai 2 nhan Object; code ho tro int[], Integer[] va List<Integer>.
- Bai 2 gui List<Integer> theo yeu cau de.
- Bai 3 va bai 4 dung CharacterService.
- Bai 5 va bai 6 gui byte[] gom [gia tri, tan suat].
- Neu he thong thi yeu cau ten lop/package khac, can dieu chinh theo huong dan cua he thong.
