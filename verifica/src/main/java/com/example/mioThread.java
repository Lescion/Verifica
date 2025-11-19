package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
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
        int balance = 0;
        List<String> history = new ArrayList<>();
        boolean initAllowed = true;

        out.println("WELCOME BANK v1 BAL 0");
        String line;
        while ((line = in.readLine()) != null) 
        {
            String[] parts = line.split(" ");
            String command = parts[0];
            switch(command){
                case "INIT":
                    handleDEPOSIT(parts, out);
                    break;

                case "BAL":
                    if (parts.length != 1) out.println("ERR SYNTAX");
                    else out.println("INFO BAL " + balance);
                    break;

                case "DEPOSIT":
                    handleDEPOSIT(parts, out);
                    break;

                case "WITHDRAW":
                    handleWITHDRAW(parts, out, balance, initAllowed);
                    break;

                case "HISTORY":
                    handleHISTORY(parts, out);
                    break;

                case "RESET":
                    if (parts.length != 1) out.println("ERR SYNTAX");
                    else {
                        balance = 0;
                        history.clear();
                        initAllowed = true;
                        out.println("OK RESET BAL 0");
                    }
                    break;

                case "QUIT":
                    out.println("BYE");
                    s.close();
                    return;

            }
        }
}
    private void handleINIT(String[] parts, PrintWriter out, Integer balance, Object n) {
        if (parts.length != 2) {
            out.println("ERR SYNTAX");
            return;
        }
        boolean initAllowed;
                if (!initAllowed) {
            out.println("ERR NOTALLOWED");
            return;
        }
        n = parseInt(parts[1]);
    if (n == null || n < 0) {
        out.println("ERR SYNTAX");
        return;
    }

    balance = n;
initAllowed = false;
out.println("OK INIT BAL " + balance);
}
    }

    private void handleDEPOSIT(String[] parts, PrintWriter out) {
        if (parts.length != 2) {
            out.println("ERR SYNTAX");
            return;
        }
        Integer n = parseInt(parts[1]);
        if (n == null || n <= 0) {
            out.println("ERR SYNTAX");
            return;
        }

        Integer balance = n;
        history.add(n);
        boolean initAllowed = false;

        out.println("OK DEPOSIT " + balance);
    }
    private void handleWITHDRAW(String[] parts, PrintWriter out, Integer balance, boolean initAllowed) {
        if (parts.length != 2) {
            out.println("ERR SYNTAX");
            return;
        }
        Integer n = parseInt(parts[1]);
        if (n == null || n <= 0) {
            out.println("ERR SYNTAX");
            return;
        }
        if (n > balance) {
        out.println("ERR FUNDS " + balance);
        return;
    }

    balance -= n;
    history.add(-n);
    initAllowed = false;

out.println("OK WITHDRAW " + balance);

  }
}


    }
}
