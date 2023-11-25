package com.tp1.Server.serviceImpl;

import com.tp1.Server.entity.Client;
import com.tp1.Server.repository.ClientRepository;
import com.tp1.Server.service.Deviner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Optional;

@Service
public class DevinerImpl implements Deviner {

    private final ClientRepository clientRepository;

    /**
     Injection de la dépendance de la base de données par le constructeur     */
    @Autowired
    public DevinerImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public String checkNumbre(int estimatedNum, int secretNum, Client client) {
        try {
            if (secretNum > estimatedNum) {
                return estimatedNum + " est plus petit";
            } else if (secretNum < estimatedNum) {
                return estimatedNum + " est plus grand";
            }
            client.setWinner(true);
            return "vous avez gangner";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public int getNumbre(Socket socket) throws IOException {
        InputStream is = socket.getInputStream();
        return is.read();
    }

    @Override
    public void sendResult(String result, Socket socket) throws IOException {
        OutputStream os = socket.getOutputStream();
        PrintWriter pw = new PrintWriter(os, true);
        pw.println(result);
    }

    @Override
    public synchronized void makeDecision(Socket socket, int secretNum) throws IOException {
        int countWinner = clientRepository.countClientsByWinner(true);
        String address = socket.getRemoteSocketAddress().toString().split(":")[0];

        if (countWinner == 0) {
            Optional<Client> client = clientRepository.findClientByIpAdress(address);
            int estimatedNum = getNumbre(socket);
            if (client.isPresent()) {
                if (client.get().getIteration() > 5) {
                    sendResult("you lost ", socket);
                } else {
                    client.get().setIteration(client.get().getIteration() + 1);
                    sendResult(checkNumbre(estimatedNum, secretNum, client.get()), socket);
                    clientRepository.save(client.get());
                }
            } else {
                Client client1 = new Client(address, 1);
                sendResult(checkNumbre(estimatedNum, secretNum, client1), socket);
                clientRepository.save(client1);
            }
        } else {
            sendResult("game finished ", socket);
        }
    }

}
