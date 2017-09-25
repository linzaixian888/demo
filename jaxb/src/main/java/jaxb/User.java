package jaxb;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author linzaixian
 * @since 2017-09-25 16:09:43
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD) // 采用此方法可以不用set和get方法
@XmlType(propOrder = { // 排序专用
        "name", "card", "cardArray", "id" })
public class User {
    private Integer id;
    private String name;
    private Card card = new Card();
    private Card[] cardArray = new Card[] { new Card(), new Card() };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Card[] getCardArray() {
        return cardArray;
    }

    public void setCardArray(Card[] cardArray) {
        this.cardArray = cardArray;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", card=" + card + ", cardArray=" + Arrays.toString(cardArray)
                + "]";
    }

}
