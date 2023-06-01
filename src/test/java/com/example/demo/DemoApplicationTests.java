package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class DemoApplicationTests {

	public static void main(String[] args) {
		int totalCount = 4;
		int psize = 2;
		double pcount = totalCount / (psize * 1.0);
		System.out.println(Math.ceil(pcount));
	}

	@Test
	void contextLoads() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = "123456";
		String finalPassword = passwordEncoder.encode(password);
		System.out.println("第1次加密：" + finalPassword);
		System.out.println("第2次加密：" + passwordEncoder.encode(password));
		System.out.println("第3次加密：" + passwordEncoder.encode(password));
		// 验证
		String inputPassword = "12345";
		System.out.println("错误密码比对结果：" +
				(passwordEncoder.matches(inputPassword, finalPassword)));
		String inputPassword2 = "123456";
		System.out.println("正确密码比对结果：" +
				(passwordEncoder.matches(inputPassword2, finalPassword)));

	}
//
//    public static void main(String[] args) {
//        String password = "123456";
//        String mdString = DigestUtils.md5DigestAsHex(password.getBytes());
//        System.out.println(mdString);
//    }

}
