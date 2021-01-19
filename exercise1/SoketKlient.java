package exercise1;


import java.io.*;
import java.net.*;
import java.util.Scanner;
class SocketKlient {
  public static void main(String[] args) throws IOException {
    final int PORTNR = 1250;

    Scanner ReadFromCommandLine = new Scanner(System.in);
    System.out.print("Oppgi navnet p� maskinen der tjenerprogrammet kj�rer: ");
    String tjenermaskin = ReadFromCommandLine.nextLine();

    Socket forbindelse = new Socket(tjenermaskin, PORTNR);
    System.out.println("N� er forbindelsen opprettet.");

    InputStreamReader readConneciton
                      = new InputStreamReader(forbindelse.getInputStream());
    BufferedReader reader = new BufferedReader(readConneciton);
    PrintWriter writer = new PrintWriter(forbindelse.getOutputStream(), true);

    String innledning1 = reader.readLine();
    String innledning2 = reader.readLine();
    System.out.println(innledning1 + "\n" + innledning2);
    
    String enLinje = ReadFromCommandLine.nextLine();
    while (!enLinje.equals("")) {
      writer.println(enLinje);
      String respons = reader.readLine();  
      System.out.println("Fra tjenerprogrammet: " + respons);
      enLinje = ReadFromCommandLine.nextLine();
    }

    reader.close();
    writer.close();
    forbindelse.close();
  }
}