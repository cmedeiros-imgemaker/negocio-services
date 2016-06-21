import java.util.ArrayList;

import cl.subdere.componente.negocio.contracts.SendEmailInput;
import cl.subdere.componente.negocio.exceptions.NegocioException;
import cl.subdere.componente.negocio.negocio.AlfrescoManager;
import cl.subdere.componente.negocio.negocio.SenderEmailManager;


public class TestSendEmail {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) {
		
		
		SendEmailInput input = new SendEmailInput();
		
		ArrayList<String> listCc = new ArrayList<String>();
		
		input.setTo("cmedeiros@imagemaker.cl");
		input.setCc(listCc);
		input.setSubject("Prueba de concepto");
		input.setType(1);
		
		SenderEmailManager mailManager = new SenderEmailManager();
		try {
			for (Integer i = 0; i < 1; i++) {
				input.setSubject("Nuevo contrato ha llegado");
				mailManager.sendAsync(input);
			}
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	}
		
		
		/*
		AlfrescoManager objAlfrescoManager =  new AlfrescoManager();
		
		System.out.println(objAlfrescoManager.generarTicket());
		*/
		
}
