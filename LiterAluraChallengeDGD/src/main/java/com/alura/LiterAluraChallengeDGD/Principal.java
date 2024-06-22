package com.alura.LiterAluraChallengeDGD;

import com.alura.LiterAluraChallengeDGD.Convertidores.ConsumoApi;
import com.alura.LiterAluraChallengeDGD.Convertidores.Deserializacion;
import com.alura.LiterAluraChallengeDGD.Model.*;
import com.alura.LiterAluraChallengeDGD.Repository.LibroRepository;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    ConsumoApi consumoApi = new ConsumoApi();
    Deserializacion convertidor = new Deserializacion();
    final String URL_BASE = "https://gutendex.com/books/?search=";
    Scanner teclado = new Scanner(System.in);
    List<Libro> listaLibros = new ArrayList();

    private LibroRepository repositorio;

    public Principal(LibroRepository repository) {
        this.repositorio = repository;

    }
    private void buscarLibro(){
        System.out.println("Introduce el libro que desea buscar");
        String tituloLibroBuscado = teclado.nextLine();
        var json = consumoApi.ConsumoApi(URL_BASE + tituloLibroBuscado.replace(" ", "+"));
        var datosBusqueda = convertidor.deserializa(json, DatosGenerales.class);
        List<LibroDTO> listaDatos = datosBusqueda.datosGenerales();
        Optional<LibroDTO> libroBuscado = datosBusqueda.datosGenerales().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloLibroBuscado.toUpperCase()))
                .findFirst();
        if (libroBuscado.isPresent()) {
            try{System.out.println("Libro Encontrado");
                Libro libro = new Libro(libroBuscado.get().titulo(),libroBuscado.get().autor(),libroBuscado.get().idiomas(),libroBuscado.get().numeroDescargas());
                System.out.println(libro);
                listaLibros.add(libro);
                repositorio.save(libro);}
            catch (DataIntegrityViolationException e){
                System.out.println("El Libro ya se encuentra registrado, favor de intentar con otro libro");
            }

        } else {
            System.out.println("Libro No encontrado, intentelo nuevamente");
        }
        System.out.println("La lista de Libros es: " + listaLibros);
        DoubleSummaryStatistics est = datosBusqueda.datosGenerales().stream()
                .filter(d -> d.numeroDescargas() >0)
                .collect(Collectors.summarizingDouble(LibroDTO::numeroDescargas));
        System.out.println("Cantidad promedio de Descargas " + est.getAverage());
        System.out.println("Cantidad Máxima de Descargas " + est.getMax());
        System.out.println("Numero de Registros utilizados para la generación de las estadisticas " + est.getCount());
    }

    public void menuOpciones () {
        var option = -1;
        while (option != 0){
            var menu = ("""
                \nElija la opción que desea realizar
                1 - Buscar libro por Titulo
                2 - Lista de libros registrados
                3 - Busqueda por autor entre los libros registrados con anterioridad
                4 - Busqueda de Autores vivos en cierto año
                5 - Busqueda de  libros por idioma entre los libros registrados con anterioridad
                6 - Top 10 libros mas descargados
                0 - Salir               
                
                **********************************************          
                """);
            System.out.println(menu);
            option = teclado.nextInt();
            teclado.nextLine();
            switch (option) {
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    listarLibros();
                    break;
                case 3:
                    busquedaPorAutor();
                    break;
                case 4:
                    busquedaPorAnio();
                    break;
                case 5:
                    busquedaPorIdioma();
                    break;
                case 6:
                    top10();
                    break;
                case 7:
                    generarEstadisticas();
                    break;
                case 0:
                    System.out.println("Gracias por usar Nuestros Servicios!\n");
                    break;
                default:
                    System.out.println("Opción No valida, Intente nuevamente");
                    break;
            }

        }



        }

    private void generarEstadisticas() {
        var json = consumoApi.ConsumoApi(URL_BASE);
        var datosBusqueda = convertidor.deserializa(json, DatosGenerales.class);

        DoubleSummaryStatistics est = datosBusqueda.datosGenerales().stream()
                .filter(d -> d.numeroDescargas() >0)
                .collect(Collectors.summarizingDouble(LibroDTO::numeroDescargas));
        System.out.println("Cantidad promedio de Descargas " + est.getAverage());
        System.out.println("Cantidad Máxima de Descargas " + est.getMax());
        System.out.println("Numero de Registros utilizados para la generación de las estadisticas " + est.getCount());



    }

    private void listarLibros() {
        List<Libro> listaDeLibros = repositorio.listaDeLibros();
        System.out.println(listaDeLibros);

    }

    private void busquedaPorAutor() {
        System.out.println("Tecleé el nombre y/o apellido del autor que desea Buscar");
        String autorBuscado = teclado.nextLine().toUpperCase();
        System.out.println("Autor es " + autorBuscado);
       List<Libro> listaPorAutor = repositorio.busquedaPorAutor(autorBuscado);
        System.out.println(listaPorAutor);


    }

    private void busquedaPorAnio() {
        System.out.println("Introduce el año que deseas Buscar");
        int anio = teclado.nextInt();
        List<Libro>librosPorAnio = repositorio.busquedaPorAnio(anio);
        System.out.println("Lista de libros del año: " + anio +"\n" + librosPorAnio);

    }

    private void busquedaPorIdioma() {
        System.out.println("""
                Elige el idioma del libro que desea buscar
                en - Inglés
                es - Español
                fr - Francés
                pt - Portugués
                it - Italiano
                """);
        String idiomaElegido = teclado.nextLine().toLowerCase();
        System.out.println(idiomaElegido);
        List<Libro> datosTabla = repositorio.findAll();
        List<Libro> listaFiltradaIdioma = datosTabla.stream()
                .filter(l -> l.getIdiomas().contains(idiomaElegido))
                .collect(Collectors.toList());
        System.out.println(listaFiltradaIdioma);

    }


    private void top10() {
        var json = consumoApi.ConsumoApi(URL_BASE);
        var datosBusqueda = convertidor.deserializa(json, DatosGenerales.class);
        System.out.println("Top 10 Libros más descargados en GUTENDEX");
       datosBusqueda.datosGenerales().stream()
                .sorted(Comparator.comparing(LibroDTO::numeroDescargas).reversed())
                .limit(10)
               .map(l -> l.titulo().toUpperCase())
                .forEach(System.out::println);


    }

}

    //aqui termina el while




