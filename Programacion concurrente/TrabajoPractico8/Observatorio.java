package TrabajoPractico8;

import java.util.concurrent.locks.*;

public class Observatorio {
    private Lock mutex;
    private Condition esperaMantenimiento;
    private Condition esperaInvestigadores;
    private Condition esperaVisitantes;
    private int capacidad;
    private int cuantasPersonasHay;
    private char quienesEstan;
    private int filaVisitante;
    private int filaMantenimiento;
    private int filaInvestigadores;
    private int cuantosPersonasPasaron;
    private int maxVisitantes;
    private int maxMantenimiento;
    private int maxInvestigadores;
    private int cuantosDiscapacitados;

    public Observatorio(int capacidad, int maxVisitantes, int maxMantenimiento, int maxInvestigadores) {
        this.capacidad = capacidad;
        this.mutex = new ReentrantLock(true);
        this.esperaVisitantes = mutex.newCondition();
        this.esperaMantenimiento = mutex.newCondition();
        this.esperaInvestigadores = mutex.newCondition();
        filaMantenimiento = 0;
        filaInvestigadores = 0;
        filaVisitante = 0;
        cuantasPersonasHay = 0;
        cuantosPersonasPasaron = 0;
        quienesEstan = ' ';
        this.maxVisitantes = maxVisitantes;
        this.maxMantenimiento = maxMantenimiento;
        this.maxInvestigadores = maxInvestigadores;
        cuantosDiscapacitados = 0;
    }

    public void entraVisitante() throws InterruptedException {
        mutex.lock();
        filaVisitante++;
        if (cuantasPersonasHay == 0) {
            // Si es el primero en entrar a la sala, marca que van a entrar los visitantes
            quienesEstan = 'v';
        }
        while (cuantasPersonasHay > capacidad || ((quienesEstan != 'v' || cuantosPersonasPasaron > maxVisitantes) && !(filaInvestigadores == 0 && filaMantenimiento == 0))) {
            //Los visitantes esperan si la sala está llena
            // o si no le tocan a los visitantes
            // o si se alcanzó al tope de visitantes
            //En caso de que no hayan mas investigadores o mantenimiento esperando, no espera
            esperaVisitantes.await();
            if (quienesEstan == ' ') {
                quienesEstan = 'v';
            }
        }
        System.out.println(Thread.currentThread().getName() + " entró a la sala");
        filaVisitante--;
        cuantasPersonasHay++;
        cuantosPersonasPasaron++;
        mutex.unlock();
    }

    public void saleVisitante() throws InterruptedException {
        mutex.lock();
        System.out.println(Thread.currentThread().getName() + " salió de la sala");
        cuantasPersonasHay--;
        if ((cuantasPersonasHay == 0 && (cuantosPersonasPasaron > maxVisitantes || filaVisitante == 0)) && !(filaMantenimiento == 0 && filaInvestigadores == 0)) {
            //Si no hay mas personas en la sala y se supero el tope o
            // no hay mas personas en la fila de visitantes, despierto a los otros conjuntos de espera
            quienesEstan = ' ';
            cuantosPersonasPasaron = 0;
            esperaMantenimiento.signalAll();
            esperaInvestigadores.signalAll();
        } else {
            esperaVisitantes.signal();
        }
        mutex.unlock();
    }

    public void entraMantenimiento() throws InterruptedException {
        mutex.lock();
        filaMantenimiento++;
        if (cuantasPersonasHay == 0) {
            quienesEstan = 'm';
        }
        while (cuantasPersonasHay > capacidad || ((quienesEstan != 'm' || cuantosPersonasPasaron > maxMantenimiento) && !(filaInvestigadores == 0 && filaVisitante == 0))) {
            //Los de Mantenimiento esperan si la sala está llena
            // o si no le tocan a los de mantenimiento
            // o si se alcanzó al tope de personal de mantenimiento
            //En caso de que no hayan mas investigadores o visitantes esperando, no espera
            esperaMantenimiento.await();
            if (quienesEstan == ' ') {
                quienesEstan = 'm';
            }
        }
        System.out.println(Thread.currentThread().getName() + " entró a la sala");
        filaMantenimiento--;
        cuantasPersonasHay++;
        cuantosPersonasPasaron++;
        mutex.unlock();
    }

    public void saleMantenimiento() throws InterruptedException {
        mutex.lock();
        System.out.println(Thread.currentThread().getName() + " salió de la sala");
        cuantasPersonasHay--;
        if ((cuantasPersonasHay == 0 && (cuantosPersonasPasaron > maxMantenimiento || filaMantenimiento == 0)) && !(filaInvestigadores == 0 && filaVisitante == 0)) {
            //Si no hay mas personas en la sala y se supero el tope o
            // no hay mas personas en la fila de Mantenimiento, despierto a los otros conjuntos de espera
            quienesEstan = ' ';
            cuantosPersonasPasaron = 0;
            esperaInvestigadores.signalAll();
            esperaVisitantes.signalAll();
        } else {
            esperaMantenimiento.signal();
        }
        mutex.unlock();
    }

    public void entraInvestigador() throws InterruptedException {
        mutex.lock();
        filaInvestigadores++;
        if (cuantasPersonasHay == 0) {
            quienesEstan = 'i';
        }
        while (cuantasPersonasHay > 0 || ((quienesEstan != 'i' || cuantosPersonasPasaron > maxInvestigadores) && (!(filaMantenimiento == 0 && filaVisitante == 0)))) {
            //Los Investigadores esperan si la sala está llena
            // o si no le tocan a los investigadores
            // o si se alcanzó al tope de personal de investigacion
            //En caso de que no hayan mas de mantenimiento o visitantes esperando, no espera (siempre y cuando no haya nadie en la sala)
            esperaInvestigadores.await();
            if (quienesEstan == ' ') {
                quienesEstan = 'i';
            }
        }
        System.out.println(Thread.currentThread().getName() + " entró a la sala");
        filaInvestigadores--;
        cuantasPersonasHay++;
        cuantosPersonasPasaron++;
        mutex.unlock();
    }

    public void saleInvestigador() throws InterruptedException {
        mutex.lock();
        System.out.println(Thread.currentThread().getName() + " salió de la sala");
        cuantasPersonasHay--;
        if (((cuantosPersonasPasaron > maxInvestigadores || filaInvestigadores == 0) && !(filaMantenimiento == 0 && filaVisitante == 0))) {
            //Si no hay mas personas en la sala y se supero el tope o
            // no hay mas personas en la fila de Investigadores, despierto a los otros conjuntos de espera
            quienesEstan = ' ';
            cuantosPersonasPasaron = 0;
            esperaVisitantes.signalAll();
            esperaMantenimiento.signalAll();
        } else {
            esperaInvestigadores.signal();
        }
        mutex.unlock();
    }

    public void entraVisitanteSillaRuedas() throws InterruptedException {
        mutex.lock();
        if (quienesEstan == ' ') {
            quienesEstan = 'v';
        }
        filaVisitante++;
        while (cuantasPersonasHay >= 30 || ((quienesEstan != 'v' || cuantosPersonasPasaron > maxVisitantes) && !(filaInvestigadores == 0 && filaVisitante == 0))) {
            //Los discapacitados esperan si la sala llegó a 30 o mas de capacidad
            // o si no le tocan a los visitantes
            // o si se alcanzó al tope de visitantes
            //En caso de que no hayan mas de mantenimiento o investigadores esperando, no espera
            esperaVisitantes.await();
            if (quienesEstan == ' ') {
                quienesEstan = 'v';
            }
        }
        System.out.println(Thread.currentThread().getName() + " entró a la sala");
        //Siempre que entre uno setea la capacidad en 30 y notifica que hay una persona en silla de ruedas
        capacidad = 30;
        cuantosDiscapacitados++;
        filaVisitante--;
        cuantasPersonasHay++;
        cuantosPersonasPasaron++;
        mutex.unlock();
    }

    public void saleVisitanteSillaRuedas() throws InterruptedException {
        mutex.lock();
        System.out.println(Thread.currentThread().getName() + " salió de la sala");
        cuantasPersonasHay--;
        cuantosDiscapacitados--;
        if (cuantosDiscapacitados == 0) {
            //Si ya no hay mas personas en silla de ruedas, vuelvo a poner la capacidad en 50
            capacidad = 50;
        }
        if ((cuantasPersonasHay == 0 && (cuantosPersonasPasaron > maxVisitantes || filaVisitante == 0)) && !((filaMantenimiento == 0) && (filaInvestigadores == 0))) {
            //Si no hay mas personas en la sala y se supero el tope o
            // no hay mas personas en la fila de visitantes, despierto a los otros conjuntos de espera
            quienesEstan = ' ';
            cuantosPersonasPasaron = 0;
            esperaMantenimiento.signalAll();
            esperaInvestigadores.signalAll();
        } else {
            esperaVisitantes.signal();
        }
        mutex.unlock();
    }
}
