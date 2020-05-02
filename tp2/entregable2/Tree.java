package entregable2;
import java.util.ArrayList;

public class Tree {
	protected int value;
	protected Tree left;
	protected Tree right;
	
	public Tree(int value) {
		this.value = value;
		this.left = null;
		this.right = null;
	}
//	complejidad: O(1) porque es una consulta atomica de pedir el valor de un atributo
	public int getValue() {
		return this.value;
	}
//	complejidad: O(n) depende de la altura del arbol, en el peor caso llega hasta la frontera
	public boolean hasElem(int n) {
		boolean res = false;
		//si no tiene hijos y no es el, termina
		if(this.left == null && this.right == null) {
			if(n == this.value) {
				return true;
			}else {
				return false;
			}
		}
		if(n < this.value) {
			if(this.left != null) {
				res = this.left.hasElem(n);
			}
		}else if(n > this.value) {
			if(this.right != null) {
				res = this.right.hasElem(n);
			}
		}
		return res;
	}
//	Complejidad: O(n) depende de la altura del arbol, en el peor caso inserta al final
	public void add(int n) {
		if(n < this.value) {//menor al lado izq
			if(this.left == null) 
				this.left = new Tree(n);
			else
				this.left.add(n);
		}else {
			if(this.right == null)
				this.right = new Tree(n);
			else
				this.right.add(n);
		}
	}
//	Complejidad: O(n) depende de la altura del arbol, en el peor caso llega hasta la frontera
	private int getNMI() {
		int res ;
		if(this.left == null) {
			res = this.getValue();
		}else {
			res = this.left.getNMI();
		}
		return res;
	}
//	Complejidad: O(n) depende de la altura del arbol, en el peor caso llega hasta la frontera
	public boolean delete(int n) {
//		trate de utilizar una variable como puntero pero no funcionaba
//		ej Tree Puntero = this.left;
		boolean res = false;
		if(n < this.value) {
			if(this.left.getValue() == n) {
				if(this.left.left == null & this.left.right == null) {
					this.left = null;
					res = true;
				}else if(this.left.left == null) {
					this.left = this.left.right;
					res = true;
				}else if(this.left.right == null) {
					this.left = this.left.left;
					res = true;
				}else {
					int nim = this.left.right.getNMI();
//					System.out.println("nim:"+nim);
					this.left.right.delete(nim);
					Tree tmp = new Tree(nim);
					tmp.left = this.left.left;
					tmp.right = this.left.right;
					this.left = tmp;
					res = true;
				}
			}else {
				res = this.left.delete(n);
			}
		}else if(n > this.value){
//			
			if(this.right.getValue() == n) {
				if(this.right.left == null & this.right.right == null) {
					this.right = null;
					res = true;
				}else if(this.right.left == null) {
					this.right = this.right.right;
					res = true;
				}else if(this.right.right == null) {
					this.right = this.right.left;
					res = true;
				}else {
					int nim = this.right.right.getNMI();
//					System.out.println("nim:"+nim);
					this.right.right.delete(nim);
					Tree tmp = new Tree(nim);
					tmp.left = this.right.left;
					tmp.right = this.right.right;
					this.right = tmp;
					res = true;
				}
			}else {
				res = this.right.delete(n);
			}
		}else {
			int nim = this.right.getNMI();
//			System.out.println("nim:"+nim);
			this.right.delete(nim);
			this.value = nim;
			res = true;
		}
		return res;
	}
//	Complejidad: O(n) depende de la altura del arbol, literal xD
	public int getHeight() {
		int res = 0;
		int izq = 0;
		int der = 0;
		if(this.left == null && this.right == null) {
			return 1;
		}else {
			if(this.left != null) {
				izq = this.left.getHeight();
			}
			if(this.right != null) {
				der = this.right.getHeight();
			}
			res = Math.max(izq, der) + 1;
		}
		return res;
	}
//	Complejidad: O(n) depende de la cantidad de elementos del arbol
	public void printPreOrder() {
		System.out.print(this.value + " ");
		if(this.left != null)
			this.left.printPreOrder();
		if(this.right != null)
			this.right.printPreOrder();
	}
//	Complejidad: O(n) depende de la cantidad de elementos del arbol
	public void printInOrder() {
		if(this.left != null)
			this.left.printInOrder();
		System.out.print(this.value + " ");
		if(this.right != null)
			this.right.printInOrder();		
	}
//	Complejidad: O(n) depende de la cantidad de elementos del arbol
	public void printPosOrder() {
		if(this.left != null)
			this.left.printPosOrder();
		if(this.right != null)
			this.right.printPosOrder();
		System.out.print(this.value + " ");
	}
//	Complejidad: O(n) depende de la altura del arbol, tiene que llegar hasta la frontera
	public ArrayList<Integer> getLongestBranch() {
		ArrayList<Integer> res = new ArrayList<Integer>();
		ArrayList<Integer> izq = new ArrayList<Integer>();
		ArrayList<Integer> der = new ArrayList<Integer>();
		if(this.left == null && this.right == null) {
			res.add(this.value);
		}else {
			if(this.left != null) {
				izq = this.left.getLongestBranch();
			}
			if(this.right != null) {
				der = this.right.getLongestBranch();
			}
			if(izq.size() > der.size()) {
				res.addAll(izq);
			}else {
				res.addAll(der);
			}
			res.add(this.value);
		}
		return res;
	}
//	Complejidad: O(n) depende de la altura del arbol, debe llegar hasta el final
	public ArrayList<Integer> getFrontera() {
		ArrayList<Integer> res = new ArrayList<Integer>();
//		todas las hojas
		if(this.left == null && this.right == null) {
			res.add(this.value);
		}
		if(this.left != null) {
			res.addAll(this.left.getFrontera());
		}
		if(this.right != null) {
			res.addAll(this.right.getFrontera());
		}
		return res;
	}
//	Complejidad: O(n) depende de la altura del arbol, en el peor llega al nmd
	public int getMaxElem() {
		int res = 0;//obtener el mas derecho
		if(this.right != null) {//si no tiene der es el mas derecho
			res = this.right.getMaxElem();
		}else {
			res = this.value;
		}
		return res;
	}
//	Complejidad: O(n) depende de la altura del arbol, en el peor caso llega hasta el ultimo nivel
	public ArrayList<Integer> getElemAtLevel(int level, int index) {
//		el parametro index es un auxiliar que uso, si viene como 1 los niveles los trato como 1, 2, 3, 4, etc
//		si viene como 0 los niveles 0, 1, 2, 3, etc
		ArrayList<Integer> res = new ArrayList<Integer>();
		if(index == level-1) {
			if(this.left != null)
				res.add(this.left.getValue());
			if(this.right != null)
				res.add(this.right.getValue());
		}else {
			if(this.left != null) {
				res.addAll(this.left.getElemAtLevel(level, index+1));
			}
			if(this.right != null) {
				res.addAll(this.right.getElemAtLevel(level, index+1));
			}
		}
		return res;
	}
}
