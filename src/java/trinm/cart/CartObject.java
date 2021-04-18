/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trinm.cart;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author tring
 */
public class CartObject {

    Map<String, Integer> items;
    int size = 0;

    public Map<String, Integer> getItems() {
        return items;
    }
    
    public int getSize() {
        return size;
    }

    public void addItemToCart(String id) {
        //1.    Check items existed
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        int quantity = 1;
        //2.    Check item not existed
        if (this.items.containsKey(id)) {
            quantity = this.items.get(id) + 1;
        }
        this.items.put(id, quantity);
        size = items.size();
    }

    public void removeItemFromCart(String id) {
        //1.    Check items existed
        if (this.items == null) {
            return;
        }
        //2.    Check item existed
        if (this.items.containsKey(id)) {
            this.items.remove(id);
            size += 1;
            if (this.items.isEmpty()) {
                this.items = null;
                size = 0;
            }
        }
    }

    public void removeAll() {
        items = null;
        size = 0;
    }

    public void setQuantity(String id, int quantity) {
        //1.    Check items existed
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        this.items.put(id, quantity);
    }

}
