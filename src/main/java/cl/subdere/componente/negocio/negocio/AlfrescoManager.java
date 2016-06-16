package cl.subdere.componente.negocio.negocio;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import cl.subdere.componente.negocio.alfresco.UtilidadesAlfresco;
import cl.subdere.componente.negocio.dto.AlfrescoSucces;

/**
 * Session Bean implementation class AlfrescoManager
 */
@Stateless
@LocalBean
public class AlfrescoManager implements AlfrescoManagerRemote, AlfrescoManagerLocal {


	@Inject 
	private UtilidadesAlfresco objUtilidadesAlfresco;
	
    public AlfrescoManager() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public String generarTicket() {
		return objUtilidadesAlfresco.generarTicket();
	}

	@Override
	public AlfrescoSucces crearDocumento() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
