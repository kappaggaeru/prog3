package entregable4;
import java.util.ArrayList;
import java.util.Collections;


public class MainFamilias {

	static ArrayList<Dia> solucion = new ArrayList<>();
	public static void main(String[] args) {
		CSVReader reader = new CSVReader("/home/kappaggaeru/eclipse-workspace/Programacion3/data/familias.csv");
		ArrayList<Familia> familias = reader.read();
		Taller musk = new Taller(340,100);
//		ordenarPorMasCaro(familias);
//		for (Familia familia: familias)
//		for(int i=0; i<10; i++) {
//			Familia familia = familias.get(i);
//			System.out.println(familia+" Bono peor dia: "+familia.bonoEnPeorDia());
//		}
		int bonos = greedy(familias, musk);
		System.out.println("aca estan tus bonos 8): "+bonos);
	}
	public static int greedy(ArrayList<Familia> familias, Taller taller) {
		int bonos = 0;
		int flotantes = 0;//familias sin asignar
		ordenarPorMasCaro(familias);
		for(Familia f: familias) {//recorre las 5000 familias
			int i = 0;
			while(i < f.cantDiasPreferidos() && !f.estaAsignada()) {//recorre sus dias preferidos
				int diaPrefe = f.preferenciaEn(i);
				Dia dia = taller.getDia(diaPrefe);
				if(dia.aceptaFamilia(f)) {//
					dia.agregarFamilia(f);
					f.asignarDia(dia.getId());
					bonos += f.calcularBono(dia.getId());
				}else {
					i++;
				}
			}
			if(!f.estaAsignada()) {
				flotantes++;
			}
		}
		int diasIncompletos = 0;
		DiasIterator it = taller.diasIterator();
		while(it.hasNext()) {
			Dia d = it.next();
			if(d.estaCompleto()) {
				diasIncompletos++;
			}
		}
		System.out.println("Días incompletos: "+diasIncompletos);
		System.out.println("Familias sin asignar: "+flotantes);
		return bonos;
	}
	public static Familia seleccionar(ArrayList<Familia> conjunto) {
		return null;
	}
	public static void ordenarPorMasCaro(ArrayList<Familia> familias) {
		Collections.sort(familias, new SortMasCaro());
		Collections.reverse(familias);
	}
}
