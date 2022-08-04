package javaimp;

import java.util.Random;
public class GEMM {
    double[][] A;
    double[][] B;
    double[][] C;
    int M;
    int N;
    int K;
    public GEMM(int m, int n, int k) {
        M = m;
        N = n;
        K = k;
        A = new double[m][k];
        B = new double[k][n];
        C = new double[m][n];
        Random r = new Random();
        for(int j = 0;j<k;j++) {
            for(int i = 0;i<m;i++) {
                A[i][j] = r.nextDouble() * 10;
            }
            for(int b = 0;b<n;b++) {
                B[j][b] = r.nextDouble() * 10;
            }
        }

    }
    public void compute() {
        for(int m = 0;m<M;m++) {
            for(int k = 0;k<K;k++) {
                for(int n = 0;n<N;n++) {
                    C[m][n] += A[m][k] * B[k][n];
                }
            }
        }
    }
    public void runCompute(int n) {
        for(int i = 0;i<n;i++) {
            compute();
        }
    }
}
