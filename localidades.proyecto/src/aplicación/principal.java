package aplicación;

import dominio.Localidad;
import dominio.Municipio;
import dominio.Provincia;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class principal {
    private static List<Provincia> provincias = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.print("Escribe una instrucción: ");
            String input = scanner.nextLine();

            switch (input.toLowerCase()) {
                case "leer":
                    leerDatos();
                    break;
                case "add provincia":
                    addProvincia(scanner);
                    break;
                case "add municipio":
                    addMunicipio(scanner);
                    break;
                case "add localidad":
                    addLocalidad(scanner);
                    break;
                case "list":
                    listarDatos();
                    break;
                case "exit":
                    running = false;
                    break;
                default:
                    System.out.println("Instrucción no reconocida.");
            }
        }
    }

    private static void leerDatos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("datos.dat"))) {
            provincias = (List<Provincia>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void guardarDatos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("datos.dat"))) {
            oos.writeObject(provincias);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addProvincia(Scanner scanner) {
        System.out.print("Nombre de la provincia: ");
        String nombre = scanner.nextLine();
        Provincia provincia = new Provincia(nombre);
        provincias.add(provincia);
        guardarDatos();
        System.out.println("Provincia añadida.");
    }

    private static void addMunicipio(Scanner scanner) {
        System.out.print("Nombre de la provincia: ");
        String nombreProvincia = scanner.nextLine();
        Provincia provincia = encontrarProvincia(nombreProvincia);
        if (provincia != null) {
            System.out.print("Nombre del municipio: ");
            String nombreMunicipio = scanner.nextLine();
            Municipio municipio = new Municipio(nombreMunicipio);
            provincia.agregarMunicipio(municipio);
            guardarDatos();
            System.out.println("Municipio añadido.");
        } else {
            System.out.println("Provincia no encontrada.");
        }
    }

    private static void addLocalidad(Scanner scanner) {
        System.out.print("Nombre de la provincia: ");
        String nombreProvincia = scanner.nextLine();
        Provincia provincia = encontrarProvincia(nombreProvincia);
        if (provincia != null) {
            System.out.print("Nombre del municipio: ");
            String nombreMunicipio = scanner.nextLine();
            Municipio municipio = provincia.encontrarMunicipio(nombreMunicipio);
            if (municipio != null) {
                System.out.print("Nombre de la localidad: ");
                String nombreLocalidad = scanner.nextLine();
                System.out.print("Número de habitantes: ");
                int numeroHabitantes = scanner.nextInt();
                scanner.nextLine(); // clear buffer
                Localidad localidad = new Localidad(nombreLocalidad, numeroHabitantes);
                municipio.agregarLocalidad(localidad);
                guardarDatos();
                System.out.println("Localidad añadida.");
            } else {
                System.out.println("Municipio no encontrado.");
            }
        } else {
            System.out.println("Provincia no encontrada.");
        }
    }

    private static Provincia encontrarProvincia(String nombre) {
        for (Provincia provincia : provincias) {
            if (provincia.getNombre().equalsIgnoreCase(nombre)) {
                return provincia;
            }
        }
        return null;
    }

    private static void listarDatos() {
        for (Provincia provincia : provincias) {
            System.out.println(provincia);
        }
    }
}