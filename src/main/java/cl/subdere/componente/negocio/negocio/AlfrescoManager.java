package cl.subdere.componente.negocio.negocio;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import cl.subdere.componente.negocio.alfresco.UtilidadesAlfresco;
import cl.subdere.componente.negocio.dto.AlfrescoSucces;

/**
 * Session Bean implementation class AlfrescoManager
 */
@Stateless
@LocalBean
public class AlfrescoManager implements AlfrescoManagerRemote, AlfrescoManagerLocal {



	private UtilidadesAlfresco objUtilidadesAlfresco;
	
    public AlfrescoManager() {
    	objUtilidadesAlfresco = new UtilidadesAlfresco();
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
