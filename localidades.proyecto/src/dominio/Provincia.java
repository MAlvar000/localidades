package dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Provincia implements Serializable {
    private String nombre;
    private List<Municipio> municipios;

    public Provincia(String nombre) {
        this.nombre = nombre;
        this.municipios = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarMunicipio(Municipio municipio) {
        municipios.add(municipio);
    }

    public int contarHabitantes() {
        int totalHabitantes = 0;
        for (Municipio municipio : municipios) {
            totalHabitantes += municipio.contarHabitantes();
        }
        return totalHabitantes;
    }

    public Municipio encontrarMunicipio(String nombre) {
        for (Municipio municipio : municipios) {
            if (municipio.getNombre().equalsIgnoreCase(nombre)) {
                return municipio;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Provincia: ").append(nombre).append("\n");
        for (Municipio municipio : municipios) {
            sb.append(municipio).append("\n");
        }
        return sb.toString();
    }
}