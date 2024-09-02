package TPO;

public class Partido {
    //Atributos
    private Equipo equipo1;
    private Equipo equipo2;
    private String ronda;
    private String ciudad;
    private String estadio;
    private int golesEquipo1;
    private int golesEquipo2;

    //Constructor
    public Partido(Equipo eq1, Equipo eq2, String ron, String ciu, String stadio, int golesEq1, int golesEq2){
        this.equipo1 = eq1;
        this.equipo2 = eq2;
        this.ronda = ron;
        this.ciudad = ciu;
        this.estadio = stadio;
        this.golesEquipo1 = golesEq1;
        this.golesEquipo2 = golesEq2;
    }

    //Observadores
    public Equipo getEquipo1(){
        return equipo1;
    }

    public Equipo getEquipo2(){
        return equipo2;
    }

    public String getRonda(){
        return ronda;
    }

    public String getCiudad(){
        return ciudad;
    }

    public String getEstadio(){
        return estadio;
    }

    public int getGolesEquipo1(){
        return golesEquipo1;
    }

    public int getGolesEquipo2(){
        return golesEquipo2;
    }
}
