package com.tp1.Server;

import com.tp1.Server.entity.Client;
import com.tp1.Server.repository.ClientRepository;
import com.tp1.Server.service.Deviner;
import com.tp1.Server.thread.ServerConcurentImpl;
import com.tp1.Server.thread.ServerIterativeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Tp1ServerApplication implements CommandLineRunner {
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	private Deviner deviner;
	public static void main(String[] args)  {

		SpringApplication.run(Tp1ServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		/*for(int i=1;i<10;i++){
			clientRepository.save(new Client("192.168.1.2"+i,false,2));
		}*/
		/***
		 * Activer le serveur socket
		 */
		ServerIterativeImpl serverIterative =new ServerIterativeImpl(deviner);
		serverIterative.start();
	}
}
