package TrabajoPractico8;
import java.util.Random;
public class mainPunto1 {
    public static void main(String[] args) {
        int cantMostradoresAlmuerzo =2;
        int cantMostadoresPostre = 1;
        Comedor comedor = new Comedor (1,cantMostradoresAlmuerzo,cantMostadoresPostre);
        for (int i = 0; i < 10; i++) {
            Soldado soldado = new Soldado(comedor, cantMostradoresAlmuerzo, cantMostadoresPostre);
            Thread tSoldado = new Thread(soldado, "Soldado "+(i+1));
            tSoldado.start();
        }
    }
}
