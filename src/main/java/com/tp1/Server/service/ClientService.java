package com.tp1.Server.service;

import com.tp1.Server.entity.Client;

import java.util.List;

public interface ClientService {
    Client getWinner();

    List<Client> getAllClient();

    List<Client> getAllLosedClient();

    List<Client> getAllPlayingClient();

    int countLosedClient();

    List<Client> getClientsByiteration(int iteration);

    Client updateClient(Client client);

}
