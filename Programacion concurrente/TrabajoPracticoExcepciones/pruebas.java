package TrabajoPracticoExcepciones;

public class pruebas {
    public static double acceso_por_indice (double [] v, int j) throws RuntimeException{
        double elem;
        try{
            elem = v[j];
        } catch (RuntimeException exc){
            throw exc; 
        }
        return elem;
        } 
        // Desde el siguiente cliente “main”: 
        public static void main(String [] args){
        double [] v = new double [15];
        acceso_por_indice (v, 16); 
        }
        
}
