package cl.subdere.componente.negocio.exceptions;

public class NegocioException extends Exception {

	private int codigo;
	private String mensaje;

	public NegocioException() {
		super();
	}

	public NegocioException(int codigo, String mensaje) {
		super(mensaje);
		this.codigo = codigo;
		this.mensaje = mensaje;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
