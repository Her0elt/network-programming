package exercise1;

import java.io.*;
import java.net.*;



class SocketServer{
  public static void main(String[] args) throws IOException {
    final int PORTNR = 1250;
    ServerSocket server = new ServerSocket(PORTNR);
    Socket connection = null;
    InputStreamReader readConnection = null;
    BufferedReader reader = null;
    PrintWriter writer = null;
    try{
        System.out.println("Logg for tjenersiden. Nå venter vi...");
        connection = server.accept(); 
    
        readConnection = new InputStreamReader(connection.getInputStream());
        reader = new BufferedReader(readConnection);
        writer = new PrintWriter(connection.getOutputStream(), true);
    
        writer.println("You have contact with the server");
                writer.println("Write a equation in the format 1 + 1, then i'll give you the answer," 
                        + "end with enter");
    
        String aLine = reader.readLine();  
        while (aLine != null) { 
            
            String answer = calc(aLine.split(" ")); 
            writer.println(answer);
            aLine = reader.readLine();
        }
    } catch(Exception e){
    }finally{
        reader.close();
        writer.close();
        connection.close();
    }

    /* Lukker forbindelse */
  }
  public static String calc(String[] array) {
    if (array.length != 3)
        return "Wrong format";

    if (array[1].equals("+"))
        return String.valueOf(Double.parseDouble(array[0]) + Double.parseDouble(array[2]));
    if (array[1].equals("-"))
        return String.valueOf(Double.parseDouble(array[0]) + Double.parseDouble(array[2]));
    if (array[1].equals("*"))
        return String.valueOf(Double.parseDouble(array[0]) + Double.parseDouble(array[2]));
    if (array[1].equals("/") && !array[2].equals("0"))
        return String.valueOf(Double.parseDouble(array[0]) + Double.parseDouble(array[2]));
    if (array[1].equals("/") && array[2].equals("0"))
        return "You can't divide by zero";

    return "Wrong format";

}
  
}

