package CompMath.secondtask;

import java.util.Arrays;
import java.util.Scanner;

public class GaussSeidelMethod {
    public static void main(String[] args) {
        int n = 3;

        // Input the matrix A and vector b
        double[][] A = {
                { 10, 1, 1 },
                { 2, 10, 1 },
                { 2, 2, 14}
        };
        double[] b = { 12, 13, 14 };

        // Initial approximation
        double[] x = new double[n];
        Arrays.fill(x, 1);

        // Tolerance
        double tol = 0.0001;

        // Number of iterations
        int maxIter = 100;

        // Implement the Gauss-Seidel method
        for (int iter = 0; iter < maxIter; iter++) {
            double[] xPrev = x.clone();
            for (int i = 0; i < n; i++) {
                double sigma = 0;
                for (int j = 0; j < i; j++) {
                    sigma += A[i][j] * x[j];
                }
                for (int j = i + 1; j < n; j++) {
                    sigma += A[i][j] * xPrev[j];
                }
                x[i] = (b[i] - sigma) / A[i][i];
            }

            // Check for convergence
            if (checkConvergence(x, xPrev, tol)) {
                System.out.println("The solution is: " + Arrays.toString(x));
                break;
            }
        }
    }

    private static boolean checkConvergence(double[] x, double[] xPrev, double tol) {
        for (int i = 0; i < x.length; i++) {
            if (Math.abs(x[i] - xPrev[i]) > tol) {
                return false;
            }
        }
        return true;
    }
}