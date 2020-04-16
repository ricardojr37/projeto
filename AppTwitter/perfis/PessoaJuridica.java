package perfis;

import java.lang.String;

public class PessoaJuridica extends Perfil {
	private long cnpj;
	
	public PessoaJuridica(String usuario, long cnpj) {
		super(usuario); //coleta o construtor da classe abstrata Perfil
		this.setCnpj(cnpj); //coleta o CNPJ
	}
	
	public void setCnpj(long cnpj) {
		this.cnpj = cnpj;
	} // armazena o cnpj
	
	public long getCnpj() {
		return this.cnpj;
	}// mostra o cnpj
}