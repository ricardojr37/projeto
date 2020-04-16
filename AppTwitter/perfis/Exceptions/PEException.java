package perfis.exceptions;

//Caso de erro quando se deseja criar um usuario ja existente
public class PEException extends Exception {
	private String usuario;
	
	public PEException(String usuario) {
		super("Perfil já existe");
		this.usuario = usuario;
	}
	
	public String getMessage() {
		return "Este usuário já existe. [usuário : " + this.usuario + "]";
	}
	
	public String getUsuario() {
		return this.usuario;
	}
}