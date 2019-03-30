package proxypattern.imitateproxy;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @Program: proxy-pattern-20190327
 * @Description: 手写Proxy
 * @Author: whx
 * @Create: 2019-03-28 22:35
 **/
public class ImitateProxy {
	
	private static final String ln = "\r\n";
	
	public static Object newInstance(ImitateClassLoader classLoader, Class<?>[] interfaces, ImitateInvocationHandler h) {
		
		try {
			// 1.动态生成源代码.java文件
			String src = generateSrc(interfaces);
			
			// 2.java文件输出到磁盘
			String path = ImitateProxy.class.getResource("").getPath();
			System.out.println(path);
			
			File file = new File(path + "$Proxy0.java");
			FileWriter fw = new FileWriter(file);
			fw.write(src);
			fw.flush();
			fw.close();
			
			// 3.把生成得java文件编译成class
			JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
			StandardJavaFileManager manager = javaCompiler.getStandardFileManager(null, null, null);
			Iterable<? extends JavaFileObject> fileObjects = manager.getJavaFileObjects(file);
			
			JavaCompiler.CompilationTask task = javaCompiler.getTask(null, manager, null, null, null, fileObjects);
			
			task.call();
			manager.close();
			
			// 4.编译生成得class文件加载到jvm中
			Class<?> proxyClass = classLoader.findClass("$Proxy0");
			Constructor<?> constructors = proxyClass.getConstructor(ImitateInvocationHandler.class);
			file.delete();
			
			
			// 5.返回字节码重组以后得新得代理对象
			return constructors.newInstance(h);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	private static String generateSrc(Class<?>[] interfaces) {
		
		StringBuffer sb = new StringBuffer();
		sb.append("package proxypattern.imitateproxy;" + ln);
		sb.append("import proxypattern.Person;" + ln);
		sb.append("import java.lang.reflect.*;" + ln);
		sb.append("public class $Proxy0 implements " + interfaces[0].getName() + "{" + ln);
		sb.append("ImitateInvocationHandler h;" + ln);
		sb.append("public $Proxy0(ImitateInvocationHandler h) { " + ln);
		sb.append("this.h = h;");
		sb.append("}" + ln);
		for (Method m : interfaces[0].getMethods()) {
			sb.append("public " + m.getReturnType().getName() + " " + m.getName() + "() {" +
					ln);
			sb.append("try{" + ln);
			sb.append("Method m = " + interfaces[0].getName() + ".class.getMethod(\""
					+ m.getName() + "\",new Class[]{});" + ln);
			sb.append("this.h.invoke(this,m,null);" + ln);
			sb.append("}catch(Throwable e){" + ln);
			sb.append("e.printStackTrace();" + ln);
			sb.append("}");
			sb.append("}");
		}
		sb.append("}" + ln);
		return sb.toString();
	}
	
}