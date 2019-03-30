package proxypattern.imitateproxy;

import proxypattern.Person;

import java.lang.reflect.Method;

/**
 * @Program: proxy-pattern-20190327
 * @Description: 手写代理
 * @Author: whx
 * @Create: 2019-03-28 22:35
 **/
public class ImitateMeiPo implements ImitateInvocationHandler {
	
	/**
	 * 保存被代理对象得引用
	 */
	private Person target;
	
	public Object getInstance(Person target) {
		this.target = target;
		Class<? extends Person> clazz = target.getClass();
		return ImitateProxy.newInstance(new ImitateClassLoader(), clazz.getInterfaces(), this);
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		before();
		Object invoke = method.invoke(this.target, args);
		System.out.println("实际执行结果：" + invoke + " ,参数== " + target + " : " + args);
		after();
		return invoke;
	}
	
	private void before() {
		System.out.println("我是媒婆： 我要给你找对象， 现在已经拿到你的需求");
		System.out.println("开始物色");
	}
	
	private void after() {
		System.out.println("如果合适的话， 就准备办事");
	}
}