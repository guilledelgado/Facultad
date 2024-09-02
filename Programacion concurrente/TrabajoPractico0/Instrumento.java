package TrabajoPractico0;

import java.util.*;
class Instrumento{
    public void tocar(){
        System.out.println("Instrumento.tocar()");
    }
    public String tipo(){
        return "Instrumento";
    }
    public void afinar(){
        
    }
}

class Guitarra extends Instrumento{
    public void tocar(){
        System.out.println("Guitarra.tocar()");
    }
    public String tipo(){
        return "Guitarra";
    }
    public void afinar(){

    }
}

class Piano extends Instrumento{
    public void tocar(){
        System.out.println("Piano.tocar()");
    }
    public String tipo(){
        return "Piano";
    }
    public void afinar(){
        
    }
}

class Saxofon extends Instrumento{
    public void tocar(){
        System.out.println("Saxofon.tocar()");
    }
    public String tipo(){
        return "Saxofon";
    }
    public void afinar(){
        
    }
}

class Guzla extends Guitarra{
    public void tocar(){
        System.out.println("Guzla.tocar()");
    }
    public String tipo(){
        return "Guzla";
    }
    public void afinar(){
        
    }
}

class Ukelele extends Guitarra{
    public void tocar(){
        System.out.println("Ukelele.tocar()");
    }
    public String tipo(){
        return "Ukelele";
    }
    public void afinar(){
        
    }
}