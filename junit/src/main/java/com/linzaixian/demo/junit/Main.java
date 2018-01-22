/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.linzaixian.demo.junit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  JUnit4使用Java5中的注解（annotation），以下是JUnit4常用的几个annotation：
	@Before：初始化方法   对于每一个测试方法都要执行一次（注意与BeforeClass区别，后者是对于所有方法执行一次）
	@After：释放资源  对于每一个测试方法都要执行一次（注意与AfterClass区别，后者是对于所有方法执行一次）
	@Test：测试方法，在这里可以测试期望异常和超时时间
	@Test(expected=ArithmeticException.class)检查被测方法是否抛出ArithmeticException异常
	@Ignore：忽略的测试方法
	@BeforeClass：针对所有测试，只执行一次，且必须为static void
	@AfterClass：针对所有测试，只执行一次，且必须为static void
	一个JUnit4的单元测试用例执行顺序为:
	@BeforeClass -> @Before -> @Test -> @After -> @AfterClass;
	每一个测试方法的调用顺序为:
	@Before -> @Test -> @After;
 * @author linzaixian@foresee.com.cn
 */

public class Main {
	private static final Logger logger=LoggerFactory.getLogger(Main.class);
	@BeforeClass
	public static void beforeClass() {
		logger.debug("BeforeClass");
	}
	@Before
	public void before() {
		logger.debug("before");
	}
	@After
	public void after() {
		logger.debug("after");
	}
	@AfterClass
	public static void afterClass() {
		logger.debug("afterClass");
	}
	@Test
	public void test() {
		logger.debug("test");
	}
	@Test
	public void test2() {
		logger.debug("test2");
	}
}
