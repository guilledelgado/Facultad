package Parciales;

public class Oxigeno implements Runnable{
    private FormacionAgua formacionAgua;
    public Oxigeno(FormacionAgua formacionAgua) {
        this.formacionAgua = formacionAgua;
    }
    @Override
    public void run(){
        try{
            Thread.sleep((int)(((Math.random())+1)*6)*1000);
            formacionAgua.oListo();
            formacionAgua.esperaHidrogeno();
            formacionAgua.aguaLista();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}