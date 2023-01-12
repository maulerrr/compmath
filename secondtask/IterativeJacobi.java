package CompMath.secondtask;

import java.util.Arrays;
import java.util.Scanner;

public class IterativeJacobi {

    public static void main(String[] args) {
        int n = 3;

        // Input the matrix A and vector b
        double[][] A = {
                { 5,-1, 1 },
                { 2, 4, 0 },
                { 1, 1 ,5 }
        } ;
        double[] b = {
                10, 12, -1
        };

//        5x – y + z = 10;
//        2x + 4y = 12;
//        x + y + 5z = –1;
//        starting with the solution (2, 3, 0).


        // Initial approximation
        double[] x = new double[]{ 2, 3, 0 };
        Arrays.fill(x, 1);

        // Tolerance
        double tol = 0.0001;

        // Number of iterations
        int maxIter = 100;

        // Implement the Jacobi method
        for (int iter = 0; iter < maxIter; iter++) {
            double[] xPrev = x.clone();
            for (int i = 0; i < n; i++) {
                double sigma = 0;
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        sigma += A[i][j] * xPrev[j];
                    }
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
