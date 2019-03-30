import org.junit.Test;
import proxypattern.cglibproxy.CglibMeiPo;
import proxypattern.cglibproxy.Customer;

/**
 * @Program: proxy-pattern-20190327
 * @Description: cglib代理测试类
 * @Author: whx
 * @Create: 2019-03-28 22:35
 **/
public class CglibTest {
	
	@Test
	public void TestCglib() throws Exception {
		Customer instance = (Customer) new CglibMeiPo().getInstance(Customer.class);
		instance.findLove();
	}
	
}