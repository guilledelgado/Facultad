package tda;

public class Alumno {
    // atributos
    private int legajo;
    private String nombre;
    private String apellido;
    private String tipoDNI;
    private int numeroDNI;
    private String domicilioCalle;
    private int domicilioNumero;
    private String domicilioCiudad;
    private int numeroTelefono;
    private String usuario;
    private String contrasenia;

    // Constructores
    public Alumno(int legajo, String tipoDNI, int numeroDNI) {
        this.legajo = legajo;
        this.tipoDNI = tipoDNI;
        this.numeroDNI = numeroDNI;
    }

    public Alumno(int legajo, String nombre, String apellido,
            String tipoDNI, int numeroDNI, String domicilioCalle, int domicilioNumero,
            String domicilioCiudad, int numeroTelefono, String usuario, String contrasenia) {
        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDNI = tipoDNI;
        this.numeroDNI = numeroDNI;
        this.domicilioCalle = domicilioCalle;
        this.domicilioNumero = domicilioNumero;
        this.domicilioCiudad = domicilioCiudad;
        this.numeroTelefono = numeroTelefono;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    // Observadores
    public int getLegajo() {
        return legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDNI() {
        return tipoDNI + ": " + numeroDNI;
    }

    public String getDomicilio() {
        return domicilioCalle + " " + domicilioNumero + ", " + domicilioCiudad;
    }

    public int getNumeroTelefono() {
        return numeroTelefono;
    }

    public String getUsuario() {
        return usuario;
    }

    public String toString() {
        return /*nombre + " " + apellido +*/ "|Legajo: " + legajo /*+ "| " + tipoDNI + ": "
                + numeroDNI + "| Domicilio: " + domicilioCalle + " " + domicilioNumero + ", " + domicilioCiudad
                + "| Telefono: " + numeroTelefono + "| Usuario: " + usuario*/;
    }

    // Modificadores
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDomicilioCalle(String domicilioCalle) {
        this.domicilioCalle = domicilioCalle;
    }

    public void setDomicilioNumero(int domicilioNumero) {
        this.domicilioNumero = domicilioNumero;
    }

    public void setDomicilioCiudad(String domicilioCiudad) {
        this.domicilioCiudad = domicilioCiudad;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    // Comparativas
    public boolean equals(int legajoB) {
        return legajo == legajoB;
    }

    public boolean verificaClave(String contraseniaB) {
        return this.contrasenia.equals(contraseniaB);
    }
}
