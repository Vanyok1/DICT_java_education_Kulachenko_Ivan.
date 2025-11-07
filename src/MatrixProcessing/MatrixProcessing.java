package MatrixProcessing;

import java.util.Scanner;

public class MatrixProcessing {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        double[][] data = new double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                data[i][j] = sc.nextDouble();
            }
        }
        Matrix matrix = new Matrix(data);

        double constant = sc.nextDouble();

        Matrix result = matrix.multiplyByConstant(constant);
        result.print();
    }
}

class Matrix {
    private double[][] data;
    private int rows;
    private int cols;

    public Matrix(double[][] data) {
        this.rows = data.length;
        this.cols = data[0].length;
        this.data = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(data[i], 0, this.data[i], 0, cols);
        }
    }

    public Matrix multiplyByConstant(double c) {
        double[][] result = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = this.data[i][j] * c;
            }
        }
        return new Matrix(result);
    }

    public void print() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (data[i][j] == Math.floor(data[i][j])) {
                    System.out.print((int) data[i][j]);
                } else {
                    System.out.print(data[i][j]);
                }
                if (j < cols - 1) System.out.print(" ");
            }
            System.out.println();
        }
    }
}