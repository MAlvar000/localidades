package presentacion;
import dominio.*;
import java.io.*;
import java.util.*;
public class Interfaz {
    private Pais p=new Pais();
    private  Scanner sc = new Scanner(System.in);
    public Interfaz(){
  	p=Pais.leer();
    }

    public void grabar(){
	p.grabar();
    }
    public static void ayuda(){
        System.out.println("introduzca una de las siguientes peticiones: \n addProvindia: añadir provincia\n addMunicipio: añadir Municipio\n addLocalidad: añadir Localidad\n list: listar el contenido\n read: lectura inicial\n exit: salir\n");

    }
    public  boolean procesarPeticion (String [] p){
        if (p.length==1)
            if 	(p[0].equals("addProvincia"))
               aniadirProvincia();
            else if (p[0].equals("addMunicipio"))
               aniadirMunicipio();
            else if (p[0].equals("addLocalidad"))
               aniadirLocalidad();
            else if (p[0].equals("read"))
	            leer();
	        else if (p[0].equals("list"))
                    System.out.println (this.p);
            else if (p[0].equals("help"))
		    ayuda();
            else if (p[0].equals("exit")){
                    grabar();
                    return false;//no se procesarán más peticiónes
            }
            else {
                    System.out.println("petición erronea");
                    ayuda();
                }
        else {
            System.out.println("petición erronea");
            ayuda();
        }
        return true;//en todos los casos debe seguir pidiendo y procesando peticiones
    }
    public  void aniadirProvincia(){
	System.out.print ("Nombre de la provincia: ");
	String nombre=sc.nextLine();
	p.add(new Provincia(nombre));
    }

    public  void aniadirMunicipio(){
	System.out.println("Listado de provincias");
	for (int i=0;i<p.size();i++)
	    System.out.println(i+".- "+p.getProvincia(i));
	System.out.print("Número de provincia: ");
	int i=sc.nextInt();
	sc.nextLine();
	System.out.print("Nombre del municipio: ");
	String nombre=sc.nextLine();
	p.getProvincia(i).add(new Municipio(nombre));
    }

    public  void aniadirLocalidad(){
	System.out.println("Listado de provincias");
	for (int i=0;i<p.size();i++)
	    System.out.println(i+".- "+p.getProvincia(i));
	System.out.print("Número de provincia: ");
	int i=sc.nextInt();
	Provincia pr=p.getProvincia(i);
	sc.nextLine();
	System.out.println("Listado de municipios de la provincia de "+pr.getNombre());
	for (i=0;i<pr.size();i++)
	    System.out.println(i+".- "+pr.getMunicipio(i));
	System.out.print("Número de municipio: ");
	i=sc.nextInt();
	sc.nextLine();
	Municipio mn=pr.getMunicipio(i);
	System.out.print("Nombre de la localidad: ");
	String nombre=sc.nextLine();
	System.out.print("Poblacion: ");
	int poblacion=sc.nextInt();
	sc.nextLine();
	mn.add(new Localidad(nombre,poblacion));

    }
    public String [] leerPeticion(){
        System.out.print("?>");
        String cadena = sc.nextLine();
        return cadena.split(" ");
    }

    public  void leer(){
            String nombreP, nombreM, nombreL;
	    p.setNombre("España");
            do {
            System.out.print("Introduce el nombre de la provincia (<enter> para finalizar: ");
            nombreP=sc.nextLine();
            if(!nombreP.equals("")){
                Provincia provincia=new Provincia(nombreP);
                do {
                    System.out.print("Introduce el nombre del municipio (<enter> para finalizar: ");
                    nombreM=sc.nextLine();
                    if(!nombreM.equals("")){
                        Municipio municipio=new Municipio(nombreM);
                        do {
                            System.out.print("Introduce el nombre de la localidad (<enter> para finalizar: ");
                            nombreL=sc.nextLine();
                            if(!nombreL.equals("")){
                                System.out.print("Introduce la población de la localidad: ");
                                int poblacion=sc.nextInt();
                                sc.nextLine();
                                Localidad localidad=new Localidad(nombreL,poblacion);
                                municipio.add(localidad);
                            }
                        } while (!nombreL.equals(""));
                        provincia.add(municipio);
                    }
                } while (!nombreM.equals(""));
                p.add(provincia);
            }
    }
    while (!nombreP.equals(""));

    }

}