package com.tp1.Server.repository;

import com.tp1.Server.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    int countClientsByWinner(boolean winner);

    Optional<Client> findClientByIpAdress(String address);

    List<Client> findAll();

    List<Client> findAllByIteration(int iteration);

    Optional<Client> findOneByWinner(boolean winner);

    List<Client> findClientsByWinnerAndIterationGreaterThan(boolean winner, int iteration);

    List<Client> findClientsByWinnerAndIterationBetween(boolean winner, int inf, int sup);

    int countClientsByWinnerAndIteration(boolean winner, int iteration);
}
