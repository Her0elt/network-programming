package exercise1;

import java.io.*;
import java.net.*;
import java.util.Scanner;
class ClientHandler extends Thread{
    Socket s;
    InputStreamReader readConnection;
    BufferedReader reader;
    PrintWriter writer;
    ClientHandler(Socket forbindelse)throws IOException{
         readConnection = new InputStreamReader(forbindelse.getInputStream());
         reader = new BufferedReader(readConnection);
         writer = new PrintWriter(forbindelse.getOutputStream(), true);
    }
    public void run(){
        try{
            writer.println("You have contact with the server");
            writer.println("Write a equation in the format 1 + 1, then i'll give you the answer," 
                    + "end with enter");

            String aLine = reader.readLine();
            while (aLine != null) {

                String svar = calc(aLine.split(" "));
                writer.println(svar);
                aLine = reader.readLine();

            }

            /* Lukker forbindelse */
            reader.close();
            writer.close();
            s.close();
        }catch(IOException e){
            e.printStackTrace();
        }
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
class ThreadSocketServer{
    public static void main(String[] args)throws IOException {
        final int PORTNR = 1250;
        Scanner readFromCommandLine = new Scanner(System.in);
        System.out.println("How many threads should be ran");
        int nr = readFromCommandLine.nextInt();
        ServerSocket server = new ServerSocket(PORTNR);
        for (int i = 0; i < nr; i++) {
            Socket forbindelse = null;
            try {
                System.out.println(i+1+" Waiting for client");
                forbindelse = server.accept();
                Thread t = new ClientHandler(forbindelse);
                t.start();

            } catch (Exception e) {
                
            }
        }
    }
       
}
