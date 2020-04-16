package perfis.exceptions;

//Não existe o usuário
public class PIException extends Exception {
	private String usuario;
	
	public PIException(String usuario) {
		super("Usuário inexistente");
		this.usuario = usuario;
	}
	
	public String getMessage() {
		return "Este usuário não existe. [usuário: " + this.usuario + "]";
	}
	
	public String getUsuario() {
		return this.usuario;
	}
}