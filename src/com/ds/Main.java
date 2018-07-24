package com.ds;

import static java.lang.Math.max;

public class Main {
    static int row = 3;
    static int col = 3;

    static int matrix[][];

    public static void main(String[] args) {

        createMatrix();

        printMatrixRowWise(matrix);

        printMatrixColWise();

        rotateMatrixClockWise();

        printMaxSumPath();
    }

    private static void createMatrix() {
        matrix = new int[row][col];
        int count = 1;
        for(int i=0; i<row; i++){
            for(int j=0; j< col; j++){
                matrix[i][j] = count++;
            }
        }
    }

    private static void printMatrixRowWise(int matrix[][]) {
        System.out.println("Matrix Row Wise:: ");
        for(int i=0; i<row; i++){
            for(int j =0; j<col; j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println("\n");
        }
    }

    private static void printMatrixColWise() {
        System.out.println("Matrix Col Wise:: ");
        for(int i=0; i<col; i++){
            for(int j=0; j<row; j++){
                System.out.print(matrix[j][i]+" ");
            }
            System.out.println("\n");
        }
    }

    private static void rotateMatrixClockWise() {
        int prev, cur;
        int m = row;
        int n = col;
        int row=0,col=0;

        while (row < m && col < n) {

            if (row + 1 == m || col + 1 == n)
                break;

            prev = matrix[row + 1][col];
            for (int i = col; i < n; i++) {
                cur = matrix[row][i];
                matrix[row][i] = prev;
                prev = cur;
            }
            row++;
            for (int i = row; i < m; i++) {
                cur = matrix[i][n - 1];
                matrix[i][n - 1] = prev;
                prev = cur;
            }

            n = n - 1;
            if (row < m) {
                for (int i = n - 1; i >= col; i--) {
                    cur = matrix[m - 1][i];
                    matrix[m - 1][i] = prev;
                    prev = cur;
                }
            }

            m = m - 1;
            if (col < n) {
                for (int i = m - 1; i >= row; i--) {
                    cur = matrix[i][col];
                    matrix[i][col] = prev;
                    prev = cur;
                }
            }
        }
        printMatrixRowWise(matrix);
    }

    private static int printMaxSumPath() {
        // To find max val in first row
        int matrix1[][] = new int[row][col];
        int res = -1;
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                matrix1[i][j] = matrix[i][j];
            }
        }
        for (int i = 0; i < col; i++)
            res = max(res, matrix1[0][i]);

        for (int i = 1; i < row; i++)
        {
            res = -1;
            for (int j = 0; j < col; j++)
            {
                // When all paths are possible
                if (j > 0 && j < col - 1)
                    matrix1[i][j] += max(matrix1[i - 1][j], max(matrix1[i - 1][j - 1], matrix1[i - 1][j + 1]));

                    // When diagonal right is not possible
                else if (j > 0)
                    matrix1[i][j] += max(matrix1[i - 1][j], matrix1[i - 1][j - 1]);

                    // When diagonal left is not possible
                else if (j < col - 1)
                    matrix1[i][j] += max(matrix1[i - 1][j], matrix1[i - 1][j + 1]);

                // Store max path sum
                res = max(matrix1[i][j], res);
            }
        }
        printMatrixRowWise(matrix1);

        return res;
    }

}
