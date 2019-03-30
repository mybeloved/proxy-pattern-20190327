package proxypattern.cglibproxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Program: proxy-pattern-20190327
 * @Description: cglib代理
 * @Author: whx
 * @Create: 2019-03-28 22:35
 **/
public class CglibMeiPo implements MethodInterceptor {
	
	public Object getInstance(Class<?> clazz) throws Exception {
		//相当于Proxy，代理的工具类
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(clazz);
		enhancer.setCallback(this);
		return enhancer.create();
	}
	
	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		before();
		Object o1 = methodProxy.invokeSuper(o, objects);
		after();
		return o1;
	}
	
	
	private void before() {
		System.out.println("我是媒婆： 我要给你找对象， 现在已经拿到你的需求");
		System.out.println("开始物色");
	}
	
	private void after() {
		System.out.println("如果合适的话， 就准备办事");
	}
}