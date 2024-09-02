import java.util.*;

class Carta {
    String palo;
    int numero;
    int valorEnvido;

    Carta(String palo, int numero) {
        this.palo = palo;
        this.numero = numero;
        if (numero < 8) { // 1-7
            this.valorEnvido = numero;
        } else { // 10-12
            this.valorEnvido = 0;
        }
    }
}

public class Truco {
    public static void main(String[] args) {
        String[] palos = {"oros", "copas", "espadas", "bastos"};
        int[] frecuenciaEnvido = new int[34]; // El envido máximo es 33
        for (int i = 0; i < 1000000; i++) {
            ArrayList<Carta> mazo = new ArrayList<>();
            for (String palo : palos) {//Ordena las cartas de 1 al 12 y con el orden de oros, copas, espadas y bastos
                for (int j = 1; j <= 12; j++) {
                    if (j != 8 && j != 9) { // Las cartas 8 y 9 no se usan en el Truco
                        mazo.add(new Carta(palo, j));
                    }
                }
            }
            Collections.shuffle(mazo);
            Carta[] mano = {mazo.get(0), mazo.get(1), mazo.get(2)};
            int envido = calcularEnvido(mano);
            frecuenciaEnvido[envido]++;
        }
        System.out.println("La cantidad de veces que salió cada resultado de envido después de 1,000,000 de repeticiones es de:");
        for (int i = 0; i < frecuenciaEnvido.length; i++) {
            System.out.println("Envido " + i + ": " + frecuenciaEnvido[i]);
        }
    }

    public static int calcularEnvido(Carta[] mano) {
        int maxEnvido = 0;
        for (int i = 0; i < mano.length; i++) {
            for (int j = i + 1; j < mano.length; j++) {
                if (mano[i].palo.equals(mano[j].palo)) {
                    int envido = 20 + mano[i].valorEnvido + mano[j].valorEnvido;
                    if (envido > maxEnvido) {
                        maxEnvido = envido;
                    }
                } else {
                    if (mano[i].valorEnvido > maxEnvido) {
                        maxEnvido = mano[i].valorEnvido;
                    }
                    if (mano[j].valorEnvido > maxEnvido) {
                        maxEnvido = mano[j].valorEnvido;
                    }
                }
            }
        }
        return maxEnvido;
    }
}
