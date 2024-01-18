import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class ManejadorFicherosTexto {

    private String ruta;
    private String fichero;
    private VideoJuego[] videojuegos;

    public ManejadorFicherosTexto(String fichero, String ruta, int numeroMaximo) {
        this.ruta = ruta;
        this.fichero = fichero;
        this.videojuegos = new VideoJuego[numeroMaximo];
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getFichero() {
        return fichero;
    }

    public void setFichero(String fichero) {
        this.fichero = fichero;
    }

    public VideoJuego[] getVideojuegos() {
        return videojuegos;
    }

    public void setVideojuegos(VideoJuego[] videojuegos) {
        this.videojuegos = videojuegos;
    }

    public VideoJuego[] cargarVideojuegosDelFichero() {

        VideoJuego[] listaVideoJuegos = new VideoJuego[20];              
        
        String rutaFichero = ruta + fichero;

        File directorio = new File(ruta);

        if(!directorio.isDirectory()){
            System.out.println("El directorio \"c:\\trastero\" no existe");
            System.out.println("Se creará el directorio");            

            if (directorio.mkdir()){
                System.out.println("El directorio se ha creado con éxito");
            } else System.out.println("Error al crear directorio");                  
        }      
       
        File f = new File(rutaFichero);
      
        
        try {

            FileReader reader = new FileReader(f);
            BufferedReader bufferedReader = new BufferedReader(reader);
            int leidos = 0;
			String linea;
			while ((linea = bufferedReader.readLine()) != null) {
				 String[] datos=linea.split("@");
				 VideoJuego videojuego=new VideoJuego(datos[0],datos[1],datos[2]);
				listaVideoJuegos[leidos] = videojuego;
				leidos++;
			}

            reader.close();     
               
        } catch (IOException e) {
            File fic = new File (ruta);
            System.out.println("\nError de lectura en el fichero"); 
            if (!fic.isFile()) {
                System.out.println("\"videojuegos.txt\" no existe, por favor, introduce registros para crearlo");
            }                      
        }         
       
        return listaVideoJuegos;
    }


    public void guardarVideojuegosEnElFichero(VideoJuego[] videojuegos) {

        String rutaFichero = ruta + fichero;
        File f = new File(rutaFichero);            
        BufferedWriter bw = null;
        FileWriter writer = null;
        try {

             writer = new FileWriter(f);          
			 bw = new BufferedWriter(writer);			

			for (int i = 0; i < videojuegos.length; i++) {
				if (videojuegos[i] != null){
					bw.write(videojuegos[i].getTitulo()+"@"+videojuegos[i].getEmpresa()+"@"+videojuegos[i].getFecha());
					bw.newLine();//salto de linea
				}
            }
				    
           
            System.out.println("\nEl archivo se ha creado / modificado correctamente");
        } catch (IOException e) {
			// Problemas con el fichero. Solucion? Informamos al usuario y seguimos adelante
			System.out.println("Error de escritura en el fichero " + ruta);
		} catch (Exception e) {
			// Fallo de otro tipo. Solucion? Informamos al usuario y seguimos adelante
			System.out.println("Error desconocido en la escritura del fichero " + ruta);
		} finally {
			
			try {
				if (null != bw)
					bw.close();
			} catch (IOException e) {
				// Nos da igual
			}
			try {
				if (null != writer)
					writer.close();
			} catch (IOException e) {
				// Nos da igual
			}
		}
}


    public void guardarVideojuegoEnElFicheroAlFinal(VideoJuego videoJuego) {

        String nuevoVideoJuego;
        nuevoVideoJuego = videoJuego.getTitulo() + "@" + videoJuego.getEmpresa() + "@" + videoJuego.getFecha() + "@";
       
        String rutaFichero = ruta + fichero;
        File f = new File(rutaFichero);
        FileWriter writer= null;
        BufferedWriter bw = null;

        try {
             writer = new FileWriter(f,true);  
             bw = new BufferedWriter(writer);          
               
                    bw.write(nuevoVideoJuego);
                    bw.newLine();                            
            
            System.out.println("\nEl archivo se ha creado / modificado correctamente");
        } catch (IOException e) {
            System.out.println("\nError de escritura en el fichero ");
        } catch (Exception e) {
            System.out.println("\nError desconocido!");
        } finally {
			
			try {
				if (null != bw)
					bw.close();
			} catch (IOException e) {
				// Nos da igual
			}
			try {
				if (null != writer)
					writer.close();
			} catch (IOException e) {
				// Nos da igual
			}
        }
    }
}