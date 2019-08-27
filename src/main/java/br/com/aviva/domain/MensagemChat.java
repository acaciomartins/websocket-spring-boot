package br.com.aviva.domain;

public class MensagemChat {
	private String fromUserID;
	private String idPushDestino;
	private String mensagem;

	public String getFromUserID() {
		return this.fromUserID;
	}

	public void setFromUserID(final String fromUserID) {
		this.fromUserID = fromUserID;
	}

	public String getIdPushDestino() {
		return this.idPushDestino;
	}

	public void setIdPushDestino(final String idPushDestino) {
		this.idPushDestino = idPushDestino;
	}

	public String getMensagem() {
		return this.mensagem;
	}

	public void setMensagem(final String mensagem) {
		this.mensagem = mensagem;
	}

}
