package entregable5;
import java.util.Comparator;
public class SortMiembros implements Comparator<Familia>{

	@Override
	public int compare(Familia f0, Familia f1) {
		return f0.miembros() - f1.miembros();
	}

}
