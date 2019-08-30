package br.com.aviva.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import br.com.aviva.model.TabelaUsuarioOnline;
import br.com.aviva.repository.UsuarioOnlineRepository;

@Component
public class WebSocketEventListener {

//	@Autowired
//	private UsuarioOnlineRepository usuarioOnlineRepository;
	
	@EventListener
	public void handleWebSocketConnectListener(SessionConnectedEvent event) {
		System.out
				.println("Received a new web socket connection" + event.getMessage().getHeaders().get("simpSessionId"));
	}

	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
		//TODO: REMOVER mock
		TabelaUsuarioOnline.getInstance().removerUsuarioPorIdSocket(event.getSessionId());
		
		// Vai remover da tabela de usuarios conectados
//		usuarioOnlineRepository.removerUsuarioPorIdWebSocket(event.getSessionId());
	}
}
