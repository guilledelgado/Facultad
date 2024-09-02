class Fecha {
    private int dia;
    private int mes;
    private int anio;

    //Constructores
    public Fecha (int dia, int mes, int anio){
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }

    //Observadores
    public int getDia(){
        return dia;
    }
    public int getMes(){
        return mes;
    }
    public int getAnio(){
        return anio;
    }
    public String toString(){
        //retorna con el formato AAAAMMDD
        String formatoDia, formatoMes;//si el dia o el mes son del 1 al 9, lo convierto en string y le agrego el 0 a la izquierda
        if (dia<10) {
            formatoDia="0"+dia;
        } else {
            formatoDia = ""+dia;
        }
        if (mes < 10){
            formatoMes = "0"+mes;
        } else {
            formatoMes = ""+mes;
        }
        return anio+formatoMes+formatoDia;
    }
}
