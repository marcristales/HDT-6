import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Tienda {

	//Scanner
	Scanner input = new Scanner(System.in);
	
	//File Reader
	String source = "./ListadoProducto.txt";
	File file = null;
	FileReader fileReader = null;
	BufferedReader br = null;
	
	//Factory
	private Factory factory = new Factory();
	private IMap<String, List<String>> coleccion;
    private IMap<String, List<String>> inventario;
    private List<String> categorias = new ArrayList<String>();
	
	
	public void initFactory(int option) {
		
		this.coleccion = factory.mapType(option);
		this.inventario = factory.mapType(option);
		
	}
	
	public void readFile() {
		
		try 
		{
			file = new File(source);
			fileReader = new FileReader(file);
			br = new BufferedReader(fileReader);
		} catch (Exception e) 
		{
			System.out.println("No existe el archivo.");
		}
		
		try 
		{
			String texto = br.readLine();
			
			while (texto != null) {
				
				String[] productos = texto.replace("|", "-").split("-");
				String categoria = productos[0].trim().toUpperCase();
				String producto = productos[1].trim();
				
				try 
				{
					
					if (inventario.get(categoria) != null) {
						
						List<String> lista = inventario.get(categoria);
						lista.add(producto);
						inventario.put(categoria, lista);
					} else
					{
						List<String> nuevaLista = inventario.get(categoria);
						nuevaLista.add(producto);
						inventario.put(categoria, nuevaLista);
						categorias.add(categoria);
						
					}
					
				} catch (Exception e) {
					System.out.println("Se ha producido un error");
				}
			}
		} catch (Exception e) {
			System.out.println("El archivo se encuentra vacio");
		}
		
	}
	
	public void agregarProducto() {
		
		Boolean agregar = true;
		System.out.println("\n Agregar Producto \n");
		System.out.println("Ingrese la categoria (Ingrese 2 para salir): ");
		String categoria = input.nextLine().toUpperCase();
		
		while (agregar) {
			
			if (categoria.equals("2")) {
				agregar = false;
			} else {
				
				List<String> productos = inventario.get(categoria);
				
				if (productos == null) {
					
					System.out.println("No existe esa categoria");
					
				} else {
					
					for(int i = 0; i < productos.size(); i++) {
						System.out.println((i + 1) + ") " + productos.get(i));
					}
					
					System.out.println("\n Ingrese el numero del producto: ");
					
					int eleccion = Integer.parseInt(input.nextLine()) - 1;
					
					if (eleccion > productos.size() || eleccion < 1) {
						
						System.out.println("No existe ese producto");
						
					} else {
						
						List<String> lista = coleccion.get(categoria);
						lista.add(productos.get(eleccion));
						coleccion.put(categoria, lista);
						System.out.println("Producto agregado existosamente");
						
					}
				}
			}
		}
		
	}
	
	public void mostrarIMAP(IMap<String, List<String>> tempList, Boolean isSort) {
		
		if (isSort) {
            Collections.sort(categorias);
        }

        for (int i = 0; i < categorias.size(); i++) {

            List<String> products = tempList.get(categorias.get(i));

            if (products != null) {
                System.out.println("\n-> " + categorias.get(i));

                for (int e = 0; e < products.size(); e++) {
                    System.out.println("  - " + products.get(e) + "| 1");
                }

            }
        }
	}
	
	public void mostrarCategoria() {
		
		System.out.println("\n Categoria de un Producto \n");
		
		System.out.println("Ingrese el producto: ");
		String producto = input.nextLine();
		
		String categoriaTemp = "";

        for (int i = 0; i < categorias.size(); i++) {

            List<String> products = inventario.get(categorias.get(i));

            for (int e = 0; e < products.size(); e++) {
                if (products.get(e).equals(producto)) {
                    categoriaTemp = categorias.get(i);
                }
            }

        }
        
        System.out.println("El producto pertenece a la categoria: " + categoriaTemp + "\n");
		
	}
	
}
