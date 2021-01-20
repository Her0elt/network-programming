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
        try {
            ServerSocket server = new ServerSocket(PORTNR);
            System.out.println("Wating for connection");
            Socket forbindelse = server.accept(); 

            InputStreamReader leseforbindelse = new InputStreamReader(forbindelse.getInputStream());
            BufferedReader leseren = new BufferedReader(leseforbindelse);
            PrintWriter skriveren = new PrintWriter(forbindelse.getOutputStream(), true);
            while(!leseren.readLine().equals("") )
            skriveren.println("HTTP/1.0 200 OK");
            skriveren.println("Content-Type: text/html; charset=utf-8");
            skriveren.println("");
            skriveren.println("<!DOCTYPE html><html><body>");
            skriveren.println("<h1> U have connect to the host </h1>");
            skriveren.println("<ul>");
            skriveren.println("<li> ...... </li>");
            skriveren.println("</ul>");
            skriveren.println("</body></html>");
            skriveren.flush();
        } catch (Exception e) {
        }finally{
                leseren.close();
                skriveren.close();
                forbindelse.close();

            }
        
    }
}
