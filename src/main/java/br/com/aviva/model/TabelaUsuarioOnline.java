package br.com.aviva.model;

import java.util.HashMap;
import java.util.Map;

public class TabelaUsuarioOnline {

	private static TabelaUsuarioOnline instance = null;

	private Map<String, String> usuarioConectados = new HashMap<String, String>();

	public static TabelaUsuarioOnline getInstance() {
		if (instance == null) {
			instance = new TabelaUsuarioOnline();
		}
		return instance;
	}

	public Map<String, String> getUsuarioConectados() {
		return usuarioConectados;
	}

	public void setUsuarioConectados(Map<String, String> usuarioConectados) {
		this.usuarioConectados = usuarioConectados;
	}
	
	public void removerUsuarioPorIdSocket(final String idWebSocket) {
		this.usuarioConectados.entrySet().removeIf(entry -> idWebSocket.equals(entry.getValue()));
		System.out.println("desconectado");
	}

}
