public class VideoJuego {
    
    private String titulo;
    private String empresa;
    private String fecha;

    public VideoJuego(String titulo, String empresa, String fecha) {
        this.titulo = titulo;
        this.empresa = empresa;
        this.fecha = fecha;
    }

    // Getters and setters for the attributes

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String toString() {
        return titulo + "-" + empresa + "-" + fecha;
    }
}
