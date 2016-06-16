package cl.subdere.componente.negocio.negocio;

import javax.ejb.Remote;

import cl.subdere.componente.negocio.contracts.SendEmailInput;
import cl.subdere.componente.negocio.exceptions.NegocioException;

@Remote
public interface SenderEmailManagerRemote {

	Boolean send(SendEmailInput input) throws NegocioException;
	
	Boolean sendAsync(SendEmailInput input) throws NegocioException;
}
