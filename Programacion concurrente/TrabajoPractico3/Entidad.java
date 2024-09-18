package TrabajoPractico3;

public class Entidad implements Runnable{
    private Energia energia;
    private int gastoEnergia;

    public Entidad(int gastoEnergia, Energia energia) {
        this.gastoEnergia = gastoEnergia;
        this.energia = energia;
    }

    private void modificarEnergia(){
        if(energia.getEnergia() >= gastoEnergia){
            System.out.println(Thread.currentThread().getName()+" modificó "+gastoEnergia+" de energia");
            energia.modificarEnergia(gastoEnergia);
            System.out.println("La energia actual es de "+energia.getEnergia());
        } else {
            System.out.println("Sin energia");
        }
    }

    public void run() {
        while(energia.getEnergia()>0){
            try{

            }
        }
    }
}
