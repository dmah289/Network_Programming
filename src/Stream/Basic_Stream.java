package Stream;

import java.io.*;
import java.io.IOException;

public class Basic_Stream {
    public static void main(String[] args) throws IOException {
        Buffered_Reader.test();
    }
}

class File_Input_Stream{
    public static void test() throws IOException {
        String path = "test.txt";
        FileInputStream fis = new FileInputStream(path);
        byte[] data = new byte[1024];   // Mảng byte lưu dữ liệu đọc được
        int numberOfByte = 0;           // Số byte thực tế đọc được mỗi vòng lặp
        while((numberOfByte = fis.read(data)) != -1){
            // Chỉ in ra số byte đọc được trả về từ read() trong mảng byte
            System.out.println(new String(data, 0, numberOfByte, "UTF-8"));
        }
        fis.close();
    }
}

class Buffered_Reader{
    public static void test() throws IOException {
        String path = "test.txt";
        FileInputStream fis = new FileInputStream(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String line = br.readLine();
        while(line != null){
            System.out.println(line);
            line = br.readLine();
        }
    }
}
