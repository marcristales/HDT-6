import java.util.Scanner;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		boolean correr = true;
		
		Scanner input = new Scanner(System.in);
		Tienda tienda = new Tienda();
		
		System.out.println("Ingrese que MAP implementara: ");
		System.out.println("1. HashMAP");
		System.out.println("2. TreeMAP");
		System.out.println("3. LinkedHashMap");
		int option = Integer.parseInt(input.nextLine());
		
		tienda.initFactory(option);
		tienda.readFile();
		
		IMap<String, List<String>> coleccion = tienda.getColeccion();
		IMap<String, List<String>> inventario = tienda.getInventario();
		
        while (correr) {
            System.out.println("\n Tienda \n");

            System.out.println("1. Agregar un producto a su coleccion");
            System.out.println("2. Mostrar datos de un producto");
            System.out.println("3. Mostrar su coleccion");
            System.out.println("4. Mostrar coleccion del usuario (Ordenada)");
            System.out.println("5. Mostrar inventario");
            System.out.println("6. Mostrar inventario (Ordenado)");
            System.out.println("7. Salir");
            int choice = Integer.parseInt(input.nextLine());
            
            switch (choice) {
            
            case 1:
            	tienda.agregarProducto();
            	break;
            	
            case 2:
            	tienda.mostrarCategoria();
            	break;
            	
            case 3:
            	System.out.println("\n Su Coleccion \n");
            	tienda.mostrarIMAP(coleccion, false);
            	break;
            	
            case 4: 
            	System.out.println("\n Coleccion Ordenada\n");
            	tienda.mostrarIMAP(coleccion, true);
            	break;
            	
            case 5:
            	System.out.println("\n Su Inventario \n");
            	tienda.mostrarIMAP(inventario, false);
            	break;
            	
            case 6:
            	System.out.println("\n Inventario Ordenado \n");
            	tienda.mostrarIMAP(inventario, true);
            	break;
            	
            case 7:
            	System.out.println("Cerrando programa");
            	correr = false;
            	break;
            	
            default:
            	System.out.println("Ingrese una opcion valida...");
            }
            
        }
        
	}

}
