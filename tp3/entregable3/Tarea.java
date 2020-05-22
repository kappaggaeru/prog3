package entregable3;
public class Tarea<T> extends VerticeAbs<T>{
	private String nombre;
	private String descripcion;
	
	public Tarea(String n, String d,T horas) {
		super(horas);
		this.nombre = n;
		this.descripcion = d;
	}
	public Tarea(T horas) {
		super(horas);
	}
	public String getNombre() {
		return nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public Object getHoras() {
		return super.getInfo();
	}
	public void setDescripcion(String s) {
		this.descripcion = s;
	}
}