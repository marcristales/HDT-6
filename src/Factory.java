import java.util.List;

/**
 * @author mario
 *
 */
public class Factory {

	//se crea un metodo de interfaz IMap para crear la clase de factory.
	//Factory permite que las subclases decidan que objeto instanciar.
	/**
	 * @param mapa
	 * @return el objeto instanciado
	 */
	public IMap<String, List<String>> mapType(Integer mapa) {

		switch (mapa) {
		
		case 1:
			
			return new HashMap<String, List<String>>();
		
		case 2:
			
			return new TreeMap<String, List<String>>();
		
		case 3:
			
			return new LinkedHashMap<String, List<String>>();
		
		default:
		
			throw new IllegalArgumentException("Mapa no reconocido");
		
		}
    }
	
}
