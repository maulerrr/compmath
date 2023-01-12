package CompMath.secondtask;

public class GaussJordan {
    static int M = 10;

    // Function to print the matrix
    static void PrintMatrix(float a[][], int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n; j++)
                System.out.print(" [" + a[i][j] + "] ");
            System.out.println();
        }
    }

    // function to reduce matrix to reduced
    // row echelon form.
    static int PerformOperation(float a[][], int n) {
        int i, j, k = 0, c, flag = 0, m = 0;
        float pro = 0;

        // Performing elementary operations
        for (i = 0; i < n; i++) {
            if (a[i][i] == 0) {
                c = 1;
                while ((i + c) < n && a[i + c][i] == 0)
                    c++;
                if ((i + c) == n) {
                    flag = 1;
                    break;
                }
                for (j = i, k = 0; k <= n; k++) {
                    float temp =a[j][k];
                    a[j][k] = a[j+c][k];
                    a[j+c][k] = temp;
                }
            }

            for (j = 0; j < n; j++) {
                // Excluding all i == j
                if (i != j) {

                    // Converting Matrix to reduced row
                    // echelon form(diagonal matrix)
                    float p = a[j][i] / a[i][i];

                    for (k = 0; k <= n; k++)
                        a[j][k] = a[j][k] - (a[i][k]) * p;
                }
            }
        }
        return flag;
    }

    // Function to print the desired result
    // if unique solutions exists, otherwise
    // prints no solution or infinite solutions
    // depending upon the input given.
    static void PrintResult(float a[][], int n, int flag) {
        System.out.print("Result is : ");

        if (flag == 2)
            System.out.println("Infinite Solutions Exists");
        else if (flag == 3)
            System.out.println("No Solution Exists");

            // Printing the solution by dividing constants by
            // their respective diagonal elements
        else {
            for (int i = 0; i < n; i++)
                System.out.print(" | " + a[i][n] / a[i][i] +" | ");
        }
    }

    // To check whether infinite solutions
    // exists or no solution exists
    static int CheckConsistency(float a[][], int n, int flag) {
        int i, j;
        float sum;

        // flag == 2 for infinite solution
        // flag == 3 for No solution
        flag = 3;
        for (i = 0; i < n; i++) {
            sum = 0;
            for (j = 0; j < n; j++)
                sum = sum + a[i][j];
            if (sum == a[i][j])
                flag = 2;
        }
        return flag;
    }

    // Driver code
    public static void main(String[] args) {
        float a[][] = {
                { 2, 1,  5, 1, 5 },
                { 1, 1, -3, 4,-1 },
                { 3, 6, -2, 1, 8 },
                { 2, 2, 2, -3, 2 }
        };

//        (i) 2x + 6y + 7z = 52;
    //        2x + y – z = 0;
    //        x + y + z = 9
//        (ii) 2x1 + x2 + 5x3 + x4 = 5;
    //        x1 + x2 – 3x3 + 4x4 = –1;
    //        3x1 + 6x2 – 2x3 + x4 = 8;
    //        2x1+ 2x2 + 2x3 – 3x4 = 2

        // Order of Matrix(n)
        int n = 4, flag = 0;

        // Performing Matrix transformation
        flag = PerformOperation(a, n);

        if (flag == 1)
            flag = CheckConsistency(a, n, flag);

        // Printing Final Matrix
        System.out.println("Final Augmented Matrix is : ");
        PrintMatrix(a, n);
        System.out.println("");

        // Printing Solutions(if exist)
        PrintResult(a, n, flag);
    }
}

