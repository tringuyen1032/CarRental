/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trinm.map;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author tring
 */
public class MapObject implements Serializable{

    Map<String, String> map = new HashMap<>();

    public Map<String, String> getItems() {
        return map;
    }
    
}
