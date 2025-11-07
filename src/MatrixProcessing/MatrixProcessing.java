package MatrixProcessing;

import java.util.Locale;
import java.util.Scanner;

public class MatrixProcessing {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.useLocale(Locale.US);

        while (true) {
            System.out.println("1. Add matrices");
            System.out.println("2. Multiply matrix by a constant");
            System.out.println("3. Multiply matrices");
            System.out.println("4. Transpose matrix");
            System.out.println("5. Calculate a determinant");
            System.out.println("6. Inverse matrix");
            System.out.println("0. Exit");
            System.out.print("Your choice: ");

            int choice = sc.nextInt();
            if (choice == 0) break;

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter size of first matrix: ");
                    int n1 = sc.nextInt(), m1 = sc.nextInt();
                    System.out.println("Enter first matrix:");
                    Matrix A = new Matrix(readMatrix(sc, n1, m1));

                    System.out.print("Enter size of second matrix: ");
                    int n2 = sc.nextInt(), m2 = sc.nextInt();
                    System.out.println("Enter second matrix:");
                    Matrix B = new Matrix(readMatrix(sc, n2, m2));

                    Matrix sum = A.add(B);
                    System.out.println("The result is:");
                    if (sum == null) System.out.println("The operation cannot be performed.");
                    else sum.print();
                }

                case 2 -> {
                    System.out.print("Enter size of matrix: ");
                    int n = sc.nextInt(), m = sc.nextInt();
                    System.out.println("Enter matrix:");
                    Matrix M = new Matrix(readMatrix(sc, n, m));

                    System.out.print("Enter constant: ");
                    double c = sc.nextDouble();

                    Matrix res = M.multiplyByConstant(c);
                    System.out.println("The result is:");
                    res.print();
                }

                case 3 -> {
                    System.out.print("Enter size of first matrix: ");
                    int n1 = sc.nextInt(), m1 = sc.nextInt();
                    System.out.println("Enter first matrix:");
                    Matrix A = new Matrix(readMatrix(sc, n1, m1));

                    System.out.print("Enter size of second matrix: ");
                    int n2 = sc.nextInt(), m2 = sc.nextInt();
                    System.out.println("Enter second matrix:");
                    Matrix B = new Matrix(readMatrix(sc, n2, m2));

                    Matrix prod = A.multiply(B);
                    System.out.println("The result is:");
                    if (prod == null) System.out.println("The operation cannot be performed.");
                    else prod.print();
                }

                case 4 -> {
                    System.out.println("1. Main diagonal");
                    System.out.println("2. Side diagonal");
                    System.out.println("3. Vertical line");
                    System.out.println("4. Horizontal line");
                    System.out.print("Your choice: ");
                    int t = sc.nextInt();

                    System.out.print("Enter matrix size: ");
                    int n = sc.nextInt(), m = sc.nextInt();
                    System.out.println("Enter matrix:");
                    Matrix M = new Matrix(readMatrix(sc, n, m));

                    Matrix tr = switch (t) {
                        case 1 -> M.transposeMainDiagonal();
                        case 2 -> M.transposeSideDiagonal();
                        case 3 -> M.transposeVertical();
                        case 4 -> M.transposeHorizontal();
                        default -> null;
                    };

                    if (tr == null) System.out.println("Invalid option.");
                    else {
                        System.out.println("The result is:");
                        tr.print();
                    }
                }

                case 5 -> {
                    System.out.print("Enter matrix size: ");
                    int n = sc.nextInt(), m = sc.nextInt();
                    System.out.println("Enter matrix:");
                    Matrix M = new Matrix(readMatrix(sc, n, m));

                    if (n != m) {
                        System.out.println("The operation cannot be performed.");
                    } else {
                        double det = M.determinant();
                        System.out.println("The result is:");
                        if (det == Math.floor(det)) System.out.println((int) det);
                        else System.out.println(det);
                    }
                }

                case 6 -> {
                    System.out.print("Enter matrix size: ");
                    int n = sc.nextInt(), m = sc.nextInt();
                    System.out.println("Enter matrix:");
                    Matrix M = new Matrix(readMatrix(sc, n, m));

                    if (n != m) {
                        System.out.println("The operation cannot be performed.");
                    } else {
                        double det = M.determinant();
                        if (det == 0) {
                            System.out.println("This matrix doesn't have an inverse.");
                        } else {
                            Matrix inv = M.inverse();
                            System.out.println("The result is:");
                            inv.print();
                        }
                    }
                }

                default -> System.out.println("Invalid option. Try again.");
            }
            System.out.println();
        }
    }

    private static double[][] readMatrix(Scanner sc, int n, int m) {
        double[][] d = new double[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                d[i][j] = sc.nextDouble();
        return d;
    }
}

class Matrix {
    private final double[][] data;
    private final int rows, cols;

    public Matrix(double[][] d) {
        rows = d.length;
        cols = d[0].length;
        data = new double[rows][cols];
        for (int i = 0; i < rows; i++)
            System.arraycopy(d[i], 0, data[i], 0, cols);
    }

    public Matrix add(Matrix o) {
        if (rows != o.rows || cols != o.cols) return null;
        double[][] r = new double[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                r[i][j] = data[i][j] + o.data[i][j];
        return new Matrix(r);
    }

    public Matrix multiplyByConstant(double c) {
        double[][] r = new double[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                r[i][j] = data[i][j] * c;
        return new Matrix(r);
    }

    public Matrix multiply(Matrix o) {
        if (cols != o.rows) return null;
        double[][] r = new double[rows][o.cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < o.cols; j++) {
                double s = 0;
                for (int k = 0; k < cols; k++)
                    s += data[i][k] * o.data[k][j];
                r[i][j] = s;
            }
        return new Matrix(r);
    }

    public Matrix transposeMainDiagonal() {
        double[][] r = new double[cols][rows];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                r[j][i] = data[i][j];
        return new Matrix(r);
    }

    public Matrix transposeSideDiagonal() {
        double[][] r = new double[cols][rows];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                r[cols - 1 - j][rows - 1 - i] = data[i][j];
        return new Matrix(r);
    }

    public Matrix transposeVertical() {
        double[][] r = new double[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                r[i][cols - 1 - j] = data[i][j];
        return new Matrix(r);
    }

    public Matrix transposeHorizontal() {
        double[][] r = new double[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                r[rows - 1 - i][j] = data[i][j];
        return new Matrix(r);
    }

    public double determinant() {
        if (rows != cols) throw new IllegalStateException("Not a square matrix");
        if (rows == 1) return data[0][0];
        if (rows == 2) return data[0][0] * data[1][1] - data[0][1] * data[1][0];
        double det = 0;
        for (int j = 0; j < cols; j++) {
            det += Math.pow(-1, j) * data[0][j] * minor(0, j).determinant();
        }
        return det;
    }

    private Matrix minor(int row, int col) {
        double[][] r = new double[rows - 1][cols - 1];
        int rr = 0;
        for (int i = 0; i < rows; i++) {
            if (i == row) continue;
            int cc = 0;
            for (int j = 0; j < cols; j++) {
                if (j == col) continue;
                r[rr][cc++] = data[i][j];
            }
            rr++;
        }
        return new Matrix(r);
    }

    public Matrix inverse() {
        double det = determinant();
        if (det == 0) return null;

        double[][] cof = new double[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                cof[i][j] = Math.pow(-1, i + j) * minor(i, j).determinant();

        Matrix adj = new Matrix(cof).transposeMainDiagonal();
        return adj.multiplyByConstant(1.0 / det);
    }

    public void print() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double val = data[i][j];
                val = Math.round(val * 100.0) / 100.0;
                if (val == Math.floor(val)) System.out.print((int) val);
                else System.out.print(val);
                if (j < cols - 1) System.out.print(" ");
            }
            System.out.println();
        }
    }
}