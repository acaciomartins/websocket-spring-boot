package br.com.aviva.controller;

import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import br.com.aviva.domain.MensagemChat;
import br.com.aviva.domain.MensagemOnline;
import br.com.aviva.domain.Response;
import br.com.aviva.model.TabelaUsuarioOnline;

@Controller
public class MessageController {

	private final SimpMessagingTemplate simpMessagingTemplate;

	public MessageController(SimpMessagingTemplate simpMessagingTemplate) {
		this.simpMessagingTemplate = simpMessagingTemplate;
	}

	@MessageMapping("/online")
	public void online(final MensagemOnline mensagemOnline, @Header("simpSessionId") final String sessionId) {
		TabelaUsuarioOnline.getInstance().getUsuarioConectados().put(mensagemOnline.getIdPush(), sessionId);
		final Response response = new Response();
		response.setSessionId(sessionId);
		simpMessagingTemplate.convertAndSendToUser(mensagemOnline.getIdPush(), "/online", response);
	}

	@MessageMapping("/chat")
	public void chat(final MensagemChat chatMessage, @Header("simpSessionId") final String sessionId) {
		if (null != TabelaUsuarioOnline.getInstance().getUsuarioConectados().get(chatMessage.getIdPushDestino())) {
			final Response response = new Response(chatMessage.getMensagem());
			simpMessagingTemplate.convertAndSendToUser(
					TabelaUsuarioOnline.getInstance().getUsuarioConectados().get(chatMessage.getIdPushDestino()),
					"/msg", response);
		} else {
			// Chama push
		}
	}

}
