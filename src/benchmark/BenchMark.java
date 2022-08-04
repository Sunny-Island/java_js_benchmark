package benchmark;

import javaimp.GEMM;
import jsimp.JSGEMM;

import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.util.Timer;

public class BenchMark {
    GEMM javaGEMM;
    int m, n, k;
    JSGEMM jsgemm;
    public BenchMark(int m, int n, int k) {
        javaGEMM = new GEMM(m ,n ,k);
        jsgemm = new JSGEMM(m, n, k);
    }
    public void runBenchMark(int n) throws ScriptException, FileNotFoundException, NoSuchMethodException {
        long startJavaGEMM = System.currentTimeMillis();
        javaGEMM.runCompute(n);
        long endJavaGEMM = System.currentTimeMillis();
        long javaGEMMCost = endJavaGEMM - startJavaGEMM;

        long startRunScript = System.currentTimeMillis();
        jsgemm.runScript(n);
        long endRunScript = System.currentTimeMillis();
        long jsRunScript = endRunScript - startRunScript;

        long startRunFunc = System.currentTimeMillis();
        jsgemm.runFunc(n);
        long endRunFunc = System.currentTimeMillis();
        long jsRunFunc = endRunFunc - startRunFunc;

        long startRunCompile = System.currentTimeMillis();
        jsgemm.runCompile(n);
        long endRunCompile = System.currentTimeMillis();
        long jsRunCompile = endRunCompile - startRunCompile;
        System.out.format(" %n javaGEMMCost : %d, jsRunScript: %d, jsRunFunc: %d, jsRunCompile %d %n" ,
                javaGEMMCost, jsRunScript, jsRunFunc, jsRunCompile);
    }

    public static void main(String[] args) throws ScriptException, FileNotFoundException, NoSuchMethodException {
        BenchMark benchMark = new BenchMark(10, 10, 10);
        benchMark.runBenchMark(100);
    }
}
