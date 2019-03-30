import org.junit.Test;
import proxypattern.Person;
import proxypattern.jdkproxy.Customer;
import proxypattern.jdkproxy.JDKMeiPo;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Program: proxy-pattern-20190327
 * @Description: JDM代理测试类
 * @Author: whx
 * @Create: 2019-03-28 22:35
 **/
public class JDKTest {
	
	@Test
	public void TestJDK() throws IOException {
		Person person = (Person) new JDKMeiPo().getInstance(new Customer());
		person.findLove();

		//通过反编译工具可以查看源代码
		byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy4", new Class[]{Person.class});
		FileOutputStream os = new FileOutputStream("E://Workspace//IdeaProjects//proxy-pattern-20190327//src//test//java//$Proxy4.class");
		os.write(bytes);
		os.close();
	}
	
}