
import java.util.Scanner;

/**
 * Crea un programa que permita: <br>
 * - Escribir en un fichero tus videojuegos favoritos.<br>
 * - Mostrar el listado de juegos del fichero.<br>
 * - Añadir un juego al final del fichero.
 * 
 * Cada videojuego tiene que tener un titulo, una empresa y una fecha de
 * edición. Cada videojuego se guardara como una linea de texto en el fichero;
 * por tanto, si hay cinco ficheros habra cinco lineas de texto.
 * 
 * NOTA: el truco es darse cuenta de que tiene que haber LO MISMO en el array de
 * videojuegos que en el fichero.
 */
public class GestorDeVideojuegos {

	private Scanner teclado = null;
	private VideoJuego[] videojuegos = null;

	// Para manejar los ficheros de texto
	private ManejadorFicherosTexto manejadorFicherosTexto = null;

	public static final int MAX_VIDEOJUEGOS = 20;
	public static final String NOMBRE_FICHERO = "videojuegos.txt";
	public static final String RUTA_FICHERO = "c:\\trastero\\";

	public GestorDeVideojuegos() {
		teclado = new Scanner(System.in);
		manejadorFicherosTexto = new ManejadorFicherosTexto(NOMBRE_FICHERO, RUTA_FICHERO, MAX_VIDEOJUEGOS);

		// Carga inicial del array
		videojuegos = manejadorFicherosTexto.cargarVideojuegosDelFichero();
		System.out.println("Bienvenido. " + numeroVideojuegos() + " juegos se han leido del fichero " + NOMBRE_FICHERO);
	}

	private void run() {
		int opcion = 0;
		do {
			opcion = opcionMenuInicial();
			if (opcion != 0) {
				ejecutarOpcionMenuInicial(opcion);
			}
		} while (opcion != 0);
	}

	private int opcionMenuInicial() {
		int ret = 0;
		do {
			try {
				escribirMenuInicial();
				System.out.print("Elija una opcion: ");
				ret = teclado.nextInt();
				teclado.nextLine();
			} catch (Exception e) {
				teclado.nextLine();
				ret = -1;
			}
		} while ((ret < 0) && (ret > 3));
		return ret;
	}

	private void escribirMenuInicial() {
		System.out.println(" ");
		System.out.println("---- MENU ----");
		System.out.println("---- 0 - SALIR ");
		System.out.println("---- 1 - Añadir videojuegos  ");
		System.out.println("---- 2 - Mostrar videojuegos ");
		System.out.println("---- 3 - Añadir videjuego al final ");
		System.out.println("--------------");
	}

	private void ejecutarOpcionMenuInicial(int opcion) {
		switch (opcion) {
		case 0:
			System.out.print("Adios!!!");
			break;
		case 1:
			introducirVideojuegosEnArray();
			manejadorFicherosTexto.guardarVideojuegosEnElFichero(videojuegos);
			// Tanto el array como el fichero tienen lo mismo. Todo OK!
			// Date cuenta de que guardarVideojuegosEnElFichero() reescribe el fichero
			// siempre, pero como le metemos todo el rato el array, no se nota
			break;
		case 2:
			videojuegos = manejadorFicherosTexto.cargarVideojuegosDelFichero();
			mostrarVideojuegos();
			// No nos fiamos de que haya lo mismo en el array que el fichero, asi que
			// refrescamos el array con lo que haya en el fichero
			break;
		case 3:
			VideoJuego nuevoVideojuego = nuevoVideojuego();
			manejadorFicherosTexto.guardarVideojuegoEnElFicheroAlFinal(nuevoVideojuego);

			// Como NO tenemos lo mismo en el array que en el fichero, hay que
			// refrescar el array
			videojuegos = manejadorFicherosTexto.cargarVideojuegosDelFichero();

			
			break;
		}
	}

	/**
	 * Pide al usuario videojuegos hasta que decide no seguir introduciendo nuevos
	 * 
	 */
	private void introducirVideojuegosEnArray() {
		boolean choice = false;
		do {
			VideoJuego nuevoVideojuego = nuevoVideojuego();
			añadirVideojuegoEnArray(nuevoVideojuego);
			System.out.println("");
			System.out.print("Desea seguir introduciendo videojuegos? [S/N]: ");
			choice = (teclado.nextLine().trim().charAt(0) + "").equalsIgnoreCase("S") ? true : false;
		} while (choice);
	}

	/**
	 * Pide al usuario un videojuego y lo retorna
	 * 
	 * @return el videojuego
	 */
	private VideoJuego nuevoVideojuego() {
		
		System.out.println("Nuevo videojuego: ");
		System.out.print("Titulo: ");
		String titulo = teclado.nextLine();
		System.out.print("Empresa: ");
		String empresa = teclado.nextLine();
		System.out.print("Fecha: ");
		String fecha = teclado.nextLine();
		
		VideoJuego videojuego = new VideoJuego(titulo, empresa, fecha);
		return videojuego;
	}

	/**
	 * Muestra todos los videjouegos
	 */
	private void mostrarVideojuegos() {
		for (int i = 0; i < MAX_VIDEOJUEGOS; i++) {
			if (videojuegos[i] != null) {
				System.out.println("");
				mostrarVideojuego(videojuegos[i]);
			}
		}
	}

	/**
	 * Muestra un videojuego
	 * 
	 * @param videojuego
	 */
	private void mostrarVideojuego(VideoJuego videojuego) {
		
		System.out.println("Videojuego: ");
		System.out.println("Titulo: " + videojuego.getTitulo());
		System.out.println("Empresa: " + videojuego.getEmpresa());
		System.out.println("Fecha: " + videojuego.getFecha());
	}

	/**
	 * Añade un videojuego en un huevo libre del array. Si no hay hueco, informa al
	 * usuario y no lo introduce.
	 * 
	 * @param nuevoVideojuego
	 * @return El array de videojuegos
	 */
	private VideoJuego[] añadirVideojuegoEnArray(VideoJuego nuevoVideojuego) {
		int i = 0;
		for (i = 0; i < MAX_VIDEOJUEGOS; i++) {
			if (videojuegos[i] == null) {
				videojuegos[i] = nuevoVideojuego;
				break;
			}
		}

		if (i == MAX_VIDEOJUEGOS) {
			System.out.println("No hay huecos en el array. El videojuego no se guardara");
		}

		return videojuegos;
	}

	/**
	 * Retorna el numero de videojuegos en el array. Basicamente, el numero de
	 * posiciones no nulas del array, dado que null significa 'vacio'.
	 * 
	 * @return Numero de videojuegos
	 */
	private int numeroVideojuegos() {
		int ret = 0;
		for (int i = 0; i < MAX_VIDEOJUEGOS; i++) {
			if (videojuegos[i] != null)
				ret++;
		}
		return ret;
	}

	public static void main(String[] args) {
		(new GestorDeVideojuegos()).run();
	}
}
