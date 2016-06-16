package cl.subdere.componente.negocio.negocio;

import javax.ejb.Local;

import cl.subdere.componente.negocio.dto.AlfrescoSucces;
import cl.subdere.componente.negocio.exceptions.NegocioException;

@Local
public interface AlfrescoManagerLocal {

	String generarTicket();
	
	AlfrescoSucces crearDocumento();
}
