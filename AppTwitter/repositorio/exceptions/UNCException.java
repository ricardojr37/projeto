package repositorio.exceptions;

//Usuário não cadastrado
public class UNCException extends Exception {
	String usuario;
	public UNCException(String usuario) {
		super("Usuário não cadastrado");
		this.usuario = usuario;
	}
	
	public String getMessage() {
		return "Não existe nenhum usuário cadastrado com o nome " + this.usuario;
	}
	
	public String getUsuario() {
		return this.usuario;
	}
}