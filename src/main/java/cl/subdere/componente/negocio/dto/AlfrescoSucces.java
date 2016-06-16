package cl.subdere.componente.negocio.dto;

public class AlfrescoSucces {

	private String nodeRef;
	private String fileName;
	private Status status;

	public AlfrescoSucces(String nodeRef, String fileName, Status status) {
		super();
		this.nodeRef = nodeRef;
		this.fileName = fileName;
		this.status = status;
	}

	public String getNodeRef() {
		return nodeRef;
	}

	public String getFileName() {
		return fileName;
	}

	public Status getStatus() {
		return status;
	}

}
