package test;

import java.util.Date;

import org.apache.shiro.util.ByteSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.sun.baisc.model.User;

public class Test {

	public static void main(String[] args) {
		User user = new User();
		user.setUserName("sss");
		user.setPassword("1");
		user.setSalt("123");
		System.out.println(ByteSource.Util.bytes(user.getCredentialsSalt()));
	}

}
