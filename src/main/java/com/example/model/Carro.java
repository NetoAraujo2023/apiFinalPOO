package com.example.model;

public class Carro extends Veiculo {
	private int quantidadeDePortas;

	public Carro() {
	}

	public Carro(int id, String placa, String modelo, int quantidadeDePortas) {
		super(id, placa, modelo);
		this.quantidadeDePortas = quantidadeDePortas;
	}

	public int getQuantidadeDePortas() {
		return quantidadeDePortas;
	}

	public void setQuantidadeDePortas(int quantidadeDePortas) {
		this.quantidadeDePortas = quantidadeDePortas;
	}
	
	
}
