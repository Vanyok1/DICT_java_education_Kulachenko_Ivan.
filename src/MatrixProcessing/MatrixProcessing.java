package MatrixProcessing;

import java.util.Scanner;

public class MatrixProcessing {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n1 = sc.nextInt();
        int m1 = sc.nextInt();
        double[][] dataA = new double[n1][m1];
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < m1; j++) {
                dataA[i][j] = sc.nextDouble();
            }
        }
        Matrix A = new Matrix(dataA);

        int n2 = sc.nextInt();
        int m2 = sc.nextInt();
        double[][] dataB = new double[n2][m2];
        for (int i = 0; i < n2; i++) {
            for (int j = 0; j < m2; j++) {
                dataB[i][j] = sc.nextDouble();
            }
        }
        Matrix B = new Matrix(dataB);

        Matrix sum = A.add(B);
        if (sum == null) {
            System.out.println("ERROR");
        } else {
            sum.print();
        }
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

    public Matrix add(Matrix other) {
        if (this.rows != other.rows || this.cols != other.cols) {
            return null;
        }
        double[][] result = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = this.data[i][j] + other.data[i][j];
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