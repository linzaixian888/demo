package com.linzaixian.demo.ognl;

import java.util.HashMap;
import java.util.Map;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

/**
 * @author linzaixian
 * @since 2017-09-07 23:15:31 
 */
public class Main {
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
        System.out.println(Ognl.getValue("name", context,context.getRoot()));
        System.out.println(Ognl.getValue("dog", context,context.getRoot()));
       System.out.println(Ognl.getValue("#person.name", context,context.getRoot()));
       System.out.println(Ognl.getValue("#person.dog.name", context,context.getRoot()));
       System.out.println(Ognl.getValue("#bean.test()", context,context.getRoot()));
       
       
    }
}
