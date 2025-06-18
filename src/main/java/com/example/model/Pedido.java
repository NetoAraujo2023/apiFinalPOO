package com.example.model;

import java.time.LocalDate;

public class Pedido {
	private int id;
    private String codigoPedido;
    private LocalDate dataPedido;
    private Cliente cliente;
    private Funcionario entregador;

    public Pedido() {}

    public Pedido(int id, String codigoPedido, LocalDate dataPedido, Cliente cliente, Funcionario entregador) {
        this.id = id;
        this.codigoPedido = codigoPedido;
        this.dataPedido = dataPedido;
        this.cliente = cliente;
        this.entregador = entregador;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigoPedido() {
		return codigoPedido;
	}

	public void setCodigoPedido(String codigoPedido) {
		this.codigoPedido = codigoPedido;
	}

	public LocalDate getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Funcionario getEntregador() {
		return entregador;
	}

	public void setEntregador(Funcionario entregador) {
		this.entregador = entregador;
	}

    
    
}
