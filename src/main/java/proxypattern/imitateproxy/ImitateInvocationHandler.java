package proxypattern.imitateproxy;

import java.lang.reflect.Method;

/**
 * @Program: proxy-pattern-20190327
 * @Description: 手写InvocationHandler接口
 * @Author: whx
 * @Create: 2019-03-28 22:35
 **/
public interface ImitateInvocationHandler {
	Object invoke(Object Proxy, Method method, Object[] args) throws Throwable;
	
}
