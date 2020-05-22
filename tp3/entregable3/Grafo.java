package entregable3;

public interface Grafo<T> {
	
	// Agrega un vertice 
	public void agregarVertice(T infoVertice);

	// Borra un vertice
	public void borrarVertice(int verticeId);

	// Agrega un arco con una etiqueta, que conecta el verticeId1 con el verticeId2
	public void agregarArco(int vId1, int vId2, T info);

	// Borra el arco que conecta el verticeId1 con el verticeId2
	public void borrarArco(int verticeId1, int verticeId2);

	// Verifica si existe un vertice
	public boolean contieneVertice(int verticeId);  

	// Verifica si existe un arco entre dos vertices
	public boolean existeArco(int verticeId1, int verticeId2);
	
	// Obtener el arco que conecta el verticeId1 con el verticeId2
	public Arco<T> getArcoByVertices(int verticeId1, int verticeId2);
	
	// Devuelve la cantidad total de vertices en el grafo
	public int cantidadVertices();

	// Devuelve la cantidad total de arcos en el grafo
	public int cantidadArcos();

	// Obtiene un iterador que me permite recorrer todos los vertices almacenados en el grafo 
	public VerticeIterator<T> obtenerVertices();

	// Obtiene un iterador que me permite recorrer todos los vertices adyacentes a verticeId 
	public VerticeIterator<T> obtenerAdyacentes(VerticeAbs<T> v);

	// Obtiene un iterador que me permite recorrer todos los arcos del grafo
	public ArcoIterator<T> obtenerArcos();
		
	// Obtiene un iterador que me permite recorrer todos los arcos que parten desde verticeId
	public ArcoIterator<T> obtenerArcos(int verticeId);

}
