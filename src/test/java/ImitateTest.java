import org.junit.Test;
import proxypattern.Person;
import proxypattern.imitateproxy.ImitateMeiPo;
import proxypattern.jdkproxy.Customer;

import java.io.IOException;

/**
 * @Program: proxy-pattern-20190327
 * @Description: 手写代理测试类
 * @Author: whx
 * @Create: 2019-03-28 22:35
 **/
public class ImitateTest {
	@Test
	public void TestJDK() throws IOException {
		Person person = (Person) new ImitateMeiPo().getInstance(new Customer());
		person.findLove();
		
	}
	
}
