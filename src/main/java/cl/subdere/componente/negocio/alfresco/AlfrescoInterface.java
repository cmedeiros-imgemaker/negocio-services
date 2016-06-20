package cl.subdere.componente.negocio.alfresco;

import java.io.File;

public interface AlfrescoInterface {

	
	public  String generarTicket();
	
	public  void uploadDocument(String authTicket, File fileobj,
			String filename, String filetype, String description,
			String destination);
}
