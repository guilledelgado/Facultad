package TPO;

public class Equipo implements Comparable{
    // Atributos
    private String nombre;
    private String directorTecnico;
    private char grupo;
    private int puntos;
    private int golesAFavor;
    private int golesEnContra;

    //Constructor
    public Equipo(String nom, String dt, char gr) {
        this.nombre = nom;
        this.directorTecnico = dt;
        this.grupo = gr;
        this.puntos = 0;
        this.golesAFavor = 0;
        this.golesEnContra = 0;
    }

    //Modificadores
    public void sumarGolesAFavor(int gol) {
        this.golesAFavor = golesAFavor + gol;
    }

    public void sumarGolesEnContra(int gol) {
        this.golesEnContra = golesEnContra + gol;
    }

    public void partidoGanado() {
        this.puntos = puntos + 3;
    }

    public void partidoEmpatado() {
        this.puntos = puntos + 1;
    }

    public void setDirectorTecnico(String entrenador){
        this.directorTecnico = entrenador;
    }

    public void setGrupo(char grup){
        this.grupo = grup;
    }

    //Observadores
    public String getNombre() {
        return nombre;
    }

    public String getDirectorTecnico() {
        return directorTecnico;
    }

    public char getGrupo() {
        return grupo;
    }

    public int getPuntos(){
        return puntos;
    }

    public int getGolesAFavor(){
        return golesAFavor;
    }

    public int getGolesEnContra(){
        return golesEnContra;
    }

    public String getDatos(){
        return "Equipo: "+nombre+" | Puntos: "+puntos+" | GAF: "+golesAFavor+" | GEC: "+golesEnContra + " | DIF: "+ (golesAFavor - golesEnContra);
    }

    public String toString(){
        return nombre;
    }
    //Comparadores
    public int compareTo(Object aux) {
        return this.nombre.compareTo(((Equipo)aux).getNombre());
    }

    public boolean equals(Equipo aux) {
        return this.nombre.equals(aux.getNombre());
    }
}
