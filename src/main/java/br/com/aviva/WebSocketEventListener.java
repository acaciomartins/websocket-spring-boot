package br.com.aviva;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import br.com.aviva.model.TabelaUsuarioOnline;

@Component
public class WebSocketEventListener {

	@Autowired
	private SimpMessageSendingOperations messagingTemplate;

	@EventListener
	public void handleWebSocketConnectListener(SessionConnectedEvent event) {
		System.out
				.println("Received a new web socket connection" + event.getMessage().getHeaders().get("simpSessionId"));
	}

	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
		// Vai remover da tabela de usuarios conectados
		TabelaUsuarioOnline.getInstance().removerUsuarioPorIdSocket(event.getSessionId());
	}
}
