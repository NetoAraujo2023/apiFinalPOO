package com.example.model;

public class Moto extends Veiculo {
	private int cilindrada;

    public Moto() {}

    public Moto(int id, String placa, String modelo, int cilindrada) {
        super(id, placa, modelo);
        this.cilindrada = cilindrada;
    }

	public int getCilindrada() {
		return cilindrada;
	}

	public void setCilindrada(int cilindrada) {
		this.cilindrada = cilindrada;
	}
    
}
