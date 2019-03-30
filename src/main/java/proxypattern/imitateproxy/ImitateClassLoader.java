package proxypattern.imitateproxy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @Program: proxy-pattern-20190327
 * @Description: 手写ClassLoader
 * @Author: whx
 * @Create: 2019-03-28 22:35
 **/
public class ImitateClassLoader extends ClassLoader {
	
	private File classPath;
	
	public ImitateClassLoader() {
		String path = ImitateClassLoader.class.getResource("").getPath();
		this.classPath = new File(path);
	}
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		Class<?> clazz = null;
		String className = ImitateClassLoader.class.getPackage().getName() + "." + name;
		
		if (classPath == null) {
			return null;
		}
		
		File classFile = new File(classPath, name.replace("\\", "/") + ".class");
		if (!classFile.exists()) {
			return null;
		}
		FileInputStream in = null;
		ByteArrayOutputStream out = null;
		
		try {
			in = new FileInputStream(classFile);
			out = new ByteArrayOutputStream();
			byte[] bytes = new byte[1024];
			int len;
			while ((len = in.read(bytes)) != -1) {
				out.write(bytes, 0, len);
			}
			clazz = defineClass(className, out.toByteArray(), 0, out.size());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return clazz;
		
	}
	
}