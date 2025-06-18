package com.example.model;

public class Produto {
	private int id;
    private String nomeProduto;
    private double preco;
    private String categoria;

    public Produto() {}

    public Produto(int id, String nomeProduto, double preco, String categoria) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.preco = preco;
        this.categoria = categoria;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

    
}
