import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Matrix {
	private int[][] originalMatrix;
	private int[][] puzzleMatrix;
	private int size;
	private List<Integer> sums;
	
	public Matrix(int size) {
		originalMatrix = new int[size][size];
		puzzleMatrix = new int[size][size];
		this.size = size;
		sums = new ArrayList<>();
		populate();
		generate();
	}

	private void populate() {
		for (int x=0; x<size; x++) {
			for (int y=0; y<size; y++) {
				originalMatrix[x][y] = new Random().nextInt(9)+1;
				puzzleMatrix[x][y] = originalMatrix[x][y];
			}
		}
		//puzzleMatrix = originalMatrix.clone();
		printOriginal();
	}
	
	private void generate() {
		for (int x=0; x<size; x++) {
			for (int y=0; y<size; y++) {
				int chance = new Random().nextInt(2);
				if (chance == 1) 
					puzzleMatrix[x][y] = 0;
			}
		}
		printGenerated();
	}
	
	public int[][] getMatrix() {
		return originalMatrix;
	}
	
	public void printOriginal() {
		for (int x=0; x<size; x++) {
			for (int y=0; y<size; y++) {
				System.out.print(originalMatrix[x][y]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public void printGenerated() {
		for (int x=0; x<size; x++) {
			for (int y=0; y<size; y++) {
				System.out.print(puzzleMatrix[x][y]+" ");
			}
			System.out.println();
		}
	}
}
