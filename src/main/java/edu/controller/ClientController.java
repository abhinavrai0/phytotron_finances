package edu.controller;
import edu.exception.CustomException;
import edu.exception.DataNotFoundException;
import edu.model.*;
import edu.service.*;

import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/client")
public class ClientController {
	@Autowired
	private ClientCRUD clientCrudRepo;

	static final Logger logger = LogManager.getLogger(ClientController.class.getName());

	@RequestMapping(method=RequestMethod.GET)
	public List<Client> getAllClients() {
		List<Client> clientList =new ArrayList<Client>();
		clientList = (List<Client>) clientCrudRepo.findAll();
		return clientList;
	}
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public Client getClient(@PathVariable("id") Long id) {
		Client client=new Client();
		try {
			client = clientCrudRepo.findOne(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
		if (client == null) throw new DataNotFoundException();
		return client;
	}

	@RequestMapping(method=RequestMethod.POST)
	public Client createClient(@RequestBody Client client) {
		logger.info("Creating Client : {}", client);
		if(client.getClient_email() == null || client.getClient_email().isEmpty() 
				|| client.getClient_first_name()==null || client.getClient_first_name().isEmpty() 
				|| client.getClient_last_name()==null || client.getClient_last_name().isEmpty()){
			throw new IllegalArgumentException("Missing a mandatory field");
		}
		try {
			clientCrudRepo.save(client);
		}
		catch(DataIntegrityViolationException e){
			throw new IllegalArgumentException("Email id already exist");
		}
		catch (Exception e) {
			logger.error(e.getMessage());
			throw new CustomException();
		}
		return client;
	} 
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public Client updateClient(@PathVariable("id") Long id,@RequestBody Client updateClient) {
		updateClient.setId(id);
		if(updateClient.getClient_email() == null || updateClient.getClient_email().isEmpty() 
				|| updateClient.getClient_first_name()==null || updateClient.getClient_first_name().isEmpty() 
				|| updateClient.getClient_last_name()==null || updateClient.getClient_last_name().isEmpty()){
			throw new IllegalArgumentException("Missing a mandatory field");
		}
		try{
        clientCrudRepo.save(updateClient);
        }
		catch(DataIntegrityViolationException e){
			throw new IllegalArgumentException("Email id already exist");
		}
		catch (Exception e) {
			logger.error(e.getMessage());
			throw new IllegalArgumentException("Some error occured");
		}
		return updateClient;
	}
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public String deleteClient(@PathVariable("id") Long id) {
		return "Delete functionality not implemented!";
	}
}
