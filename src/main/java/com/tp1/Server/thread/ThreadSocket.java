package com.tp1.Server.thread;

import com.tp1.Server.service.Deviner;

import java.net.Socket;

public class ThreadSocket extends Thread {
    private final Socket socket;

    private final Deviner deviner;

    private final int secretNum;

    public ThreadSocket(Socket socket, Deviner deviner, int secretNum) {
        this.socket = socket;
        this.deviner = deviner;
        this.secretNum = secretNum;
    }

    @Override
    public void run() {
        try {
            deviner.makeDecision(socket, secretNum);
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

