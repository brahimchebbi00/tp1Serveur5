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

//Exercice 6
@Service

public class DevinerImplV2 implements Deviner {
    private final ClientRepository clientRepository;

    /**
     Injection de la dépendance de la base de données par le constructeur     */
    @Autowired
    public DevinerImplV2(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public String checkNumbre(int estimatedNum, int secretNum, Client client) {
        char[] chEst = String.valueOf(estimatedNum).toCharArray();
        int v=0;
        int t=0;
        String chSecret =String.valueOf(secretNum);
        for (int i=0;i< chEst.length;i++){
            if (chSecret.contains(chEst[i]+"")){
                if(chSecret.charAt(i)==chEst[i]){
                    t++;
                }
              else{
                  v++;
                }
            }
        }
        if (t==5){
            client.setWinner(true);
            return "vous avez ganger";}

        return "t:"+t+"v:"+v;
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
