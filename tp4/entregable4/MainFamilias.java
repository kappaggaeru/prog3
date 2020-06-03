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
	public static void mejora(Taller taller) {
		
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
