package com.tp1.Server.controller;

import com.tp1.Server.entity.Client;
import com.tp1.Server.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {
    @Autowired
    ClientService clientService;

    @CrossOrigin("*")
    @GetMapping("/winner")
    public Client getWinner() {
        return clientService.getWinner();
    }

    @CrossOrigin("*")
    @GetMapping("/clients")
    public List<Client> getAllClient() {
        return clientService.getAllClient();
    }

    @CrossOrigin("*")
    @GetMapping("/LosedClients")
    public List<Client> getAllLosedClient() {
        return getAllLosedClient();
    }

    @CrossOrigin("*")
    @GetMapping("/playingClients")
    public List<Client> getAllPlayingClient() {
        return clientService.getAllPlayingClient();
    }

    @CrossOrigin("*")
    @GetMapping("/numberOfLosedClients")
    public int countLosedClient() {
        return clientService.countLosedClient();
    }

    @CrossOrigin("*")
    @GetMapping("/clientsByiteration/{iteration}")
    public List<Client> getClientsByiteration(@PathVariable("iteration") int iteration) {
        return clientService.getClientsByiteration(iteration);
    }

    @CrossOrigin("*")
    @PostMapping("/updateClient")
    public Client updateClient(@RequestBody Client client) {
        return clientService.updateClient(client);
    }
}
