package cl.subdere.componente.negocio.dto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public enum MessageBody {
	
	DEFAULT(0, "Por Defecto", ""),
	SOLICITUD_MODIFICACION_INTERNA(1, "Unidad de Control", "");
	
	private final Integer number;
	private final String model;
	private final String format;
	private static final String DEFAULT_FORMAT = "default_format";
	
	private MessageBody(Integer number, String model, String format) {
		this.number = number;
		this.model = model;
		this.format = format.isEmpty() ? DEFAULT_FORMAT : format;
	}

	public Integer getNumber() {
		return number;
	}

	public String getModel() {
		return model;
	}
	
	public String getFormat() {
		return format;
	}
	
	public String getContent() {
		String content = "";
		try {
			Path path = Paths.get( 
					System.getProperty("user.home"), 
					System.getProperty("mail.format.repository", "mail-formats"), 
					getFormat()
			);
			content = new String(Files.readAllBytes(path));
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		return content;
	}
	
	public static MessageBody getByNumero(Integer number) {
		MessageBody[] listFormats = MessageBody.values();
		for (MessageBody messageBody : listFormats) {
			if (messageBody.getNumber().equals(number)) {
				return messageBody;
			}
		}
		return MessageBody.DEFAULT;
	}
}
