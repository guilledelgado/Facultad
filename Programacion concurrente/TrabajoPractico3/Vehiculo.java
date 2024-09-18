package TrabajoPractico3;

public class Vehiculo {
    private String marca;
    private String modelo;
    private double km;
    private String patente;

    public Vehiculo(String marca, String modelo, double km, String patente) {
        this.marca = marca;
        this.modelo = modelo;
        this.km = km;
        this.patente = patente;
    }

    public void sumarKm(double km) {
        this.km += km;
    }

    public String getMarca() { return marca;}
    public String getModelo() { return modelo;}
    public double getKm() { return km;}
    public String getPatente() { return patente;}


}
