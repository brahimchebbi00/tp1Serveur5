package com.tp1.Server.service;

import com.tp1.Server.entity.Client;

import java.io.IOException;
import java.net.Socket;

public interface Deviner {
    /**Vérifier si le nombre secret est égal au nombre estimé.
     * Mettre à jour le client s'il a fait une bonne proposition
     **/
     String checkNumbre(int estimatedNum , int secretNum, Client client) ;
     /**Lire un nombre reçu à travers la socket
      **/
     int getNumbre(Socket socket) throws IOException;
     /**Envoyer un nombre reçu à travers la socket
      **/
     void sendResult(String result,Socket socket) throws IOException;
     /**Lire l'adresse du client.
      Vérifier l'état du client.
      Décider s'il est gagnant.
      Envoyer le résultat.
      **/
     void makeDecision(Socket socket,int secretNum) throws IOException;
}
