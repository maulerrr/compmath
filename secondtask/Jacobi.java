package CompMath.secondtask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Jacobi {

    public static final int MAX_ITERATIONS = 100;
    private double[][] Matrix = {
            { 5,-1, 1 },
            { 2, 4, 0 },
            { 1, 1 ,5 }
    } ;

    public Jacobi() {}

    public void print() {
        int n = Matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n + 1; j++)
                System.out.print(Matrix[i][j] + " ");
            System.out.println();
        }
    }

    public boolean transformToDominant(int r, boolean[] V, int[] R) {
        int n = Matrix.length;

        if (r == Matrix.length) {
            double[][] T = new double[n][n+1];
            for (int i = 0; i < R.length; i++) {
                System.arraycopy(Matrix[R[i]], 0, T[i], 0, n + 1);
            }

            Matrix = T;

            return true;
        }

        for (int i = 0; i < n; i++) {
            if (V[i]) continue;

            double sum = 0;

            for (int j = 0; j < n; j++)
                sum += Math.abs(Matrix[i][j]);

            if (2 * Math.abs(Matrix[i][r]) > sum) { // diagonally dominant?
                V[i] = true;
                R[r] = i;

                if (transformToDominant(r + 1, V, R))
                    return true;

                V[i] = false;
            }
        }

        return false;
    }


    /**
     * Returns true if is possible to transform M(data member) to a diagonally
     * dominant matrix, false otherwise.
     */
    public boolean makeDominant() {
        boolean[] visited = new boolean[Matrix.length];
        int[] rows = new int[Matrix.length];

        Arrays.fill(visited, false);

        return transformToDominant(0, visited, rows);
    }

    public void solve() {
        int iterations = 0;
        int n = Matrix.length;
        double epsilon = 1e-15;
        double[] X = new double[n]; // Approximations
        double[] P = new double[n]; // Prev
        Arrays.fill(X, 0);
        Arrays.fill(P, 0);

        while (true) {
            for (int i = 0; i < n; i++) {
                double sum = Matrix[i][n]; // b_n

                for (int j = 0; j < n; j++)
                    if (j != i)
                        sum -= Matrix[i][j] * P[j];

                // Update x_i but it's no used in the next row calculation
                // but up to de next iteration of the method
                X[i] = 1/ Matrix[i][i] * sum;
            }

            System.out.print("X_" + iterations + " = {");
            for (int i = 0; i < n; i++)
                System.out.print(X[i] + " ");
            System.out.println("}");

            iterations++;
            if (iterations == 1) continue;

            boolean stop = true;
            for (int i = 0; i < n && stop; i++)
                if (Math.abs(X[i] - P[i]) > epsilon)
                    stop = false;

            if (stop || iterations == MAX_ITERATIONS) break;
            P = (double[])X.clone();
        }
    }

    public static void main(String[] args) throws IOException
    {
        int n = 3;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out, true);

        Jacobi jacobi = new Jacobi();

        if (!jacobi.makeDominant()) {
            writer.println("The system isn't diagonally dominant: " +
                    "The method cannot guarantee convergence.");
        }

        writer.println();
        jacobi.print();
        jacobi.solve();
    }
}
