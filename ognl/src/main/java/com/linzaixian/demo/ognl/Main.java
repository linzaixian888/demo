package com.linzaixian.demo.ognl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ognl.Ognl;
import ognl.OgnlContext;

/**
 * @author linzaixian
 * @since 2017-09-07 23:15:31 
 */
public class Main {
	private static final Logger logger=LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) throws Exception {
        OgnlContext context=new OgnlContext(new HashMap<>(23));
        Map person=new HashMap<>();
        person.put("name", "名字");
        Map dog=new HashMap<>();
        dog.put("name", "小狗");
        person.put("dog", dog);
        context.put("person", person);
        Bean bean=new Bean();
        bean.setName("test");
        context.put("bean", bean);
        context.setRoot(person);
        
        logger.debug("根节点属性name:{}",Ognl.getValue("name", context,context.getRoot()));
        logger.debug("根节点属性dog:{}",Ognl.getValue("dog", context,context.getRoot()));
        logger.debug("person里的name:{}",Ognl.getValue("#person.name", context,context.getRoot()));
        logger.debug("person里的dog里的name:{}",Ognl.getValue("#person.dog.name", context,context.getRoot()));
        logger.debug("调用bean里的无参数无返回test方法:{}", Ognl.getValue("#bean.test()", context,context.getRoot()));
        logger.debug("调用bean里的有参数有返回test方法:{}", Ognl.getValue("#bean.test(#person.dog.name)", context,context.getRoot()));
        logger.debug("进行判断返回{}",Ognl.getValue("name=='名字'", context,context.getRoot()));
        logger.debug("进行判断返回{}",Ognl.getValue("name!='名字'", context,context.getRoot()));
        logger.debug("进行多个条件判断返回{}",Ognl.getValue("name=='名字' && #person.name=='名字'", context,context.getRoot()));
    }
}
