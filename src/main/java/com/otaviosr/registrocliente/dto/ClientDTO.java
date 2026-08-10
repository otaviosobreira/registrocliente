package com.otaviosr.registrocliente.dto;

import java.time.LocalDate;

import com.otaviosr.registrocliente.entity.Client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PositiveOrZero;

public class ClientDTO {

	private Long id;
	@NotBlank(message = "Campo requerido!")//age na camada de validação antes de chegar no BD, bloqueia nulos e vazios
	private String name;
	@NotBlank(message = "Campo requerido!")//age na camada de validação antes de chegar no BD, bloqueia nulos e vazios
	private String cpf;
	@PositiveOrZero(message = "Campo não pode ser negativo")//o cliente pode até ter renda zerada, mas não pode ter renda negativa
	private Double income;
	@Past(message = "A data precisa ser uma data passada")//garante que a data digitada será sempre uma data no passado
	private LocalDate birthDate;
	@PositiveOrZero(message = "O campo não pode ser negativo")//aceita zero ou numero positivo, mas bloqueia numeros negativos
	private Integer children;
	
	public ClientDTO() {
	}

	public ClientDTO(Long id, String name, String cpf, Double income, LocalDate birthDate, Integer children) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.income = income;
		this.birthDate = birthDate;
		this.children = children;
	}
	
	public ClientDTO(Client entity) {
		id = entity.getId();
		name = entity.getName();
		cpf = entity.getCpf();
		income = entity.getIncome();
		birthDate = entity.getBirthDate();
		children = entity.getChildren();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCpf() {
		return cpf;
	}

	public Double getIncome() {
		return income;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public Integer getChildren() {
		return children;
	}
}
