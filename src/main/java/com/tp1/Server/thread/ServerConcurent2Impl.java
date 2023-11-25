package com.tp1.Server.thread;

import com.tp1.Server.service.Deviner;
import com.tp1.Server.service.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerConcurent2Impl extends Thread implements Server {
    Deviner deviner;

    public ServerConcurent2Impl(Deviner deviner) {
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
             * choisir un int random entre 0 et 100*/
            Random random = new Random();
            int secretNum = random.nextInt(101);
            /**
             * Accepter les connexions entrantes*/
            ExecutorService executorService = Executors.newFixedThreadPool(10);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Nouvelle connexion entrante : " + socket);
                executorService.submit(new ThreadSocket(socket, deviner, secretNum));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
