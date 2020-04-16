package repositorio;

import perfis.Perfil;
import repositorio.exceptions.UJCException;
import repositorio.exceptions.UNCException;

public interface IRepositorioUsuario {
	public void cadastrar(Perfil usuario) throws UJCException;
	public Perfil buscar(String usuario);
	public void atualizar(Perfil usuario) throws UNCException;
}