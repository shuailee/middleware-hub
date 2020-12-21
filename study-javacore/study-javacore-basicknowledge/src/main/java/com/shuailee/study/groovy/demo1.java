package com.shuailee.study.groovy;

import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import groovy.lang.GroovyShell;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;
import org.codehaus.groovy.control.CompilationFailedException;
import org.codehaus.groovy.jsr223.GroovyScriptEngineFactory;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import java.io.File;
import java.io.IOException;

/**
 * @program: study-javacore
 * @description: demo1
 * @author: shuai.li
 * @create: 2020-06-04 16:53

 **/
public class demo1 {
    public static void main(String[] args) throws ResourceException, ScriptException, IOException {
        //groovyShellTest();
        //groovyScriptEngineTest();

        groovyClassLoaderTest();
    }



    /**
     * 1 使用GroovyShell的evaluate来执行groovy脚本，可以是文件，字符串，输入流等
     * */
    public static void groovyShellTest() throws CompilationFailedException, IOException {

        // 使用Groovy提供的Binding类来绑定参数
        Binding binding = new Binding();
        binding.setProperty("a", 1);
        binding.setProperty("b", 2);
        GroovyShell groovyShell = new GroovyShell(binding);
        // Object result =groovyShell.evaluate(new File("study-javacore-basicknowledge/src/main/java/com/shuailee/study/groovy/Sum.groovy"));
        Object result =groovyShell.evaluate("def cal(int a, int b){\n" +
                "    return a+b\n" +
                "} \n cal(a,b)");
        System.out.println(result.toString());

    }

    /**
     * 2  使用GroovyScriptEngine
     * */
    private static void groovyScriptEngineTest() throws IOException, ResourceException, ScriptException {
        String path = "study-javacore-basicknowledge/src/main/java/com/shuailee/study/groovy/Test.groovy";

        GroovyScriptEngine gse = new GroovyScriptEngine(path);
        // 通过binding来读取，写入参数
        Binding binding = new Binding();
        binding.setVariable("input", "Groovy");
        gse.run("Test.groovy", binding);
        // 获取groovy的变量值
        Object str = binding.getVariable("output");
        System.out.println(str);
    }

    /**
     * 使用ScriptEngine
     * */
    private static final GroovyScriptEngineFactory scriptEngineFactory = new GroovyScriptEngineFactory();

    private static <T> T invoke(String script, String function, Object... objects) throws Exception {
        ScriptEngine scriptEngine = scriptEngineFactory.getScriptEngine();
        scriptEngine.eval(script);
        return (T) ((Invocable) scriptEngine).invokeFunction(function, objects);
    }


    /**
     * 3  使用GroovyClassLoader
     *
     * GroovyClassLoader是一个定制的类装载器，在代码执行时动态加载groovy脚本为java对象
     * 双亲委派
     * Bootstrap ClassLoader
     *              ↑
     * sun.misc.Launcher.ExtClassLoader      // 即Extension ClassLoader
     *              ↑
     * sun.misc.Launcher.AppClassLoader      // 即System ClassLoader
     *              ↑
     * org.codehaus.groovy.tools.RootLoader  // 以下为User Custom ClassLoader
     *              ↑
     * groovy.lang.GroovyClassLoader
     *              ↑
     * groovy.lang.GroovyClassLoader.InnerLoader

     * */
    private static void groovyClassLoaderTest() {
        GroovyClassLoader classLoader = new GroovyClassLoader();
        // 加载groovy脚本，可以从文件里或字符串里等
        // parseClass方法内会生成一个groovy文件会转为class对象，最终groovy每执行一次脚本，都会生成一个脚本的class对象，
        // 这个class对象的名字由 “script” + System.currentTimeMillis() + Math.abs(text.hashCode()组成，所以即便是每次执行同一个脚本也会生成不同的对象
        // GroovyClassLoader会将每次生成的类缓存进 Map<String, Class> sourceCache， 集合中，如果下次再来访问缓存中如果有的话就直接获取
        //
        Class groovyClass = classLoader.parseClass("def cal(int a, int b){\n" +
                "    return a+b\n" +
                "}");
        try {
            Object[] param = { 8,7 };
            GroovyObject groovyObject = (GroovyObject) groovyClass.newInstance();
            int result = (int)groovyObject.invokeMethod("cal",param);
            System.out.println(result);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
