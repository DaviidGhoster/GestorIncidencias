package excepciones;



public class UsuarioException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6504893836344096464L;

	public UsuarioException() {
		
	}
	
	public UsuarioException(String mensaje) {
		super(mensaje);
	}

}
