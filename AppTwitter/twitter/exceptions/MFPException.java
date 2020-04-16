package twitter.exceptions;

//Mensagem que não segue as regras do Twitter
public class MFPException extends Exception {
	private String mensagem;
	public MFPException(String mensagem) {
		super("Mensagem fora do padrão");
		this.mensagem = mensagem;
	}
	
	public String getMessage() {
		if (mensagem.length() < 1) {
			return "Mensagem muito curta. O tweet deve possuir 1-150 carácteres.";
		} else {
			return "Mensagem muito longa. O tweet deve possuir 1-150 carácteres.";
		}
	}
	
	public String getMensagemEnviada() {
		return this.mensagem;
	}
}