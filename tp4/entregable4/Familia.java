package entregable4;

import java.util.Arrays;

/* Una familia, con su cantidad de dias, y una arreglo con el top de 4 dias preferidos */
public class Familia {

	private int id;
	private int miembros;
	private int[] diasPreferidos;
	private int diaAsignado;
	
	public Familia(int id, int miembros, int[] diasPreferidos) {
		this.id = id;
		this.miembros = miembros;
		this.diasPreferidos = diasPreferidos;
		this.diaAsignado = -1;
	}

	/* Id de la familia */
	public int getId() {
		return id;
	}


	/* Retorna la cantidad de miembros de la familia. */
	public int miembros() {
		return miembros;
	}

	
	/* Dado un indice entre 0 y 7, retorna el día preferido por la familia para ese indice. */
	public int preferenciaEn(int indice) {
		return this.diasPreferidos[indice];
	}
	
	/* Retorna el día preferido de la familia */
	public int diaPreferido() {
		return preferenciaEn(0);
	}
	
	/* Dado un dia pasado por parametro, indica el orden de ese dia en el top 8 de preferencias.
	Si el dia no forma parte de las preferencias del usuario, se retorna -1. */ 
	public int indiceDePreferencia(int dia) {
		for (int indice = 0; indice < diasPreferidos.length; indice++)
			if (dia == diasPreferidos[indice])
				return indice;
		return -1;
	}

	@Override
	public String toString() {
		return "Familia: id=" + id + ", miembros=" + miembros + ", preferencias=" + Arrays.toString(diasPreferidos);
	}
	
	public void asignarDia(int dia) {
		this.diaAsignado = dia;
	}
	
	public int diaAsignado() {
		return this.diaAsignado;
	}
	public int calcularBono(int dia) {
//		dia es el id, ej 33
		int res = 0;
		if(this.diaPreferido() == dia) {//diaPreferido = prefe[0]
			return res;
		}else {
//		U$S 25 + (U$S 10 * miembro del grupo familiar) + (U$S 5 * Día asignado)
			res += 25;
			res += 10 * this.miembros();
//			res += 5 * this.indiceDePreferencia(this.diaAsignado());
			res += 5 * this.indiceDePreferencia(dia);
		}
		return res;
	}
	public boolean estaAsignada() {
		return this.diaAsignado != -1;
	}
	public int bonoEnPeorDia() {
//		return this.calcularBono(this.diasPreferidos.length - 1);
		int i = this.diasPreferidos[this.diasPreferidos.length-1];
		return this.calcularBono(i);
	}
	public int cantDiasPreferidos() {
		return this.diasPreferidos.length;
	}
	public int bono() {
		return this.calcularBono(this.diaAsignado);
	}
}
