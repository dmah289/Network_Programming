package Stream;

import java.io.*;
import java.io.IOException;

public class Basic_Stream {

    public static String path = "test.txt";
    public static String path1 = "data.bin";
    public static String path2 = "test1.txt";

    public static void main(String[] args) throws IOException {
        Print_Writer();
    }

    /*------------------------------------------- Luồng byte -------------------------------------------*/

    // Đọc dữ liệu theo từng byte vào stream/.txt
    public static void File_Input_Stream() throws IOException {
        FileInputStream fis = new FileInputStream(path);
        byte[] data = new byte[1024];   // Mảng byte lưu dữ liệu đọc được
        int numberOfByte = 0;           // Số byte thực tế đọc được mỗi vòng lặp
        while((numberOfByte = fis.read(data)) != -1){
            // Chỉ in ra số byte đọc được trả về từ read() trong mảng byte
            System.out.println(new String(data, 0, numberOfByte, "UTF-8"));
        }
        fis.close();
    }

    // Ghi dữ liệu nhị phân vào stream/.txt
    public static void File_Output_Stream() throws IOException {
        FileOutputStream fos = new FileOutputStream(path1);

        byte[] data = {(byte) 289, (byte) 313, (byte) 197, (byte) 155};
        fos.write(data);

        fos.close();
    }

    // Sử dụng bộ đệm lưu trữ tạm thời lượng lớn dữ liệu vào bộ đệm được đọc từ stream/.txt
    public static void Buffered_Input_Stream() throws IOException {
        FileInputStream fis = new FileInputStream(path);
        BufferedInputStream bis = new BufferedInputStream(fis);

        byte[] data = new byte[1024];
        int nOfBytesReaded;

        while((nOfBytesReaded = bis.read(data)) != -1){
            System.out.println(new String(data, 0, nOfBytesReaded, "UTF-8"));
        }

        bis.close();
    }

    // Chỉ ghi ra file khi bộ đệm đầy / đóng stream
    public static void Buffered_Output_Stream() throws IOException {
        FileOutputStream fos = new FileOutputStream(path2);
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        String data = "Nguyễn Đình Mạnh";
        bos.write(data.getBytes());
        bos.flush();    // Ghi luôn dữ liệu mà không cần đợi bộ đệm đầy

        bos.close();
    }

    // Ghi dữ liệu nhị phân vào stream/bin
    public static void Data_Output_Stream() throws IOException {
        FileOutputStream fos = new FileOutputStream(path1);
        DataOutputStream dos = new DataOutputStream(fos);

        dos.writeInt(289);
        dos.writeDouble(31.3);
        dos.writeUTF("Nguyễn Đình Mạnh");

        dos.close();
    }

    // Đọc dữ liệu thô có kiểu xác định từ stream/bin
    public static void Data_Input_Stream() throws IOException {
        Data_Output_Stream();
        FileInputStream fis = new FileInputStream(path1);
        DataInputStream dis  = new DataInputStream(fis);
        while(dis.available() > 0){
            int num1 = dis.readInt();
            double num2 = dis.readDouble();
            String data = dis.readUTF();
            System.out.println(num1 + " " + num2 + " " + data);
        }
        dis.close();
    }

    /*------------------------------------------- Luồng ký tự -------------------------------------------*/

    // Luồng ký tự: Đọc dữ liệu từ .txt, đọc byte rồi chuyển về ký tự
    public static void File_Reader() throws IOException {
        FileReader fr = new FileReader(path2);
        int character;

        while((character = fr.read()) != -1){
            System.out.println((char) character);
        }

        fr.close();
    }

    // Luồng ký tự: Ghi dữ liệu vào .txt
    public static void File_Writer() throws IOException {
        FileWriter fw = new FileWriter(path2);

        fw.write("Nguyễn Đình Mạnh\n");

        fw.close();
    }

    // Luồng ký tự: Bọc quanh 1 FileInputStream. Đọc dữ liệu nhị phân
    public static void Buffered_Reader() throws IOException {
        // FileReader không cho chỉ định mã hóa ký tự
        FileInputStream fis = new FileInputStream(path);
        // Cần Chuyển đổi InputStream thành 1 luồng Reader - luồng ký tự -> Chỉ định mã hóa ký tự
        BufferedReader br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
        String line = br.readLine();
        while(line != null){
            System.out.println(line);
            line = br.readLine();
        }
    }

    // Luồng ký tự: Bọc quanh 1 FileWriter / OutputStreamWriter. Ghi văn bản vào 1 tệp ký tự .txt / stream
    public static void Buffered_Writer() throws IOException {
        FileWriter fw = new FileWriter(path2);
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write("313");
        bw.newLine();
        bw.write("Nguyễn Đình Mạnh");
        bw.flush();

        bw.close();
    }

    // Luồng ký tự: Ghi dữ lệu có định dạng vào .txt / stream
    public static void Print_Stream() throws FileNotFoundException {
        FileOutputStream fos = new FileOutputStream(path2);
        PrintStream ps = new PrintStream(fos);

        ps.println(289);
        ps.printf("Formatted: %.2f", 289.313);

        ps.close();
    }

    // Luồng ký tự: Bọc quanh 1 FileWriter. Ghi văn bản có định dạng vào .txt
    public static void Print_Writer() throws IOException {
        FileWriter fw = new FileWriter(path2);
        PrintWriter pw = new PrintWriter(fw);

        pw.println(289);
        pw.println("Nguyễn Đình Mạnh");
        pw.printf("Formatted num: %.2f", 289.313);
        pw.flush();

        pw.close();
    }

}
