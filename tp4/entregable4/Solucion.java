package entregable4;

public class Solucion{
	private int bonos;
	private int familias;
	private int diasCompletos;
	private int diasIncompletos;
	public Solucion() {
		this.bonos = 0;
		this.familias = 0;
		this.diasCompletos = 0;
		this.diasIncompletos = 0;
	}
	public int getBonos() {
		return bonos;
	}
	public int getFamilias() {
		return familias;
	}
	public int getDiasCompletos() {
		return diasCompletos;
	}
	public int getDiasIncompletos() {
		return diasIncompletos;
	}
	public void setBonos(int bonos) {
		this.bonos += bonos;
	}
	public void setFamilias(int familias) {
		this.familias += familias;
	}
	public void setDiasCompletos(int diasCompletos) {
		this.diasCompletos += diasCompletos;
	}
	public void setDiasIncompletos(int diasIncompletos) {
		this.diasIncompletos += diasIncompletos;
	}
}
