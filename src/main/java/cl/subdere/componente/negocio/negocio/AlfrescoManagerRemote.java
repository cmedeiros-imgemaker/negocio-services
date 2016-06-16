package cl.subdere.componente.negocio.negocio;

import javax.ejb.Remote;

import cl.subdere.componente.negocio.exceptions.NegocioException;

@Remote
public interface AlfrescoManagerRemote {

	
	String saludar() throws NegocioException;
}
