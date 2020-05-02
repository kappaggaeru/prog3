package entregable1;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
public class Main {
	public static void main(String[] args) {
		ArrayList<LinkedList> salida = new ArrayList<LinkedList>();
		LinkedList entrada = new LinkedList();
		
//		cargaManual(entrada,5);
		cargaAutomatica(entrada,20,1,30);
		imprimirLista(entrada);
		salida = getSecuencias(entrada, 2);
		imprimirSecuencias(salida);
	}
	public static ArrayList<LinkedList> getSecuencias(LinkedList entrada, int minimo) {
		ArrayList<LinkedList> salida = new ArrayList<LinkedList>();
		MyIterator it = entrada.iterator();
		ArrayList<Integer> aux = new ArrayList<Integer>();
		int num1 = 0;
		int num2 = 0;
		while(it.hasNext()) {
			num1 = it.get();
			it.next();
			if(it.hasNext()) {
			num2 = it.get();
				if(num1 > num2) {
					aux.add(num1);
					if(aux.size() >= minimo) {
						salida.add(grabarSecuencia(aux));
					}
					aux.clear();
				}
				else if(num1 != num2){
					aux.add(num1);
				}
			}
			if(!it.hasNext()) {
				aux.add(num2);
				if(aux.size() >= minimo) {
					salida.add(grabarSecuencia(aux));
				}
			}
		}
		return salida;
	}
	public static LinkedList grabarSecuencia(ArrayList<Integer> aux) {
		LinkedList tmp = new LinkedList();
		Iterator<Integer> it = aux.iterator();
		while(it.hasNext()) {
			tmp.insertBack(it.next());
		}
		return tmp;
	}
	public static void imprimirSecuencias(ArrayList<LinkedList> salida) {
		System.out.println("\nCantidad de secuencias: "+salida.size());
		for(int i=0; i < salida.size(); i++) {
			MyIterator it = salida.get(i).iterator();
			System.out.print("[");
			while(it.hasNext()) {
				System.out.print(it.next());
				if(it.hasNext()) {//verifica si es el ultimo
					System.out.print(", ");
				}
			}
			System.out.print("]");
			if(i < salida.size()-1) {
				System.out.print("; ");
			}
		}
	}
	public static void imprimirLista(LinkedList lista) {
		for(Integer elem: lista) {
			System.out.print(elem+" ");
		}
	}
	public static void cargaAutomatica(LinkedList entrada, int size, int min, int max) {
		for(int i=0; i<size; i++) {
			int num = (int)((Math.random()*max)+min);
			entrada.insertBack(num);
		}
	}
	public static void cargaManual(LinkedList entrada, int size) {
		System.out.println("Ingrese "+size+" numero/s:");
		for(int i=0; i<size; i++) {
			int num = leerTeclado();
			entrada.insertBack(num);
		}
	}
	public static int leerTeclado() {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		try {
			int num = Integer.parseInt(bf.readLine());
			return num;
		}
		catch(Exception exc) {
			System.out.println("Error al ingresar un numero, intente otra vez:");
			return leerTeclado();
		}
	}
}
