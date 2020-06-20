package entregable4;
import java.util.ArrayList;
import java.util.Collections;
public class MainFamilias {
	static Solucion solucion = new Solucion();
	public static void main(String[] args) {
		CSVReader reader = new CSVReader("/home/kappaggaeru/eclipse-workspace/Programacion3/data/familias.csv");
		ArrayList<Familia> familias = reader.read();
		Taller musk = new Taller(340,100);
		solucion.setBonos(pasadaPreferencial(familias,musk));
		printCronograma(musk);
		System.out.println("Bonos: $"+solucion.getBonos());
		System.out.println("Familias asignadas: "+solucion.getFamilias());
		System.out.println("Días completos: "+solucion.getDiasCompletos());
		System.out.println("Días incompletos: "+solucion.getDiasIncompletos());
		
//		System.out.println("Familias con bono: "+familiasConSinBono(familias,1).size());
//		mejora(familiasConBono(familias,1), musk);
		
//		cambiarFamilias(familiasConSinBono(familias,1),familiasConSinBono(familias,0),musk);
		
//		int bon = 0;
//		for(Familia f: familias)
//			bon+= f.bono();
//		System.out.println("Bonos con mejora: "+bon);
	}
	public static int pasadaPreferencial(ArrayList<Familia> familias, Taller taller) {
		ArrayList<Familia> res = new ArrayList<>();
		ordenarPorMiembros(familias);
		for(Familia f: familias) {//recorre las 5000 familias
			int diaPrefe = f.diaPreferido();
			Dia dia = taller.getDia(diaPrefe);
			if(dia.aceptaFamilia(f)) {
				dia.agregarFamilia(f);
				f.asignarDia(dia.getId());
				solucion.setFamilias(1);
			}else {
				res.add(f);
//				agrega las familias que quedaron fuera de la 1er selección
			}
		}
		return pasadaBonificada(res,taller);
	}
	public static int pasadaBonificada(ArrayList<Familia> familias, Taller taller) {
		int bonos = 0;
		for(Familia f: familias) {
			int i = 0;
			while(i < f.cantDiasPreferidos() && !f.estaAsignada()) {
				int diaPrefe = f.preferenciaEn(i);
				Dia dia = taller.getDia(diaPrefe);
				if(dia.aceptaFamilia(f)) {
					dia.agregarFamilia(f);
					f.asignarDia(dia.getId());
					bonos += f.bono();
					solucion.setFamilias(1);
				}else {
					i++;
				}
			}
		}
		DiasIterator it = taller.diasIterator();
		while(it.hasNext()) {
			Dia d = it.next();
			if(d.estaCompleto()) {
				solucion.setDiasCompletos(1);;
			}else {
				solucion.setDiasIncompletos(1);
			}
		}
		return bonos;
	}
	public static void printCronograma(Taller musk) {
		System.out.println("<Cronograma>");
		DiasIterator itDias = musk.diasIterator();
		while(itDias.hasNext()) {
			Dia d = itDias.next();
			System.out.println("Día: "+d.getId()+" Capacidad usada: "+d.getCapacidad()+" Cantidad de familias: "+d.totalFamilias());
			System.out.print("Familias [");
			FamiliasIterator itFlias = d.familiasIterator();
			while(itFlias.hasNext()) {
				Familia f = itFlias.next();
				System.out.print(" "+f.getId());
			}
			System.out.print("]");
			System.out.println();
		}
	}
	public static void ordenarPorMiembros(ArrayList<Familia> familias) {
		Collections.sort(familias, new SortMiembros());
	}
	public static void mejora(ArrayList<Familia> familias, Taller taller) {
		for(int i=0;i<familias.size();i++) {
			Familia f1= familias.get(i);
			Dia dia1 = taller.getDia(f1.diaAsignado());
			int j = i+1;
			boolean cambio = true;
			while(j<familias.size() && cambio) {
				Familia f2 = familias.get(j);
				Dia dia2 = taller.getDia(f2.diaAsignado());
				if(f1.contieneDia(dia2.getId()) && f2.contieneDia(dia1.getId())) {
					int bonoActual = f1.bono() + f2.bono();
					int bonoNuevo = f1.calcularBono(dia2.getId()) + f2.calcularBono(dia1.getId());
					if(bonoNuevo < bonoActual){
						dia1.removeFamilia(f1);
						dia2.removeFamilia(f2);
						if(dia1.aceptaFamilia(f2) && dia2.aceptaFamilia(f1)) {
							dia1.agregarFamilia(f2);
							f2.asignarDia(dia1.getId());
							dia2.agregarFamilia(f1);
							f1.asignarDia(dia2.getId());
							cambio = false;
						}else {
							dia1.agregarFamilia(f1);
							dia2.agregarFamilia(f2);
						}
					}

				}
				j++;
			}
		}
	}
	public static ArrayList<Familia> familiasConSinBono(ArrayList<Familia> familias,int op){
		ArrayList<Familia> res = new ArrayList<>();
		for(Familia f:familias) {
			if(op == 1) {
//				op 1 devuelve las familias que reciben el bono
				if(f.bono() > 0) {
					res.add(f);
				}
			}else {
//				familias que entran gratis
				if(f.bono() == 0) {
					res.add(f);
				}
			}
		}
		return res;
	}
	public static void cambiarFamilias(ArrayList<Familia> familiasConBono, ArrayList<Familia> familiasSinBono,Taller taller){
		ArrayList<Familia> familiasEnEspera = new ArrayList<>();
		int maxNumMiembros = 8;
		for(Familia f:familiasSinBono){
			if(f.miembros() == maxNumMiembros){
				Dia diaPreferido = taller.getDia(f.diaAsignado());
				int capacidad = 0;
					for(Familia fAux:familiasConBono) {
						if(fAux.diaPreferido() == diaPreferido.getId()) {
							if(fAux.miembros() + capacidad <= maxNumMiembros) {
								familiasEnEspera.add(fAux);
								capacidad += fAux.miembros();
							}
							if(capacidad == maxNumMiembros) {
								break;
							}
						}
					}
				int bonoActual = f.bono();
				int bonoNuevo = 0;
				for(Familia f2:familiasEnEspera) {
					bonoNuevo += f2.calcularBono(diaPreferido.getId());
				}
				if(bonoNuevo < bonoActual) {
					if(ubicarFamiliaDondeSea(f,taller)) {
						diaPreferido.removeFamilia(f);
						for(Familia f2:familiasEnEspera) {
							f2.asignarDia(diaPreferido.getId());
							diaPreferido.agregarFamilia(f2);
						}
					}
				}
			}
		}
	}
	public static boolean ubicarFamiliaDondeSea(Familia f,Taller taller) {
		int i=1;//empieza en 1 porque 0 es el dia que esta intercambiando
		boolean exito = false;
		while(i < f.cantDiasPreferidos() && !exito) {
			int diaPrefe = f.preferenciaEn(i);
			Dia dia = taller.getDia(diaPrefe);
			if(dia.aceptaFamilia(f)) {
				dia.agregarFamilia(f);
				f.asignarDia(dia.getId());
				exito = true;
			}
			i++;
		}
		return exito;
	}
}
