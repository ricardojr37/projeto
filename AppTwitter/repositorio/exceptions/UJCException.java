package repositorio.exceptions;

//Já existe
public class UJCException extends Exception {
	String usuario;
	
	public UJCException(String usuario) {
		super("Usuário já cadastrado");
		this.usuario = usuario;
	}
	
	public String getMessage() {
		return "O usuário " + this.usuario + "já está cadastrado!";
	}
	
	public String getUsuario() {
		return this.usuario;
	}
}