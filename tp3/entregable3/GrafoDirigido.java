package entregable3;

import java.util.ArrayList;
import java.util.HashMap;

public class GrafoDirigido<T> implements Grafo<T>{
	private HashMap<Integer,VerticeAbs<T>> vertices;
	private HashMap<Integer,Arco<T>> arcos;
	private int index;
	private int indey;

	public GrafoDirigido(){
		vertices = new HashMap<Integer,VerticeAbs<T>>(); 
		arcos = new HashMap<Integer,Arco<T>>();
		index = 0;
		indey = 0;
	}
	@Override
	public void agregarVertice(T info) {
		VerticeAbs<T> v = new Vertice<T>(info);
		this.agregarVertice(v);
	}
//	Complejidad temporal: O(1) donde 1 representa la atomicidad para consultar e insertar en un hashMap
	public void agregarVertice(VerticeAbs<T> v) {
		if(!vertices.containsValue(v)) {
			vertices.put(index, v);
			index++;
		}
	}
//	Complejidad temporal: O(1) donde 1 representa la atomicidad para insertar y consultar en un hashMap, agregando los mensajes entre los objetos
	@Override
	public void agregarArco(int v1, int v2, T info) {
		if(contieneVertice(v1) && contieneVertice(v2)) {
			VerticeAbs<T> ver1 = vertices.get(v1);
			VerticeAbs<T> ver2 = vertices.get(v2);
			if(!ver1.esAdyacente(ver2)) {
				Arco<T> aux = new Arco<T>(ver1, ver2, info);
				ver1.addAdyacente(ver2);
				arcos.put(indey, aux);
				indey++;
			}
		}
	}
//	Complejidad temporal: O(n) donde n representa la cantidad de arcos que puede llegar a tener un vertice en el peor caso, n salientes y n entrantes
	@Override
	public void borrarVertice(int id) {
		if(this.contieneVertice(id)) {
			VerticeAbs<T> aux = vertices.get(id);
			if(!this.tieneArcosEntrantes(id)) {//nadie lo apunta
				this.borrarArcosSalientes(aux);
				vertices.remove(id);
			}else if(aux.getTotalAdyacentes() == 0) {//lo apuntan y es un endpoint
				this.borrarArcosEntrantes(aux);
				vertices.remove(id);//no apunta a nadie
			}else { 
//				if(this.borradoConsistente(aux)){//me apuntan y yo apunto
				this.borrarArcosEntrantes(aux);
				this.borrarArcosSalientes(aux);
				vertices.remove(id);
			}
		}
	}
//	private boolean borradoConsistente(VerticeAbs<T> v) {
//		for(int i : vertices.keySet()) {
//			if(v.esAdyacente(vertices.get(i))) {//lo estoy apuntando
//				VerticeAbs<T> aux = vertices.get(i);//el apuntado
//				if(this.esApuntadoPor(aux) > 1)
//					return true;
//			}
//		}
//		return false;
//	}
//	private int esApuntadoPor(VerticeAbs<T> v) {
//		int res = 0;
//		for(int i : vertices.keySet()) {
//			if(vertices.get(i).esAdyacente(v))
//				res++;
//		}		
//		return res;
//	}
	private void borrarArcosSalientes(VerticeAbs<T> v) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for(int i: arcos.keySet()) {
			if(arcos.get(i).getOrigen().equals(v))
				arr.add(i);
		}
		for(int i: arr) {
			arcos.remove(i);
		}
	}
	private void borrarArcosEntrantes(VerticeAbs<T> v) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for(int i: arcos.keySet()) {
			if(arcos.get(i).getDestino().equals(v))
				arr.add(i);
		}
		for(int i: arr) {
			arcos.remove(i);
		}
	}
//	Complejidad temporal: O(1) donde 1 representa la atomicidad para consultar e insertar en un hashMap, sumando los mensajes entre los objetos
	@Override
	public void borrarArco(int v1, int v2) {
		if(contieneVertice(v1) && contieneVertice(v2)) {
			VerticeAbs<T> ver1 = vertices.get(v1);
			VerticeAbs<T> ver2 = vertices.get(v2);
			if(ver1.esAdyacente(ver2)) {
//				if(ver2.getTotalAdyacentes() == 0) {//no apunta a ningun vertice, es un endpoint
					arcos.remove(this.getIdArcoByVert(ver1, ver2));
//					this.borrarVertice(v2);//elimino el endpoint porque nadie lo apunta
//				}else {//no es un endpoint
//					if(this.tieneArcosEntrantes(v2)) {//existen otras maneras de llegar al vertice
//						arcos.remove(this.getIdArcoByVert(ver1, ver2));
//					}
//				}//sino no hago nada para evitar que quede un vertice que no pueda ser visitado
			}
		}
	}
	private int getIdArcoByVert(VerticeAbs<T> v1, VerticeAbs<T> v2) {
		for(int i : arcos.keySet()) {
			if(arcos.get(i).getOrigen().equals(v1) && arcos.get(i).getDestino().equals(v2))
				return i;
		}
		return 0;
	}
	private boolean tieneArcosEntrantes(int v) {
		VerticeAbs<T> aux = vertices.get(v);
		for(int i : vertices.keySet()) {
			VerticeAbs<T> ver = vertices.get(i);
			if(ver.esAdyacente(aux))
				return true;
		}
		return false;
	}
//	Complejidad temporal: O(1) donde 1 representa la atomicidad para consultar en un hashMap
	@Override
	public boolean contieneVertice(int verticeId) {
		return vertices.containsKey(verticeId);
	}
//	Complejidad temporal: O(1) donde 1 representa la atomicidad para consultar en un hashMap, sumando los mensajes entre los objetos
	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {
		boolean res = false;
		if(contieneVertice(verticeId1) && contieneVertice(verticeId2)) {
			VerticeAbs<T> ver1 = vertices.get(verticeId1);
			VerticeAbs<T> ver2 = vertices.get(verticeId2);
			res = ver1.esAdyacente(ver2);
		}
		return res;
	}
//	Complejidad temporal: O(1) donde 1 representa la atomicidad para consultar en un hashMap, sumando el tiempo entre los mensajes de los objetos
	@Override
	public Arco<T> getArcoByVertices(int v1, int v2){
		if(this.contieneVertice(v1) && this.contieneVertice(v2)) {
			VerticeAbs<T> ver1 = vertices.get(v1);
			VerticeAbs<T> ver2 = vertices.get(v2);
			if(ver1.esAdyacente(ver2)) {
				for(int i : arcos.keySet()) {
					Arco<T> arc = arcos.get(i);
					if(arc.getOrigen().equals(ver1) && arc.getDestino().equals(ver2))
						return arcos.get(i);
				}
			}
		}
		return null;
	}
	public Arco<T> getArcoByVertices(VerticeAbs<T> v1, VerticeAbs<T> v2){
		for(int i : arcos.keySet()) {
			if(arcos.get(i).getOrigen().equals(v1) && arcos.get(i).getDestino().equals(v2))
				return arcos.get(i);
		}
		return null;
	}
	public Arco<T> getArcoById(int idArco){
		if(arcos.containsKey(idArco))
			return arcos.get(idArco);
		return null;
	}
	public int getTotalAdyacentesDeUnVert(int v) {
		int res = 0;
		if(this.contieneVertice(v)) {
			res = vertices.get(v).getTotalAdyacentes();
		}
		return res;
	}
	public int getIdDeUnVertice(VerticeAbs<T> v) {
		for(int i : vertices.keySet()) {
			if(vertices.get(i).equals(v))
				return i;
		}
		return -1;
	}
//	Complejidad temporal: O(1) donde 1 representa la atomicidad para retornar un size
	@Override
	public int cantidadVertices() {
		return vertices.size();
	}
//	Complejidad temporal: O(1) donde 1 representa la atomicidad para retornar un size
	@Override
	public int cantidadArcos() {
		return arcos.size();
	}
	private ArrayList<Arco<T>> getArcosByVert(int id){
		ArrayList<Arco<T>> res = new ArrayList<Arco<T>>();
		if(vertices.containsKey(id)) {
			VerticeAbs<T> vertice = vertices.get(id);
			for(VerticeAbs<T> elem : vertice.getAdyacentes())
				res.add(this.getArcoByVertices(vertice, elem));
		}
		return res;
	}
//	Complejidad temporal: O(n) donde n representa la cantidad de elementos a copiar en la coleccion retornable
	@Override
	public VerticeIterator<T> obtenerVertices() {
		ArrayList<VerticeAbs<T>> res = new ArrayList<VerticeAbs<T>>();
		for(int i : vertices.keySet())
			res.add(vertices.get(i));
		return new VerticeIterator<T>(res);
	}
//	Complejidad temporal: O(n) donde n representa la cantidad de elementos a copiar en la coleccion retornable
	@Override
	public VerticeIterator<T> obtenerAdyacentes(VerticeAbs<T> v){
		return new VerticeIterator<T>(v.getAdyacentes());
	}
//	Complejidad temporal: O(n) donde n representa la cantidad de elementos a copiar en la coleccion retornable
	@Override
	public ArcoIterator<T> obtenerArcos() {
		ArrayList<Arco<T>> res = new ArrayList<Arco<T>>();
		for(int index : arcos.keySet())
			res.add(arcos.get(index));
		return new ArcoIterator<T>(res);
	}
//	Complejidad temporal: O(n) donde n representa la cantidad de elementos a copiar en la coleccion retornable
	@Override
	public ArcoIterator<T> obtenerArcos(int verticeId) {
		return new ArcoIterator<T>(this.getArcosByVert(verticeId));
	}
}
