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
            System.out.println("0. Exit");
            System.out.print("Your choice: ");

            int choice = sc.nextInt();
            if (choice == 0) break;

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter size of first matrix: ");
                    int n1 = sc.nextInt();
                    int m1 = sc.nextInt();
                    System.out.println("Enter first matrix:");
                    double[][] dataA = readMatrix(sc, n1, m1);
                    Matrix A = new Matrix(dataA);

                    System.out.print("Enter size of second matrix: ");
                    int n2 = sc.nextInt();
                    int m2 = sc.nextInt();
                    System.out.println("Enter second matrix:");
                    double[][] dataB = readMatrix(sc, n2, m2);
                    Matrix B = new Matrix(dataB);

                    Matrix sum = A.add(B);
                    System.out.println("The result is:");
                    if (sum == null) {
                        System.out.println("The operation cannot be performed.");
                    } else {
                        sum.print();
                    }
                }

                case 2 -> {
                    System.out.print("Enter size of matrix: ");
                    int n = sc.nextInt();
                    int m = sc.nextInt();
                    System.out.println("Enter matrix:");
                    double[][] data = readMatrix(sc, n, m);
                    Matrix M = new Matrix(data);

                    System.out.print("Enter constant: ");
                    double constant = sc.nextDouble();

                    Matrix result = M.multiplyByConstant(constant);
                    System.out.println("The result is:");
                    result.print();
                }

                case 3 -> {
                    System.out.print("Enter size of first matrix: ");
                    int n1 = sc.nextInt();
                    int m1 = sc.nextInt();
                    System.out.println("Enter first matrix:");
                    double[][] dataA = readMatrix(sc, n1, m1);
                    Matrix A = new Matrix(dataA);

                    System.out.print("Enter size of second matrix: ");
                    int n2 = sc.nextInt();
                    int m2 = sc.nextInt();
                    System.out.println("Enter second matrix:");
                    double[][] dataB = readMatrix(sc, n2, m2);
                    Matrix B = new Matrix(dataB);

                    Matrix product = A.multiply(B);
                    System.out.println("The result is:");
                    if (product == null) {
                        System.out.println("The operation cannot be performed.");
                    } else {
                        product.print();
                    }
                }

                case 4 -> {
                    System.out.println("1. Main diagonal");
                    System.out.println("2. Side diagonal");
                    System.out.println("3. Vertical line");
                    System.out.println("4. Horizontal line");
                    System.out.print("Your choice: ");
                    int type = sc.nextInt();

                    System.out.print("Enter matrix size: ");
                    int n = sc.nextInt();
                    int m = sc.nextInt();
                    System.out.println("Enter matrix:");
                    double[][] data = readMatrix(sc, n, m);
                    Matrix M = new Matrix(data);

                    Matrix transposed = switch (type) {
                        case 1 -> M.transposeMainDiagonal();
                        case 2 -> M.transposeSideDiagonal();
                        case 3 -> M.transposeVertical();
                        case 4 -> M.transposeHorizontal();
                        default -> null;
                    };

                    if (transposed == null) {
                        System.out.println("Invalid option.");
                    } else {
                        System.out.println("The result is:");
                        transposed.print();
                    }
                }

                default -> System.out.println("Invalid option. Try again.");
            }
            System.out.println();
        }
    }

    private static double[][] readMatrix(Scanner sc, int n, int m) {
        double[][] data = new double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                data[i][j] = sc.nextDouble();
            }
        }
        return data;
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
        if (this.rows != other.rows || this.cols != other.cols) return null;
        double[][] result = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = this.data[i][j] + other.data[i][j];
            }
        }
        return new Matrix(result);
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

    public Matrix multiply(Matrix other) {
        if (this.cols != other.rows) return null;
        double[][] result = new double[this.rows][other.cols];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < other.cols; j++) {
                double sum = 0;
                for (int k = 0; k < this.cols; k++) {
                    sum += this.data[i][k] * other.data[k][j];
                }
                result[i][j] = sum;
            }
        }
        return new Matrix(result);
    }

    public Matrix transposeMainDiagonal() {
        double[][] result = new double[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = data[i][j];
            }
        }
        return new Matrix(result);
    }

    public Matrix transposeSideDiagonal() {
        double[][] result = new double[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[cols - 1 - j][rows - 1 - i] = data[i][j];
            }
        }
        return new Matrix(result);
    }

    public Matrix transposeVertical() {
        double[][] result = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][cols - 1 - j] = data[i][j];
            }
        }
        return new Matrix(result);
    }

    public Matrix transposeHorizontal() {
        double[][] result = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[rows - 1 - i][j] = data[i][j];
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