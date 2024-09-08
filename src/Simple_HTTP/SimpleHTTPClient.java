package Simple_HTTP;

import java.io.*;
import java.net.Socket;

public class SimpleHTTPClient {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost", 8080);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        String request = "GET / HTTP/1.1\r\n" + "Host: Google.com\r\n" + "User-Agent: SimpleHTTP\r\n" + "Accept: text/*\r\n\n";

        bw.write(request);
        bw.flush();

        // ---------------------------- Luồng byte ----------------------------

//        InputStream in = client.getInputStream();
//        byte[] data = new byte[1024];
//        int byteRead = 0;
//        while((byteRead = in.read(data)) != -1){
//            System.out.println(new String(data, 0, byteRead, "UTF-8"));
//        }

        // ---------------------------- Luồng văn bản ----------------------------

        // Đọc từ socket
        BufferedReader in1 = new BufferedReader(new InputStreamReader(client.getInputStream()));
        // Ghi vào file theo luồng văn bản
        bw = new BufferedWriter(new FileWriter("test1.txt"));
        String line;
        while((line = in1.readLine()) != null){
            bw.write(line);
            bw.newLine();
        }
        bw.flush();    // Ghi xong phải đẩy bộ đệm đi

        bw.close();
        in1.close();
        client.close();
    }
}
