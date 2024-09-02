package TPO;

public class Ciudad {
    //Atributo
    private String nombre;
    private boolean alojamientoDisponible;
    private boolean esSede;

    //Constructor
    public Ciudad(String nom, boolean alojamiento, boolean sede){
        this.nombre = nom;
        this.alojamientoDisponible = alojamiento;
        this.esSede = sede;
    }

    //Modificadores
    public void tieneAlojamientoDisponible(){
        alojamientoDisponible = true;
    }

    public void noTieneAlojamientoDisponible(){
        alojamientoDisponible = false;
    }

    public void esSede(){
        esSede = true;
    }

    public void noEsSede(){
        esSede = false;
    }

    //Observadores
    public String getNombre(){
        return nombre;
    }

    public boolean getAlojamientoDisponible(){
        return alojamientoDisponible;
    }

    public boolean getEsSede(){
        return esSede;
    }
    public String toString(){
        return nombre;
    }

    //Comparadores
    @Override
    public boolean equals(Object aux){
        boolean resultado;
        if(this == aux){
            resultado = true;
        } else if(aux == null || getClass() != aux.getClass()){
            resultado = false;
        } else {
            Ciudad ciudad = (Ciudad) aux;
            resultado = this.nombre.equals(ciudad.getNombre());
        }
        return resultado;
    }
    
    public int compareTo(Object aux){
        return this.nombre.compareTo(((Ciudad)aux).getNombre());
    }
}
