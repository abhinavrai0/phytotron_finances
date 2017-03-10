package edu.controller;
import edu.model.*;
import edu.service.*;

import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/client")
public class ClientController {
	@Autowired
	private ClientCRUD clientCrudRepo;

	static final Logger logger = LogManager.getLogger(ClientController.class.getName());

//	@RequestMapping(value="/client/",method=RequestMethod.GET)
	@RequestMapping(method=RequestMethod.GET)
	public List<Client> getAllClients() {
		List<Client> clientList =new ArrayList<Client>();
		System.out.println("aaaaaaa"+clientList);
		clientList = (List<Client>) clientCrudRepo.findAll();
		System.out.println("a :"+clientList);
		return clientList;
	}
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public Client getClient(@PathVariable("id") Long id) {
		Client client=new Client();
		try {
			client = clientCrudRepo.findOne(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
//            return e.getMessage();
        }
		System.out.println("Client :"+client);
		return client;
	}

//	@RequestMapping(value="/client/",method=RequestMethod.POST)
	@RequestMapping(method=RequestMethod.POST)
	public Client createClient(@RequestBody Client client) {
		System.out.println("--====Post Request=====--");
		logger.info("Creating Client : {}", client);
		/* if (clientCrudRepo.exists(client.getId()) {
            logger.error("Unable to create. A User with name {} already exist", user.getName());
            return new ResponseEntity(new CustomErrorType("Unable to create. A User with name " + 
            user.getName() + " already exist."),HttpStatus.CONFLICT);
        }*/
		try {
			clientCrudRepo.save(client);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return client;
//		return "creation successful: " + String.valueOf(client.getId());
	} 
//	@RequestMapping(value="/client/{id}",method=RequestMethod.PUT)
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public Client updateClient(@PathVariable("id") Long id,@RequestBody Client updateClient) {
		Client client= new Client();
		client=clientCrudRepo.findOne(id);
		if(client.getAddress()!=updateClient.getAddress()){
			client.setAddress(updateClient.getAddress());
		}
		if(client.getDept_name()!=updateClient.getDept_name()){
			client.setDept_name(updateClient.getDept_name());
		}
		if(client.getProject_client_email()!=updateClient.getProject_client_email()){
			client.setProject_client_email(updateClient.getProject_client_email());
		}
		if(client.getProject_client_name()!=updateClient.getProject_client_name()){
			client.setProject_client_name(updateClient.getProject_client_name());
		}
        clientCrudRepo.save(client);
		return client;
	}
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public String deleteClient(@PathVariable("id") Long id) {
		return "Greetings from Spring Boot!";
	}
}
