package entregable3;

import java.util.ArrayList;

public class Main {
	static ArrayList<Integer> solucion = new ArrayList<Integer>();
	static GrafoDirigido<Integer> grafito = new GrafoDirigido<Integer>();
	public static void main(String[] args) {
//		Tareas
		Tarea<Integer> t0 = new Tarea<Integer>(0);
		Tarea<Integer> t1 = new Tarea<Integer>(4);
		Tarea<Integer> t2 = new Tarea<Integer>(18);
		Tarea<Integer> t3 = new Tarea<Integer>(4);
		Tarea<Integer> t7 = new Tarea<Integer>(12);
		Tarea<Integer> t5 = new Tarea<Integer>(22);
		Tarea<Integer> t4 = new Tarea<Integer>(13);
		Tarea<Integer> t8 = new Tarea<Integer>(3);
		Tarea<Integer> t6 = new Tarea<Integer>(18);
		Tarea<Integer> t11 = new Tarea<Integer>(1);
		Tarea<Integer> t9 = new Tarea<Integer>(2);
		Tarea<Integer> t12 = new Tarea<Integer>(5);
		Tarea<Integer> t10 = new Tarea<Integer>(3);
//		Tareas agregadas al grafo
		grafito.agregarVertice(t0);
		grafito.agregarVertice(t1);
		grafito.agregarVertice(t2);
		grafito.agregarVertice(t3);
		grafito.agregarVertice(t4);
		grafito.agregarVertice(t5);
		grafito.agregarVertice(t6);
		grafito.agregarVertice(t7);
		grafito.agregarVertice(t8);
		grafito.agregarVertice(t9);
		grafito.agregarVertice(t10);
		grafito.agregarVertice(t11);
		grafito.agregarVertice(t12);
//		Arcos entre las tareas
		grafito.agregarArco(0, 1, 0);
		grafito.agregarArco(0, 2, 0);
		grafito.agregarArco(1, 3, 3);
		grafito.agregarArco(2, 5, 1);
		grafito.agregarArco(2, 7, 18);
		grafito.agregarArco(3, 4, 5);
		grafito.agregarArco(3, 6, 8);
		grafito.agregarArco(5, 6, 2);
		grafito.agregarArco(7, 8, 7);
		grafito.agregarArco(4, 11, 3);
		grafito.agregarArco(6, 12, 2);
		grafito.agregarArco(6, 10, 6);
		grafito.agregarArco(8, 9, 4);
		grafito.agregarArco(11, 12, 9);
		grafito.agregarArco(9, 10, 1);
		
//		Iteradores
//		VerticeIterator<Integer> itVerts = grafito.obtenerVertices();
//		System.out.println("Vertices:");
//		while(itVerts.hasNext()) {
//			System.out.print(itVerts.next().getInfo()+" |");
//		}
//		System.out.println();
//		VerticeIterator<Integer> itAdy = grafito.obtenerAdyacentes(t0);
//		System.out.println("Adyacentes:");
//		while(itAdy.hasNext()) {
//			System.out.print(itAdy.next().getInfo()+" |");
//		}
//		System.out.println();
//		ArcoIterator<Integer> itArcos = grafito.obtenerArcos();
//		System.out.println("Arcos:");
//		while(itArcos.hasNext()) {
//			System.out.print(itArcos.next().getInfo()+" |");
//		}
//		System.out.println();
//		ArcoIterator<Integer> itArcoVert = grafito.obtenerArcos(0);
//		System.out.println("Arcos:");
//		while(itArcoVert.hasNext()) {
//			System.out.print(itArcoVert.next().getInfo()+" |");
//		}
//		System.out.println();
		
		System.out.println("Tiempo del camino critico: " +dfs());
		System.out.println("Camino:");
		for(int i : solucion)
			System.out.print(i + " |");
	}
	public static int dfs(){
		ArrayList<Tarea<Integer>> tareas = getListByIterator(grafito.obtenerVertices());
		for(Tarea<Integer> t : tareas)
			t.setDescripcion("blanco");
		int tiempo = 0;
		int mejorTiempo = 0;
		for(Tarea<Integer> t : tareas) {
			if(t.getDescripcion() == "blanco") {
				mejorTiempo = dfs_visit(t);
				if(mejorTiempo > tiempo)
					tiempo = mejorTiempo;
			}
		}
		return tiempo;
	}
	public static int dfs_visit(Tarea<Integer> tarea) {
		int tiempo = 0;
		tarea.setDescripcion("amarillo");
		solucion.add(grafito.getIdDeUnVertice(tarea));
		tiempo = tiempo + tarea.getInfo();
		ArrayList<Tarea<Integer>> adyacentes = getListByIterator(grafito.obtenerAdyacentes(tarea));
		int mejorCamino = 0;
		int otroTiempo = 0;
		Tarea<Integer> tareaCritica = new Tarea<Integer>(0);
		for(Tarea<Integer> adj : adyacentes) {
			mejorCamino = grafito.getArcoByVertices(tarea, adj).getInfo();
			mejorCamino += getCamino(adj);
			if(otroTiempo < mejorCamino) {
				otroTiempo = mejorCamino;
				tareaCritica = adj;
				tareaCritica.setDescripcion("violeta");
			}
			mejorCamino = 0;
		}
		if(tareaCritica.getDescripcion() == "violeta") {
			tiempo += grafito.getArcoByVertices(tarea, tareaCritica).getInfo();
			tiempo += dfs_visit(tareaCritica);
		}
		tarea.setDescripcion("negro");
		return tiempo;
	}
	public static int getCamino(Tarea<Integer> tarea) {
		tarea.setDescripcion("verde");
		int tiempo = tarea.getInfo();
		int mejorCamino = 0;
		int otroTiempo = 0;
		ArrayList<Tarea<Integer>> adyacentes = getListByIterator(grafito.obtenerAdyacentes(tarea));
		for(Tarea<Integer> adj : adyacentes) {
			mejorCamino = grafito.getArcoByVertices(tarea, adj).getInfo();
			mejorCamino += getCamino(adj);
			if(otroTiempo < mejorCamino)
				otroTiempo = mejorCamino;
			mejorCamino = 0;
		}
		tiempo += otroTiempo;
		return tiempo;
	}
	public static ArrayList<Tarea<Integer>> getListByIterator(VerticeIterator<Integer> it){
		ArrayList<Tarea<Integer>> res = new ArrayList<Tarea<Integer>>();
		while(it.hasNext())
			res.add((Tarea<Integer>)it.next());
		return res;
	}
}