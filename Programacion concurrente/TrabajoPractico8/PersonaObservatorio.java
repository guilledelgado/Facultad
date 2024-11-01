package TrabajoPractico8;

public class PersonaObservatorio implements Runnable {
    private Observatorio observatorio;
    private char tipo;
    public PersonaObservatorio(char tipoVisitante, Observatorio observatorio) {
        this.observatorio = observatorio;
        this.tipo = tipoVisitante;
    }
    private void esVisitante() throws InterruptedException {
        observatorio.entraVisitante();
        Thread.sleep((long) ((Math.random()*5)*1000));
        observatorio.saleVisitante();
    }

    private void esInvestigador() throws InterruptedException {
        observatorio.entraInvestigador();
        Thread.sleep((long) ((Math.random()*5)*1000));
        observatorio.saleInvestigador();
    }

    private void esMantenimiento() throws InterruptedException {
        observatorio.entraMantenimiento();
        Thread.sleep((long) ((Math.random()*5)*1000));
        observatorio.saleMantenimiento();
    }
    private void esSillaRuedas() throws InterruptedException {
        observatorio.entraVisitanteSillaRuedas();
        Thread.sleep((long) ((Math.random()*5)*1000));
        observatorio.saleVisitanteSillaRuedas();
    }
    public void run(){
        try{
            if(tipo == 'i'){
                esInvestigador();
            } else if(tipo == 'v'){
                esVisitante();
            } else if(tipo == 'm'){
                esMantenimiento();
            } else {
                esSillaRuedas();
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

}
