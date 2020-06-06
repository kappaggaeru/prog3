package entregable4;
import java.util.ArrayList;
import java.util.Collections;
public class MainFamilias {
	static Solucion solucion = new Solucion();
	public static void main(String[] args) {
		CSVReader reader = new CSVReader("/home/kappaggaeru/eclipse-workspace/Programacion3/data/familias.csv");
		ArrayList<Familia> familias = reader.read();
		Taller musk = new Taller(340,100);
		solucion.setBonos(greedy(familias, musk));
		printCronograma(musk);
		System.out.println("Bonos: $"+solucion.getBonos());
		System.out.println("Familias asignadas: "+solucion.getFamilias());
		System.out.println("Días completos: "+solucion.getDiasCompletos());
		System.out.println("Días incompletos: "+solucion.getDiasIncompletos());
		
//		System.out.println("Familias con bono: "+familiasConBono(familias).size());
//		mejora(familiasConBono(familias), musk);
//		int bon = 0;
//		for(Familia f: familias) {
//			bon+= f.bono();
//		}
//		System.out.println("Bonos con mejora: "+bon);
	}
	public static int greedy(ArrayList<Familia> familias, Taller taller) {
		int bonos = 0;
		ordenarPorMiembros(familias);
		for(Familia f: familias) {//recorre las 5000 familias
			int i = 0;
			while(i < f.cantDiasPreferidos() && !f.estaAsignada()) {//recorre sus dias preferidos
				int diaPrefe = f.preferenciaEn(i);
				Dia dia = taller.getDia(diaPrefe);
				if(dia.aceptaFamilia(f)) {
					dia.agregarFamilia(f);
					f.asignarDia(dia.getId());
					bonos += f.calcularBono(dia.getId());
				}else {
					i++;
				}
			}
			if(f.estaAsignada()) {
				solucion.setFamilias(1);
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
	public static ArrayList<Familia> familiasConBono(ArrayList<Familia> familias){
		ArrayList<Familia> res = new ArrayList<>();
		for(Familia f:familias) {
			if(f.bono() > 0) {
				res.add(f);
			}
		}
		return res;
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
					int bonoF1 = f1.bono();
					int bonoF2 = f2.bono();
					int bonoAux1 = f1.calcularBono(dia2.getId());
					int bonoAux2 = f2.calcularBono(dia1.getId());
					if(bonoF1 > bonoAux1 && bonoF2 > bonoAux2){
						dia1.removeFamilia(f1);
						dia2.removeFamilia(f2);
						if(intercambiarDias(f1,dia1,dia2) && intercambiarDias(f2,dia2,dia1)) {
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
	public static boolean intercambiarDias(Familia f1, Dia dia1, Dia dia2) {
		if(dia2.aceptaFamilia(f1)) {
			dia1.removeFamilia(f1);
			dia2.agregarFamilia(f1);
			f1.asignarDia(dia2.getId());
			return true;
		}
		return false;
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
}
