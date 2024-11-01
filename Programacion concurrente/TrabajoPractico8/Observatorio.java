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
        this.mutex = new ReentrantLock();
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
        if(cuantasPersonasHay==0){
            quienesEstan = 'v';
        }
        while(cuantasPersonasHay > capacidad || quienesEstan != 'v' || cuantosPersonasPasaron > maxVisitantes){
            esperaVisitantes.await();
            if(quienesEstan == ' '){
                quienesEstan = 'v';
            }
        }
        System.out.println(Thread.currentThread().getName()+ " entró a la sala");
        filaVisitante--;
        cuantasPersonasHay++;
        cuantosPersonasPasaron++;
        mutex.unlock();
    }

    public void saleVisitante() throws InterruptedException {
        mutex.lock();
        System.out.println(Thread.currentThread().getName() + " salió de la sala");
        cuantasPersonasHay--;
        if((cuantasPersonasHay == 0 && cuantosPersonasPasaron > maxVisitantes) ||filaVisitante==0){
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
        if(cuantasPersonasHay==0){
            quienesEstan = 'm';
        }
        while(cuantasPersonasHay > capacidad || quienesEstan != 'm' || cuantosPersonasPasaron > maxMantenimiento){
            esperaMantenimiento.await();
            if(quienesEstan == ' '){
                quienesEstan = 'm';
            }
        }
        System.out.println(Thread.currentThread().getName()+ " entró a la sala");
        filaMantenimiento--;
        cuantasPersonasHay++;
        cuantosPersonasPasaron++;
        mutex.unlock();
    }

    public void saleMantenimiento() throws InterruptedException {
        mutex.lock();
        System.out.println(Thread.currentThread().getName() + " salió de la sala");
        cuantasPersonasHay--;
        if((cuantasPersonasHay == 0 && cuantosPersonasPasaron > maxMantenimiento)||(filaMantenimiento==0)){
            quienesEstan = ' ';
            cuantosPersonasPasaron = 0;
            esperaVisitantes.signalAll();
            esperaInvestigadores.signalAll();
        } else {
            esperaMantenimiento.signal();
        }
        mutex.unlock();
    }

    public void entraInvestigador() throws InterruptedException {
        mutex.lock();
        filaInvestigadores++;
        if(cuantasPersonasHay==0){
            quienesEstan = 'i';
        }
        while(cuantasPersonasHay > 0 || quienesEstan != 'i' || cuantosPersonasPasaron > maxInvestigadores){
            esperaInvestigadores.await();
            if(quienesEstan == ' '){
                quienesEstan = 'i';
            }
        }
        System.out.println(Thread.currentThread().getName()+ " entró a la sala");
        filaInvestigadores--;
        cuantasPersonasHay++;
        cuantosPersonasPasaron++;
        mutex.unlock();
    }

    public void saleInvestigador() throws InterruptedException {
        mutex.lock();
        System.out.println(Thread.currentThread().getName() + " salió de la sala");
        cuantasPersonasHay--;
        if((cuantasPersonasHay==0 && cuantosPersonasPasaron > maxInvestigadores)||filaInvestigadores==0){
            quienesEstan = ' ';
            cuantosPersonasPasaron = 0;
            esperaVisitantes.signalAll();
            esperaMantenimiento.signalAll();
        } else {
            esperaInvestigadores.signal();
        }
    }

    public void entraVisitanteSillaRuedas() throws InterruptedException {
        mutex.lock();
        if(quienesEstan == ' '){
            quienesEstan = 'v';
        }
        filaVisitante++;
        while(cuantasPersonasHay >= 30 || quienesEstan != 'v' || cuantosPersonasPasaron > maxVisitantes){
            esperaVisitantes.await();
            if(quienesEstan == ' '){
                quienesEstan = 'v';
            }
        }
        System.out.println(Thread.currentThread().getName()+ " entró a la sala");
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
        if(cuantosDiscapacitados==0){
            capacidad = 50;
        }
        if((cuantasPersonasHay == 0 && cuantosPersonasPasaron > maxVisitantes)||filaVisitante==0){
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
