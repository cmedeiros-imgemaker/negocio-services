package cl.subdere.componente.negocio.contracts;

import java.io.Serializable;
import java.util.ArrayList;

public class SendEmailInput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String to;
	private String subject;
	private Integer type;
	private ArrayList<String> cc;
	
	public SendEmailInput() {
	}
	
	@Override
	public String toString() {
		return "SendEmailInput [to=" + to + ", topic="
				+ subject + ", type=" + type + ", cc=" + cc + "]";
	}
	
	public String getTo() {
		return to;
	}
	
	public void setTo(String to) {
		this.to = to;
	}
	
	public ArrayList<String> getCc() {
		return cc;
	}
	
	public void setCc(ArrayList<String> cc) {
		this.cc = cc;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public Integer getType() {
		return type;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
}
