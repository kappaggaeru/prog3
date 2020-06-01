package entregable4;
import java.util.Comparator;
public class SortMasCaro implements Comparator<Familia>{
	@Override
	public int compare(Familia f1, Familia f2) {
		return f1.bonoEnPeorDia() - f2.bonoEnPeorDia();
	}

}
