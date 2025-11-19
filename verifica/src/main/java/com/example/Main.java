package com.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException{
        ServerSocket ss = new ServerSocket(3000);
        do{
        Socket s = ss.accept();
        mioThread t = new mioThread(s);
        t.start();
        }while(true);
    }
}