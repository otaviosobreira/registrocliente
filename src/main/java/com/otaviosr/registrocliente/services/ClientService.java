package com.otaviosr.registrocliente.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.otaviosr.registrocliente.dto.ClientDTO;
import com.otaviosr.registrocliente.entity.Client;
import com.otaviosr.registrocliente.exceptions.ResourceNotFundException;
import com.otaviosr.registrocliente.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	
	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Client client = repository.findById(id).orElseThrow(
				()-> new ResourceNotFundException("Recurso não encontrado"));
		return new ClientDTO(client);
	}
}
