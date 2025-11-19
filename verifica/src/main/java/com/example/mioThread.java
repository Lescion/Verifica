package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;


public class mioThread extends Thread {
    Socket s;
    BufferedReader in;
    PrintWriter out;

    public mioThread(Socket s) throws IOException {
        this.s = s;

        in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        out = new PrintWriter(s.getOutputStream(), true);
    }

    public void run() 
    {
        private static int balance = 0;
        private static List<String> history = new ArrayList<>();
        private static boolean initAllowed = true;

        out.println("WELCOME BANK v1 BAL 0");
        String line;
        while ((line = in.readLine()) != null) {
            String[] parts = line.split(" ");
            String command = parts[0];
        switch(command){
            case "INIT":
                handleInit(parts, out);
                break;
            case "BAL" :
                handleBalance(out);
                break;
            case "DEPOSIT":
                handDeposit(parts, out);
                break;
            case "WITHDRAW":
                handWithdraw(parts, out);
                break;
            case "HISTORY":
                handHistory(out);
                break;
            case "RESET":
                handReset(out);
                break;
            case "QUIT":
                handQuit("BYE");
                break;    
        }
     catch (IOException e) {
         System.err.println("Errore di I/O: " + e.getMessage());
            }

     
        }
    }
}