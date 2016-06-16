package cl.subdere.componente.negocio.negocio;

import javax.ejb.Remote;

import cl.subdere.componente.negocio.dto.AlfrescoSucces;
import cl.subdere.componente.negocio.exceptions.NegocioException;

@Remote
public interface AlfrescoManagerRemote {

	
	String generarTicket();
	
	
	AlfrescoSucces crearDocumento();
	
}
