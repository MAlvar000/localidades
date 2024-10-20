package aplicacion;

import dominio.Localidad;
import dominio.Municipio;
import dominio.Provincia;

public class Principal {
    public static void main(String[] args) {
        // Crear una localidad
        Localidad localidad = new Localidad("Madrid", 3223334);
        System.out.println(localidad);

        // Crear un municipio y agregar la localidad
        Municipio municipio = new Municipio("Madrid");
        municipio.agregarLocalidad(localidad);
        System.out.println(municipio);
        System.out.println("Total de habitantes en el municipio: " + municipio.contarHabitantes());

        // Crear una provincia y agregar el municipio
        Provincia provincia = new Provincia("Madrid");
        provincia.agregarMunicipio(municipio);
        System.out.println(provincia);
        System.out.println("Total de habitantes en la provincia: " + provincia.contarHabitantes());
    }
}
