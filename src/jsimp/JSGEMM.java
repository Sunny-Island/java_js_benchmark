package jsimp;
import com.sun.xml.internal.ws.server.sei.EndpointArgumentsBuilder;
import jdk.nashorn.api.scripting.ClassFilter;

import javax.script.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JSGEMM {
    ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
    ScriptEngine nashorn = scriptEngineManager.getEngineByName("nashorn");
    int m, n, k;

    public JSGEMM(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;

    }

    public void runScript(int n) throws ScriptException, FileNotFoundException {
        String path = new File("").getAbsolutePath();
        System.out.print(path);
        for(int i = 0;i<n;i++) {
            nashorn.eval(new FileReader(path +"/src/jsimp/GEMM.js"));
        }
    }

    public void runFunc(int n) throws ScriptException, FileNotFoundException, NoSuchMethodException {
        String path = new File("").getAbsolutePath();
        System.out.print(path);
        nashorn.eval(new FileReader(path +"/src/jsimp/GEMM_FUNC.js"));
        Invocable invocable = (Invocable) nashorn;
        for(int i = 0;i<n;i++) {
            invocable.invokeFunction("multiply",this.m, this.n, this.k);
        }
    }

    public void runCompile(int n) throws ScriptException, NoSuchMethodException, FileNotFoundException {
        String path = new File("").getAbsolutePath();
        CompiledScript compiledScript = ((Compilable)nashorn).compile(
                new FileReader(path +"/src/jsimp/GEMM_FUNC.js"));

        Invocable invocable = (Invocable) compiledScript.getEngine();
        for(int i = 0;i<n;i++) {
            invocable.invokeFunction("multiply", this.m, this.n, this.k);
        }
    }

    public void serializeTest() throws IOException, ScriptException, NoSuchMethodException, ClassNotFoundException {
        ObjectOutputStream objectOutputStream =
                new ObjectOutputStream( new FileOutputStream( new File("student.txt") ) );
        String path = new File("").getAbsolutePath();
        CompiledScript compiledScript = ((Compilable)nashorn).compile(
                new FileReader(path +"/src/jsimp/GEMM_FUNC.js"));
        objectOutputStream.writeObject(compiledScript);
        objectOutputStream.close();

        ObjectInputStream objectInputStream =
                new ObjectInputStream( new FileInputStream( new File("student.txt") ) );
        CompiledScript compiledScript1 = (CompiledScript)objectInputStream.readObject();

        Invocable invocable = (Invocable) compiledScript1.getEngine();
        invocable.invokeFunction("multiply", this.m, this.n, this.k);
    }
    public static void main(String[] args) throws ScriptException, IOException, NoSuchMethodException, ClassNotFoundException {
        JSGEMM jsgemm = new JSGEMM(100, 100, 100);


        jsgemm.serializeTest();
    }
}
