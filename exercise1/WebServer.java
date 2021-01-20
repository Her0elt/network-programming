package exercise1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
    public static void main(String[] args) throws IOException{
        final int PORTNR = 1250;
        Socket connection = null;
        InputStreamReader readConnection = null;
        BufferedReader reader = null;
        PrintWriter writer = null;
        try {
            ServerSocket server = new ServerSocket(PORTNR);
            System.out.println("Wating for connection");
            connection = server.accept(); 

            readConnection = new InputStreamReader(connection.getInputStream());
            reader = new BufferedReader(readConnection);
            writer = new PrintWriter(connection.getOutputStream(), true);
            while(!reader.readLine().equals("") )
            writer.println("HTTP/1.0 200 OK");
            writer.println("Content-Type: text/html; charset=utf-8");
            writer.println("");
            writer.println("<!DOCTYPE html><html><body>");
            writer.println("<h1> U have connect to the host </h1>");
            writer.println("<ul>");
            writer.println("<li> ...... </li>");
            writer.println("</ul>");
            writer.println("</body></html>");
            writer.flush();
        } catch (Exception e) {
        }finally{
                reader.close();
                writer.close();
                connection.close();

            }
        
    }
}
