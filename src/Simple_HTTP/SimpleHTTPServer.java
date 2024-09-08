package Simple_HTTP;

import java.io.*;
import java.net.*;

public class SimpleHTTPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8080);
        System.out.println("Server is listening on port 8080 ...");

        while(true){
            Socket conn = server.accept();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = br.readLine();
            while(!line.isEmpty()){
                System.out.println(line);
                line = br.readLine();
            }
            System.out.println("---------- Accepted ----------");

            OutputStream out = conn.getOutputStream();
            String strHeader = "HTTP/1.1 200 OK \r\n\r\n";
            String strBody = "The server is busy now. Try again later!";
            out.write((strHeader + strBody).getBytes("UTF-8"));

            out.close();
            br.close();
            conn.close();
        }
    }
}
