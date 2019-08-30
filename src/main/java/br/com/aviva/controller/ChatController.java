package br.com.aviva.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import br.com.aviva.domain.MensagemChat;
import br.com.aviva.domain.MensagemOnline;
import br.com.aviva.domain.Response;
import br.com.aviva.model.TabelaUsuarioOnline;
import br.com.aviva.model.UsuarioOnline;
import br.com.aviva.repository.UsuarioOnlineRepository;

@Controller
public class ChatController {

	@Autowired
	private UsuarioOnlineRepository usuarioOnlineRepository;

	private final SimpMessagingTemplate simpMessagingTemplate;

	public ChatController(SimpMessagingTemplate simpMessagingTemplate) {
		this.simpMessagingTemplate = simpMessagingTemplate;
	}

	@MessageMapping("/online")
	public void online(final MensagemOnline mensagemOnline, @Header("simpSessionId") final String sessionId) {
		// TODO: REMOVER Mock
		TabelaUsuarioOnline.getInstance().getUsuarioConectados().put(mensagemOnline.getIdPush(), sessionId);

		// Salva na tabela UsuarioOnline o idPush e o idWebSocket
		final UsuarioOnline usuarioOnline = new UsuarioOnline();
		usuarioOnline.setIdPush(mensagemOnline.getIdPush());
		usuarioOnline.setIdWebSocket(sessionId);
		if (usuarioOnlineRepository.findByIdPush(mensagemOnline.getIdPush()) == null) {
			usuarioOnlineRepository.save(usuarioOnline);
		}
		
		final Response response = new Response();
		response.setSessionId(sessionId);
		simpMessagingTemplate.convertAndSendToUser(mensagemOnline.getIdPush(), "/online", response);
	}

	@MessageMapping("/chat")
	public void chat(final MensagemChat chatMessage, @Header("simpSessionId") final String sessionId) {
		// TODO: remover mock
		// if (null !=
		// TabelaUsuarioOnline.getInstance().getUsuarioConectados().get(chatMessage.getIdPushDestino()))
		// {

		// Consulta Usuario Online
		final UsuarioOnline usuarioOnline = usuarioOnlineRepository.findByIdPush(chatMessage.getIdPushDestino());

		if (null != usuarioOnline) {
			final Response response = new Response(chatMessage.getMensagem());
			simpMessagingTemplate.convertAndSendToUser(
					TabelaUsuarioOnline.getInstance().getUsuarioConectados().get(usuarioOnline.getIdPush()), "/msg",
					response);
		} else {
			// Chama push
		}
	}

}
