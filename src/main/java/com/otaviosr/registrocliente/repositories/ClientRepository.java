package com.otaviosr.registrocliente.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.otaviosr.registrocliente.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
