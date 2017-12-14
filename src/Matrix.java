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
		print(originalMatrix);
	}
	
	private void generate() {
		for (int x=0; x<size; x++) {
			int sumRow = 0;
			for (int y=0; y<size; y++) {
				int chance = new Random().nextInt(2);
				if (chance == 1) 
					puzzleMatrix[x][y] = 0;
				sumRow += puzzleMatrix[x][y];
			}
			sums.add(sumRow);
		}
		print(puzzleMatrix);
		sumCols();
		printSums();
	}
	
	private void sumCols() {
		for (int y=0; y<size; y++) {
			int sumCol = 0;
			for (int x=0; x<size; x++) {
				sumCol += puzzleMatrix[x][y];
			}
			sums.add(sumCol);
		}
	}
	
	private void printSums() {
		for (int x=0; x<size; x++) {
			System.out.println("Sum of row "+x+" is: "+sums.get(x));
			System.out.println("Sum of col "+x+" is: "+sums.get(x+size));
		}
	}
	
	public int[][] getMatrix() {
		return originalMatrix;
	}
	
	
	public int[][] getSolution() {
		return puzzleMatrix;
	}
	
	public List<Integer> getSums() {
		return sums;
	}
	
	public void print(int[][] matrix) {
		for (int x=0; x<size; x++) {
			for (int y=0; y<size; y++) {
				System.out.print(matrix[x][y]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
