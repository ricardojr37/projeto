package repositorio;

import perfis.Perfil;
import java.util.Vector;
import repositorio.exceptions.UJCException;
import repositorio.exceptions.UNCException;

public class RepositorioVector implements IRepositorioUsuario {
	private Vector<Perfil> usersRepo;
	
	public RepositorioVector() { //Construtor 
		usersRepo = new Vector<Perfil>();
	}
	
	public Perfil buscar(String usuario) { //Busca pelo usuário
		for (int i = 0; i < usersRepo.size(); i++) {
			if (usersRepo.get(i).getUsuario().equals(usuario)) {
				return usersRepo.get(i);
			}
		}
		return null;
	}

	public void cadastrar(Perfil usuario) throws UJCException{
		if (buscar(usuario.getUsuario()) == null) { //Usuário não existe
			this.usersRepo.add(usuario); //cadastra o usuario
		}
		else {
			throw new UJCException(usuario.getUsuario()); //usuario já está cadastrado
		}
	}

	//Atualiza informações de um usuário
	public void atualizar(Perfil usuario) throws UNCException{
		Perfil usuarioVelho = buscar(usuario.getUsuario());
		if (usuarioVelho == null) {
			throw new UNCException(usuario.getUsuario()); //usuário não existe
		} else {
			usuarioVelho = usuario;
		}
	}
}