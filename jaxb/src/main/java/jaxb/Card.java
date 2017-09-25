package jaxb;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 * @author linzaixian
 * @since 2017-09-25 16:18:05 
 */
@XmlAccessorType(XmlAccessType.FIELD) //采用此方法可以不用set和get方法
public class Card {
    private String id="1111111";
    @XmlAttribute
    private String attribute="属性";
    @XmlElementWrapper(name="all")
    private String[] array=new String[]{"a","b"};
    @Override
    public String toString() {
        return "Card [id=" + id + ", attribute=" + attribute + ", array=" + Arrays.toString(array) + "]";
    }
    

    
    
    
}
