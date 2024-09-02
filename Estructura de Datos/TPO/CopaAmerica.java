package TPO;

import java.io.*;
import java.util.Scanner;
import lineales.dinamicas.*;
import conjuntistas.ArbolAVL;
import conjuntistas.HeapMax;
import grafos.GrafoEtiquetado;
import usoEspecifico.MapeoAMuchos;

public class CopaAmerica {
    public static void main(String[] args) {
        String estado = "estado.log";
        ArbolAVL equipos = new ArbolAVL();
        GrafoEtiquetado mapaCiudades = new GrafoEtiquetado();
        Scanner sc = new Scanner(System.in);
        int opcion, subOpcion;
        boolean finalizar = false;
        MapeoAMuchos partidos = new MapeoAMuchos(32);// Lo creo con la cantidad de partidos que tiene la copa 24 de
                                                     // grupos + 4 de cuartos + 2 de semis + 2 de final y 3er puesto
        String s = leerArchivo(
                "C:\\Users\\guill\\Documents\\Estructuras de datos\\Estructura de Datos\\TPO\\CargaInicial.txt");
        cargarDatos(s, equipos, mapaCiudades, partidos);
        try (FileWriter writer = new FileWriter(estado)) {
            writer.write(equipos.toString()+" \n");
            writer.write(mapaCiudades.toString()+" \n");
            writer.write(partidos.toString()+" \n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (!finalizar) {
            System.out.println("------------ APLICACION COPA AMERICA ------------");
            System.out.println("1. Modificar ciudades");
            System.out.println("2. Modificar equipos");
            System.out.println("3. Cargar un partido");
            System.out.println("4. Consulta por equipos");
            System.out.println("5. Consulta sobre partidos");
            System.out.println("6. Consulta sobre ciudades");
            System.out.println("7. Mejores equipos");
            System.out.println("8. Finalizar programa");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("1. Dar de alta una ciudad");
                    System.out.println("2. Dar de baja una ciudad");
                    System.out.println("3. Modificar una ciudad");
                    System.out.println("4. Volver al inicio");
                    subOpcion = sc.nextInt();
                    switch (subOpcion) {
                        case 1:
                            darAltaCiudad(mapaCiudades);
                            break;
                        case 2:
                            darBajaCiudad(mapaCiudades);
                            break;
                        case 3:
                            modificarCiudad(mapaCiudades);
                            break;
                        default:
                            break;
                    }
                    try (FileWriter writer = new FileWriter(estado)) {
                        writer.write(mapaCiudades.toString()+" \n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.println("1. Dar de alta equipo");
                    System.out.println("2. Dar de baja equipo");
                    System.out.println("3. Modificar equipo");
                    System.out.println("4. Volver al inicio");
                    subOpcion = sc.nextInt();
                    switch (subOpcion) {
                        case 1:
                            darAltaEquipo(equipos);
                            break;
                        case 2:
                            darBajaEquipo(equipos);
                            break;
                        case 3:
                            modificarEquipo(equipos);
                        default:
                            break;
                    }
                    try (FileWriter writer = new FileWriter(estado)) {
                        writer.write(equipos.toString()+" \n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    cargarPartido(partidos, equipos);
                    try (FileWriter writer = new FileWriter(estado)) {
                        writer.write(partidos.toString()+" \n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    consultaEquipos(equipos);
                    break;
                case 5:
                    consultaPartidos(partidos);
                    break;
                case 6:
                    consultaCiudades(mapaCiudades);
                    break;
                case 7:
                    tablaMejoresEquipos(equipos);
                    break;
                case 8:
                try (FileWriter writer = new FileWriter(estado)) {
                    writer.write(equipos.toString()+" \n");
                    writer.write(mapaCiudades.toString()+" \n");
                    writer.write(partidos.toString()+" \n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                    finalizar = true;
                    break;

                default:
                    System.err.println("Opcion incorrecta");
                    break;
            }
        }
    }

    public static void cargarDatos(String datos, ArbolAVL equipos, GrafoEtiquetado mapaCiudades,
            MapeoAMuchos partidos) {
        String[] cargaDatos = datos.split("\\|");
        for (int i = 1; i < cargaDatos.length; i++) {
            String[] aux = cargaDatos[i].split(": ");
            switch (aux[0]) {
                case "E":
                    String[] datosEquipo = aux[1].split("; ");
                    Equipo equi = new Equipo(datosEquipo[0], datosEquipo[1], datosEquipo[2].charAt(0));
                    equipos.insertar(equi);
                    break;
                case "C":
                    String[] datosCiudad = aux[1].split("; ");
                    Ciudad ciudad = new Ciudad(datosCiudad[0], datosCiudad[1].equals("TRUE"),
                            datosCiudad[2].equals("TRUE"));
                    mapaCiudades.insertarVertice(ciudad);
                    break;
                case "P":
                    String[] datosPartido = aux[1].split("; ");
                    Equipo aux1 = new Equipo(datosPartido[0], " ", ' ');// AUXILIARES PARA BUSCAR LOS EQUIPOS
                    Equipo aux2 = new Equipo(datosPartido[1], " ", ' ');

                    Equipo eq1 = (Equipo) equipos.buscarNodo(aux1);// DEVUELVE LOS EQUIPOS NECESARIOS
                    Equipo eq2 = (Equipo) equipos.buscarNodo(aux2);
                    // sumo los goles y puntos
                    int golesEq1 = Integer.parseInt(datosPartido[5]);
                    int golesEq2 = Integer.parseInt(datosPartido[6]);
                    eq1.sumarGolesAFavor(golesEq1);
                    eq1.sumarGolesEnContra(golesEq2);
                    eq2.sumarGolesAFavor(golesEq2);
                    eq2.sumarGolesEnContra(golesEq1);
                    // Sumo los puntos si es que es fase de grupos
                    if (datosPartido[2].equals("GRUPO")) {
                        sumarPuntos(eq1, eq2, golesEq1, golesEq2);
                    }
                    partidos.asociar(eq1.getNombre() + " - " + eq2.getNombre(),
                            golesEq1 + " - " + golesEq2 + " | " + datosPartido[2]);
                    break;
                case "R":
                    String[] datosRuta = aux[1].split("; ");
                    Ciudad auxCiudad = new Ciudad(datosRuta[0], false, false);
                    Ciudad auxCiudad2 = new Ciudad(datosRuta[1], false, false);
                    Ciudad ciudad1 = (Ciudad) mapaCiudades.encontrarElemento(auxCiudad);
                    Ciudad ciudad2 = (Ciudad) mapaCiudades.encontrarElemento(auxCiudad2);
                    mapaCiudades.insertarArco(ciudad1, ciudad2, Integer.parseInt(datosRuta[2]));
                    break;
                default:
                    System.err.println("Carga erronea");
                    break;
            }
        }
    }

    public static String leerArchivo(String archivo) {
        String s = "";
        String linea = null;
        try {
            FileReader lectorArchivo = new FileReader(archivo);
            BufferedReader bufferLectura = new BufferedReader(lectorArchivo);

            while ((linea = bufferLectura.readLine()) != null) {
                s = s + "|" + linea;
            }
            bufferLectura.close();
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage() + "\nEl archivo no existe");
        } catch (IOException ex) {
            System.err.println("Error leyendo o escribiendo el archivo");
        }
        return s;
    }

    public static void sumarPuntos(Equipo eq1, Equipo eq2, int golesEq1, int golesEq2) {
        if (golesEq1 > golesEq2) {
            eq1.partidoGanado();
        } else if (golesEq2 > golesEq1) {
            eq2.partidoGanado();
        } else {
            eq1.partidoEmpatado();
            eq2.partidoEmpatado();
        }
    }

    public static void darAltaCiudad(GrafoEtiquetado ciudades) {
        Scanner sc = new Scanner(System.in);
        String nombre;
        boolean alojamiento, esSede;
        System.out.println("Ingrese nombre de la ciudad");
        nombre = sc.next().toUpperCase();
        System.out.println("¿Tiene alojamiento disponible? 1: si | 2: no");
        alojamiento = sc.nextInt() == 1;
        System.out.println("¿Es sede? 1: si | 2: no");
        esSede = sc.nextInt() == 1;
        Ciudad nuevaCiudad = new Ciudad(nombre, alojamiento, esSede);
        ciudades.insertarVertice(nuevaCiudad);
    }

    public static void darBajaCiudad(GrafoEtiquetado ciudades) {
        Scanner sc = new Scanner(System.in);
        String nombre;
        Ciudad aux;
        System.out.println("Ingrese el nombre de la ciudad a eliminar");
        nombre = sc.next().toUpperCase();
        aux = new Ciudad(nombre, false, false);
        ciudades.eliminarVertice(aux);
    }

    public static void modificarCiudad(GrafoEtiquetado ciudades) {
        Scanner sc = new Scanner(System.in);
        String nombre;
        int opcion, subOpcion;
        Ciudad aux, ciudadBuscada;
        System.out.println("Ingrese el nombre de la ciudad a modificar");
        nombre = sc.next().toUpperCase();
        aux = new Ciudad(nombre, false, false);
        ciudadBuscada = (Ciudad) ciudades.encontrarElemento(aux);
        if (ciudadBuscada != null) {
            do {
                System.out.println("1. Modificar la disponibilidad de alojamiento");
                System.out.println("2. Asignar/Desasignar como sede");
                System.out.println("3. Volver al inicio");
                opcion = sc.nextInt();
                if (opcion == 1) {
                    System.out.println("¿Tiene alojamiento disponible? 1: si | 2: no");
                    subOpcion = sc.nextInt();
                    if (subOpcion == 1) {
                        ciudadBuscada.tieneAlojamientoDisponible();
                    } else if (subOpcion == 2) {
                        ciudadBuscada.noTieneAlojamientoDisponible();
                    }
                }
            } while (opcion == 1 || opcion == 2);
        } else {
            System.out.println("Ciudad inexistente");
        }
    }

    public static void darAltaEquipo(ArbolAVL equipos) {
        Scanner sc = new Scanner(System.in);
        String nombreEquipo, nombreEntrenador;
        char grupo;
        Equipo nuevoEquipo;
        System.out.println("Ingrese nombre del equipo");
        nombreEquipo = sc.next().toUpperCase();
        System.out.println("Ingrese nombre del entrenador");
        nombreEntrenador = sc.next().toUpperCase();
        System.out.println("Ingrese el grupo");
        grupo = sc.next().toUpperCase().charAt(0);
        nuevoEquipo = new Equipo(nombreEquipo, nombreEntrenador, grupo);
        equipos.insertar(nuevoEquipo);
    }

    public static void darBajaEquipo(ArbolAVL equipos) {
        Scanner sc = new Scanner(System.in);
        String equipoBuscado;
        System.out.println("Ingrese el nombre del equipo que desee dar de baja");
        equipoBuscado = sc.next().toUpperCase();
        Equipo aux = new Equipo(equipoBuscado, " ", ' ');
        equipos.eliminar(aux);

    }

    public static void modificarEquipo(ArbolAVL equipos) {
        Scanner sc = new Scanner(System.in);
        String equipoBuscado, auxString;
        int opcion = 1;
        System.out.println("Ingrese el nombre del equipo para modificar");
        equipoBuscado = sc.next().toUpperCase();
        Equipo aux = new Equipo(equipoBuscado, " ", ' ');
        Equipo aux2 = (Equipo) equipos.buscarNodo(aux);
        if (aux2 != null) {
            System.out.println("1. Modificar entrenador");
            System.out.println("2. Volver al inicio");
            opcion = sc.nextInt();
            if (opcion == 1) {
                System.out.println("Ingrese el nombre del director tecnico");
                auxString = sc.next();
                aux2.setDirectorTecnico(auxString);
            }
        } else {
            System.err.println("Equipo inexistente");
        }

    }

    public static void cargarPartido(MapeoAMuchos partidos, ArbolAVL equipos) {
        Scanner sc = new Scanner(System.in);
        String primerEquipo, segundoEquipo, fase, ciudad, estadio;
        int golesEquipo1, golesEquipo2;
        Equipo equipo1, equipo2, aux1, aux2;
        System.out.println("Ingrese el primer equipo");
        primerEquipo = sc.next().toUpperCase();
        aux1 = new Equipo(primerEquipo, " ", ' ');
        System.out.println("Ingrese el segundo equipo");
        segundoEquipo = sc.next().toUpperCase();
        aux2 = new Equipo(segundoEquipo, " ", ' ');
        System.out.println("Ingrese fase");
        fase = sc.next().toUpperCase();
        System.out.println("Ingrese ciudad");
        ciudad = sc.next().toUpperCase();
        System.out.println("Ingrese estadio");
        estadio = sc.next().toUpperCase();
        System.out.println("Ingrese los goles del primer equipo");
        golesEquipo1 = sc.nextInt();
        System.out.println("Ingrese los goles del segundo equipo");
        golesEquipo2 = sc.nextInt();
        equipo1 = (Equipo) equipos.buscarNodo(aux1);
        equipo2 = (Equipo) equipos.buscarNodo(aux2);
        if (equipo1 != null && equipo2 != null) {
            if (fase.equals("GRUPO")) {
                sumarPuntos(equipo1, equipo2, golesEquipo1, golesEquipo2);
            }
            equipo1.sumarGolesAFavor(golesEquipo1);
            equipo1.sumarGolesEnContra(golesEquipo2);
            equipo2.sumarGolesAFavor(golesEquipo2);
            equipo2.sumarGolesEnContra(golesEquipo1);
            if (primerEquipo.compareTo(segundoEquipo) < 0) {
                partidos.asociar(primerEquipo + " - " + segundoEquipo,
                        golesEquipo1 + " - " + golesEquipo2 + " | " + fase);
            } else {
                partidos.asociar(segundoEquipo + " - " + primerEquipo,
                        golesEquipo2 + " - " + golesEquipo1 + " | " + fase);
            }
        } else {
            System.err.println("Uno de los equipos no existe");
        }
    }

    public static void consultaEquipos(ArbolAVL equipos) {
        Scanner sc = new Scanner(System.in);
        String s, min, max;
        Equipo aux, aux2, equipoBuscado;
        int opcion;
        do {
            System.out.println("1. Consultar datos del equipo");
            System.out.println("2. Mostrar equipos entre una cadena min y una cadena max");
            System.out.println("3. Volver al inicio");
            opcion = sc.nextInt();
            if (opcion == 1) {
                System.out.println("Ingresa el nombre del equipo");
                s = sc.next().toUpperCase();
                aux = new Equipo(s, " ", ' ');
                equipoBuscado = (Equipo) equipos.buscarNodo(aux);
                if (equipoBuscado != null) {
                    System.out.println(equipoBuscado.getDatos());
                } else {
                    System.out.println("Equipo inexistente");
                }
            } else if (opcion == 2) {
                System.out.println("Ingrese cadena min");
                min = sc.next().toUpperCase();
                aux = new Equipo(min, " ", ' ');
                System.out.println("Ingrese cadena max");
                max = sc.next().toUpperCase();
                aux2 = new Equipo(max, " ", ' ');
                System.out.println(equipos.listarRango(aux, aux2));
            }

        } while (opcion > 0 && opcion < 3);

    }

    public static void consultaPartidos(MapeoAMuchos partidos) {
        Scanner sc = new Scanner(System.in);
        String equipo1, equipo2;
        Lista partidosDisputados;
        System.out.println("Ingrese el primer equipo");
        equipo1 = sc.next().toUpperCase();
        System.out.println("Ingrese el segundo equipo");
        equipo2 = sc.next().toUpperCase();
        if (equipo1.compareTo(equipo2) < 0) {
            partidosDisputados = partidos.obtenerDominio(equipo1 + " - " + equipo2);
        } else {
            partidosDisputados = partidos.obtenerDominio(equipo2 + " - " + equipo1);
        }
        if (partidosDisputados.esVacia()) {
            System.out.println("No existen partidos entre " + equipo1 + " y " + equipo2);
        } else {
            System.out.println(partidosDisputados.toString());
        }
    }

    public static void consultaCiudades(GrafoEtiquetado ciudades) {
        Scanner sc = new Scanner(System.in);
        Ciudad aux1, aux2;
        String ciudad1, ciudad2;
        int opcion;
            System.out.println("1. Obtener la ruta con el menor tiempo de viaje");
            System.out.println("2. Obtener la ruta con la menor cantidad de escalas");
            System.out.println("3. Volver al menu principal");
            opcion = sc.nextInt();
            sc.nextLine();
            if (opcion == 2) {
                System.out.println("Ingrese la primer ciudad");
                ciudad1 = sc.nextLine().toUpperCase();
                System.out.println("Ingrese la segunda ciudad");
                ciudad2 = sc.nextLine().toUpperCase();
                aux1 = new Ciudad(ciudad1, false, false);
                aux2 = new Ciudad(ciudad2, false, false);
                System.out.println(ciudades.caminoMasCorto(aux1, aux2).toString());
            }
    }

    public static void tablaMejoresEquipos(ArbolAVL equipos) {
        Pila pilaEquipos = equipos.apilar();
        pilaEquipos.toString();
        HeapMax arbolHeap = new HeapMax(100);
        while (pilaEquipos.obtenerTope() != null) {
            Equipo aux = (Equipo) pilaEquipos.obtenerTope();
            MejorEquipo auxMejorEquipo = new MejorEquipo(aux.getNombre(), aux.getGolesAFavor());
            arbolHeap.insertar(auxMejorEquipo);
            pilaEquipos.desapilar();
        }
        int i = 1;
        while(!arbolHeap.esVacio()){
            System.out.println(i+"- "+arbolHeap.recuperarCima().toString());
            i++;
            arbolHeap.eliminarCima();
        }
    }
}
