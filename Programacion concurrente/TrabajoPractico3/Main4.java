package TrabajoPractico3;

public class Main4 {
    public static void main(String[] args) {
        ParqueTematico parqueTematico = new ParqueTematico(10);
        Visitante visitante = new Visitante(parqueTematico);
        Visitante visitante2 = new Visitante(parqueTematico);
        Visitante visitante3 = new Visitante(parqueTematico);
        Visitante visitante4 = new Visitante(parqueTematico);
        Visitante visitante5 = new Visitante(parqueTematico);
        Visitante visitante6 = new Visitante(parqueTematico);
        Visitante visitante7 = new Visitante(parqueTematico);
        Visitante visitante8 = new Visitante(parqueTematico);
        Visitante visitante9 = new Visitante(parqueTematico);
        Visitante visitante10 = new Visitante(parqueTematico);
        Thread thread1 = new Thread(visitante, "Visitante 1");
        Thread thread2 = new Thread(visitante2, "Visitante 2");
        Thread thread3 = new Thread(visitante3, "Visitante 3");
        Thread thread4 = new Thread(visitante4, "Visitante 4");
        Thread thread5 = new Thread(visitante5, "Visitante 5");
        Thread thread6 = new Thread(visitante6, "Visitante 6");
        Thread thread7 = new Thread(visitante7, "Visitante 7");
        Thread thread8 = new Thread(visitante8, "Visitante 8");
        Thread thread9 = new Thread(visitante9, "Visitante 9");
        Thread thread10 = new Thread(visitante10, "Visitante 10");
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();
        thread9.start();
        thread10.start();

    }
}
