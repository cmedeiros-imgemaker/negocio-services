package cl.subdere.componente.negocio.dto;

public class Status {

    private  long code;
    private  String name;
    private  String description;
	public Status() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Status(long code, String name, String description) {
		super();
		this.code = code;
		this.name = name;
		this.description = description;
	}
	public long getCode() {
		return code;
	}
	public void setCode(long code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
    
    
    
    
    
}
