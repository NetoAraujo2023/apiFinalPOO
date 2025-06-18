package com.example.model;

import java.time.LocalDate;

public class Cliente extends Pessoa {
	private LocalDate dataNascimento;
	private String cpf;

	public Cliente() {
	}

	public Cliente(int id, String nome, LocalDate dataNascimento, String cpf) {
		super(id, nome);
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	
}
