package com.tp1.Server.thread;

import com.tp1.Server.service.Deviner;
import com.tp1.Server.service.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class ServerIterativeImpl extends Thread implements Server {
    Deviner deviner;

    public ServerIterativeImpl(Deviner deviner) {
        this.deviner = deviner;
    }

    @Override
    public void run() {
        startGame();
    }

    public void startGame() {
        try {
            // Créez un serveur socket qui écoute sur un port spécifique (par exemple, 8080)
            ServerSocket serverSocket = new ServerSocket(8071);
            System.out.println("Serveur socket en écoute sur le port " + serverSocket.getLocalPort());
            /**
             * choisir un entier random entre 0 et 100*/
            Random random = new Random();
            int secretNum = random.nextInt(101);
            /**
             * Accepter les connexions entrantes*/
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nouvelle connexion entrante : " + clientSocket);
                /** appeler le service deviner pour repondre au client*/
                deviner.makeDecision(clientSocket, secretNum);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
