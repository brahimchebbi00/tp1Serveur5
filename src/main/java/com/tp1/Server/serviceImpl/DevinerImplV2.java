package com.tp1.Server.serviceImpl;

import com.tp1.Server.entity.Client;
import com.tp1.Server.service.Deviner;

import java.io.IOException;
import java.net.Socket;

public class DevinerImplV2 implements Deviner {
    @Override
    public String checkNumbre(int estimatedNum, int secretNum, Client client) {
        char[] chEst=String.valueOf(estimatedNum).toCharArray();
        String chSecret=String.valueOf(secretNum);
        int t=0;
        int v=0;
        for (int i = 0; i<chEst.length;i++){
            if (chSecret.contains(""+chEst[i])){
                if (chSecret.charAt(i)==chEst[i]){
                    t++;

                }
                else
                    v++;
            }

        }
        return "t:"+t+"v"+v;
    }

    @Override
    public int getNumbre(Socket socket) throws IOException {
        return 0;
    }

    @Override
    public void sendResult(String result, Socket socket) throws IOException {

    }

    @Override
    public void makeDecision(Socket socket, int secretNum) throws IOException {

    }
}
