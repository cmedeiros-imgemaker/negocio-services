package cl.subdere.componente.negocio.negocio;

import javax.ejb.Local;

import cl.subdere.componente.negocio.contracts.SendEmailInput;
import cl.subdere.componente.negocio.exceptions.NegocioException;


@Local
public interface SenderEmailManagerLocal {

	Boolean send(SendEmailInput input) throws NegocioException;
	
	Boolean sendAsync(SendEmailInput input) throws NegocioException;
}
