package Parciales;

public class Hidrogeno implements Runnable {
    private FormacionAgua formacionAgua;
    public Hidrogeno(FormacionAgua formacion) {
        this.formacionAgua = formacion;
    }
    @Override
    public void run() {
        try{
            Thread.sleep((int)(((Math.random())+1)*6)*1000);
            formacionAgua.hListo();
            formacionAgua.esperaAguaLista();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
