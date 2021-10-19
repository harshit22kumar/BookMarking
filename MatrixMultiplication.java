package com.company.DSAlgo;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MatrixMultiplication {
    public static void main(String []args) {
        int size=2000;
        long startTime = System.nanoTime();
        solve(size);
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println(totalTime);
        System.out.println("=============using stream==========");
        matrixMul(size);

    }
    public static void solve(int size) {
        int [][]matrix1=new int[size][size];
        int [][]matrix2=new int[size][size];
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                matrix1[i][j]=j+1;
                matrix2[i][j]=j+1;
            }
        }
        int [][]resultMatrix=new int[size][size];
        for (int i = 0; i < resultMatrix.length; i++)
        {
            for (int j = 0; j < resultMatrix[i].length; j++)
            {
                for (int k = 0; k < size; k++)
                {
                    resultMatrix[i][j]
                            += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
//        for(int []row:resultMatrix) {
//            System.out.println ( Arrays.toString ( row ) );
//        }
    }

    public static void matrixMul(int size){
        int [][]a=new int[size][size];
        int [][]b=new int[size][size];
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                a[i][j]=j+1;
                b[i][j]=j+1;
            }
        }
        long startTime = System.nanoTime();
        int [][]c=Arrays.stream ( a )
                .parallel ()
                .map ( aMatrixRow-> IntStream.range ( 0, b[0].length )
                    .map ( i -> IntStream.range(0, b.length )
                        .map(j -> aMatrixRow[j] * b[j][i])
                        .sum ()
                ).toArray ()).toArray (int [][]::new);
        long endTime   = System.nanoTime();
//        for(int []row:c) {
//            System.out.println ( Arrays.toString ( row ) );
//        }
        long totalTime = endTime - startTime;
        System.out.println(totalTime);
    }
}
