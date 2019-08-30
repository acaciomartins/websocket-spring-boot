package br.com.aviva.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.aviva.model.UsuarioOnline;

@Repository
public interface UsuarioOnlineRepository extends CrudRepository<UsuarioOnline, Long> {

//	void removerUsuarioPorIdWebSocket(final String idWebSocket);
	
	UsuarioOnline findByIdPush(final String idPush);

}
