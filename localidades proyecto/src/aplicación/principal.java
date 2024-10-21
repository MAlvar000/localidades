package aplicacion;

import dominio.Localidad;
import dominio.Municipio;
import dominio.Provincia;

public class Principal {
    public static void main(String[] args) {
        Localidad localidad = new Localidad("Madrid", 3200000);
        System.out.println(localidad);
        
        // Ejemplo de Municipio
        Municipio municipio = new Municipio("Madrid");
        municipio.addLocalidad(localidad);
        System.out.println("Total de habitantes en el municipio: " + municipio.contarHabitantes());
        
        // Ejemplo de Provincia
        Provincia provincia = new Provincia("Madrid");
        provincia.addMunicipio(municipio);
        System.out.println("Total de habitantes en la provincia: " + provincia.contarHabitantes());
    }
}
