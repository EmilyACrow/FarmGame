/**
* Wrapper for a Merchandise Arraylist. Sole purpose is to enable JAXB XML Parsing
* 
* Implements XmlElements based on code from Robby Cornelissen's answer to this question on SO
* (https://stackoverflow.com/questions/42289733/jaxb-xmlelement-with-multiple-names-and-types)
* Structure of this class based on 
* (https://howtodoinjava.com/jaxb/jaxb-exmaple-marshalling-and-unmarshalling-list-or-set-of-objects/)
* 
* Last modified: 29-4-20 by Dmitri Smith
*
* Created: 28-4-20
* @author  Dmitri Smith
*/

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "merch")
@XmlAccessorType(XmlAccessType.FIELD)
public class MerchWrapper {
	@XmlElements({
        @XmlElement(name="animal",type=Animal.class),
        @XmlElement(name="crop",type=Crop.class),
        @XmlElement(name="item",type=Item.class)
    })
	private ArrayList<Merchandise> merchList = new ArrayList<Merchandise>();

 
    public ArrayList<Merchandise> getMerchList() {
        return merchList;
    }
 
    public void setMerchList(ArrayList<Merchandise> merchList) {
        this.merchList = merchList;
    } 

}
