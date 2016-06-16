package cl.subdere.componente.negocio.negocio;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import cl.subdere.componente.negocio.exceptions.NegocioException;

/**
 * Session Bean implementation class AlfrescoManager
 */
@Stateless
@LocalBean
public class AlfrescoManager implements AlfrescoManagerRemote, AlfrescoManagerLocal {

    /**
     * Default constructor. 
     */
    public AlfrescoManager() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public String saludar() throws NegocioException {
		// TODO Auto-generated method stub
		return null;
	}

}
