import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.xml.transform.ErrorListener;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import cl.subdere.componente.negocio.contracts.SendEmailInput;
import cl.subdere.componente.negocio.exceptions.NegocioException;
import cl.subdere.componente.negocio.negocio.SenderEmailManager;


public class TestSendEmail {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		
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

}
