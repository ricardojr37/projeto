package perfis;

import java.lang.String;

public class PessoaFisica extends Perfil {
	private long cpf;
	
	public PessoaFisica(String usuario, long cpf) { //Construtor de perfil
		super(usuario); //coleta o construtor da classe abstrata Perfil
		this.setCpf(cpf); //Define o cpf
	}
	
	public void setCpf(long cpf) {
		this.cpf = cpf;
	} //Coletar cpf
	
	public long getCpf() {
		return this.cpf;
	} //obter cpf
}