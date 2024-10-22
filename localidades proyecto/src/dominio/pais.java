package dominio;

import java.util.ArrayList;
import java.io. Serializable;

public class Pais implements Serializable {
    private String nombre;
    private ArrayList<Provincia> provincias;
    public Pais add(Provincia provincia){
        provincias.add(provincia);
        return this;
    }
    public String getNombre() {
        return nombre;
    }
    public int getPoblacion(){
        int poblacion=0;
        for(Provincia provincia:provincias){
        poblacion+=provincia.getPoblacion();
        }
        return poblacion;
    }
    public ArrayList<Provincia> getProvincias(){
        return provincias;
    }
    public Provincia getProvincia(int i){
        return provincias.get(i);
    }
    public String toString(){
        return "Pais: "+nombre+" Población: "+getPoblacion() +" habitantes\n"+provincias.toString()+"\n";
    }
    public int size(){
        return provincias.size();
    }
    public void grabar(){
	try{
	    OutputObjectSetream fo=new OutputObjectSetream ( new FileOutStream("pais.dat"));
	    fo.writeObject(this);
	} catch (Exception e) {
	    System.out.println("error de escritura");
	}
    }
    public static Pais leer(){
	try{
	   ObjectInputStream fi=new ObjectInputStream(new FileInputStream("pais.se"));
	   Pais p= (Pais) fi.readObject();
	   fi.close();
	   return p;
	}  catch (Exception e){
	    return  new Pais();
	}
    }

}