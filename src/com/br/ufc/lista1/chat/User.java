package com.br.ufc.lista1.chat;

import java.io.Serializable;
import java.net.Socket;

public class User implements Serializable{

	private Long id;
	private String nome;
	private transient Socket socket;
	
	public User(String nome) {
		this.nome = nome;
	}

	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public Socket getSocket() {
		return socket;
	}


	public void setSocket(Socket socket) {
		this.socket = socket;
	}	
}
