package jaxb;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * @author linzaixian
 * @since 2017-09-25 16:06:06 
 */
public class Main {
    public static void main(String[] args) throws Exception {
       String xml= marshal();
       System.out.println(xml);
       User user=unmarshaller(xml);
       System.out.println(user);
       
    }
    /**
     * 对象转xml
     * @throws Exception 
     */
    public static String marshal() throws Exception {
        StringWriter stringWriter=new StringWriter();
        User user=new User();
        user.setId(33);
        user.setName("54name");
        JAXBContext context = JAXBContext.newInstance(user.getClass());  
        Marshaller marshaller = context.createMarshaller();  
        // 格式化xml输出的格式  
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,  
                Boolean.TRUE);  
        marshaller.marshal(user, stringWriter);
        return stringWriter.getBuffer().toString();
    }
    /**
     * xml转对象
     * @param xml
     * @return
     * @throws JAXBException
     */
    public static User unmarshaller (String xml) throws JAXBException{
        JAXBContext context = JAXBContext.newInstance(User.class);  
        Unmarshaller unmarshaller=context.createUnmarshaller();
        return (User) unmarshaller.unmarshal(new StringReader(xml));
    }
}
