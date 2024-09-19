package TrabajoPractico3;

public class ParqueTematico {

    private int[] areas = new int[10];

    public ParqueTematico(int cant) {
        for (int i = 0; i < areas.length; i++) {
            areas[i] = cant;
        }
    }

    public void reservarLugar(int area){
        if(areas[area] > 0){
            areas[area]--;
        }
    }

    public boolean estaLleno(int area){
        return areas[area] == 0;
    }
}
