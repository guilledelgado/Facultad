package TrabajoPractico8;

public class mainPunto2 {
    public static void main(String[] args) {
        Observatorio observatorio = new Observatorio(50, 100, 25,10);
        for (int i = 0; i < 150; i++) {
            PersonaObservatorio visitantes = new PersonaObservatorio('v',observatorio);
            Thread vThread = new Thread(visitantes, "Visitante "+(i+1));
            vThread.start();
        }
        for (int i = 0; i < 50; i++) {
            PersonaObservatorio discapacitados = new PersonaObservatorio('d',observatorio);
            Thread dThread = new Thread(discapacitados, "Visitante Silla ruedas "+(i+1));
            dThread.start();
        }
        for (int i = 0; i < 50; i++) {
            PersonaObservatorio mantenimiento = new PersonaObservatorio('m',observatorio);
            Thread mThread = new Thread(mantenimiento, "Mantenimiento "+(i+1));
            mThread.start();
        }
        for (int i = 0; i < 50; i++) {
            PersonaObservatorio investigador = new PersonaObservatorio('i',observatorio);
            Thread iThread = new Thread(investigador, "Investigador "+(i+1));
            iThread.start();
        }

    }
}
