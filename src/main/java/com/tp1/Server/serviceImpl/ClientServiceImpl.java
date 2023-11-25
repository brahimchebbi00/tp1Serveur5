package com.tp1.Server.serviceImpl;

import com.tp1.Server.entity.Client;
import com.tp1.Server.repository.ClientRepository;
import com.tp1.Server.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepository clientRepository;

    @Override
    public Client getWinner() {
        try {
            return clientRepository.findOneByWinner(true).orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Client> getAllClient() {
        try {
            return clientRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Client> getAllLosedClient() {
        try {
            return clientRepository.findClientsByWinnerAndIterationGreaterThan(false, 5);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Client> getAllPlayingClient() {
        try {
            return clientRepository.findClientsByWinnerAndIterationBetween(false, 1, 5);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int countLosedClient() {
        try {
            return clientRepository.findClientsByWinnerAndIterationGreaterThan(false, 6).size();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Client> getClientsByiteration(int iteration) {
        try {
            return clientRepository.findAllByIteration(iteration);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Client updateClient(Client client) {
        try {
            return clientRepository.save(client);
        } catch (Exception e) {
            e.printStackTrace();
            return client;
        }
    }
}
