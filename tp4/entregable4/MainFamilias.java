package entregable4;
import java.util.ArrayList;
import java.util.Collections;


public class MainFamilias {

//	static ArrayList<Dia> solucion = new ArrayList<>();
	public static void main(String[] args) {
		CSVReader reader = new CSVReader("/home/kappaggaeru/eclipse-workspace/Programacion3/data/familias.csv");
		ArrayList<Familia> familias = reader.read();
		Taller musk = new Taller(340,100);
		int bonos = greedy(familias, musk);
		printCronograma(musk);
		
		
//		for(Familia f:familias) {
//			System.out.println(f+" Bono: $"+f.bono());
//		}
		System.out.println("Bonos: $"+bonos);
	}
	public static int greedy(ArrayList<Familia> familias, Taller taller) {
		int bonos = 0;
//		int flotantes = 0;//familias sin asignar
//		ordenarPorMasCaro(familias);
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
//					solucion.add(dia);
				}else {
					i++;
				}
			}
//			if(!f.estaAsignada()) {
//				flotantes++;
//			}
		}
//		int diasIncompletos = 0;
//		DiasIterator it = taller.diasIterator();
//		while(it.hasNext()) {
//			Dia d = it.next();
//			if(d.estaCompleto()) {
//				diasIncompletos++;
//			}
//		}
//		System.out.println("Días incompletos: "+diasIncompletos);
//		System.out.println("Familias sin asignar: "+flotantes);
		return bonos;
	}
	public static void mejora(Taller taller) {
		
	}
	public static void printCronograma(Taller musk) {
		System.out.println("<Cronograma>");
		DiasIterator itDias = musk.diasIterator();
		while(itDias.hasNext()) {
			Dia d = itDias.next();
			System.out.println("Día: "+d.getId()+" Capacidad usada: "+d.getCapacidad());
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
