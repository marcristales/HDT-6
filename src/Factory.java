import java.util.List;

public class Factory {

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
