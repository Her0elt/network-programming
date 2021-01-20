package exercise1;


import java.io.*;
import java.net.*;
import java.util.Scanner;
class SocketClient {
  public static void main(String[] args) throws IOException {
    final int PORTNR = 1250;
    Socket connection = null;
    InputStreamReader readConneciton = null;
    BufferedReader reader = null;
    PrintWriter writer = null;
    try {
      
      Scanner ReadFromCommandLine = new Scanner(System.in);
      System.out.print("Give the name of the host: ");
      String tjenermaskin = ReadFromCommandLine.nextLine();
  
      connection = new Socket(tjenermaskin, PORTNR);
      System.out.println("Connection created.");
  
      readConneciton = new InputStreamReader(connection.getInputStream());
      reader = new BufferedReader(readConneciton);
      writer = new PrintWriter(connection.getOutputStream(), true);
  
      String intro1 = reader.readLine();
      String intro2 = reader.readLine();
      System.out.println(intro1 + "\n" + intro2);
      
      String aline = ReadFromCommandLine.nextLine();
      while (!aline.equals("")) {
        writer.println(aline);
        String respons = reader.readLine();  
        System.out.println("From server: " + respons);
        aline = ReadFromCommandLine.nextLine();
      }
    } catch (Exception e) {
      
    }finally{
      reader.close();
      writer.close();
      connection.close();
    }
  }
}