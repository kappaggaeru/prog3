package entregable2;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Tree tree = new Tree(20);
		tree.add(10);
		tree.add(40);
		tree.add(6);
		tree.add(15);
		tree.add(4);
		tree.add(8);
		tree.add(12);
		tree.add(17);
		tree.add(30);
		tree.add(50);
		tree.add(25);
		tree.add(35);
		tree.add(45);
		tree.add(60);
		tree.add(2);
		
		System.out.println("hasElem(4): "+tree.hasElem(4));
		System.out.println("hasElem(15): "+tree.hasElem(15));
		System.out.println("getValue( ): "+tree.getValue());
		System.out.println("getHeight( ): "+tree.getHeight());
		System.out.println("Longest branch:");
		printList(tree.getLongestBranch());
		System.out.println("getFrontera( ):");
		printList(tree.getFrontera());
		System.out.println("getMaxElem( ): "+tree.getMaxElem());
		System.out.println("getElemAtLevel(): ");
		printList(tree.getElemAtLevel(2,1));
		System.out.println("printPreOrder( ):");
		tree.printPreOrder();
//		8, 5, 3, 4, 10, 9 salida
		System.out.println("\nprintInOrder( ):");
		tree.printInOrder();
//		3, 4, 5, 8, 9, 10 
		System.out.println("\nprintPosOrder( ):");
		tree.printPosOrder();
//		4, 3, 5, 9, 10, 8
		System.out.println("\n");
		int c = 40;
		System.out.println("delete("+c+"): "+tree.delete(c));
		System.out.println("hasElem("+c+"): "+tree.hasElem(c));
		System.out.println("\nprintPosOrder( ):");
		tree.printPosOrder();
		
		int n = (int)(Math.random()*40)+1;
		Tree treeRandom = new Tree(n);
//		ya tiene un valor
		System.out.println();
		generateNums(treeRandom,14,1,40);
		System.out.println("\nprintPreOrder()");
		treeRandom.printPreOrder();
	}
	public static void printList(ArrayList<Integer> list) {
		for(int elem: list)
			System.out.print(elem + " ");
		System.out.println();
	}
//	Una función que permita generar un árbol al azar de 15 nodos con valores no
//	repetidos entre 1 y 40.
	public static void generateNums(Tree tree,int nodes,int min,int max) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		System.out.println("numero al azar");
		System.out.print(tree.getValue() + " ");
		int i = 0;
		while(i < nodes) {
			int n = (int)((Math.random()*max)+min);
			if(!arr.contains(n)) {
				arr.add(n);
				i++;
			}
		}
		for(int item: arr) {
			System.out.print(item + " ");
			tree.add(item);
		}
		System.out.println();
	}
}
