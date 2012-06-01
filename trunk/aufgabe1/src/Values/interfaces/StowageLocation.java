/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Values.interfaces;

/**
 *
 * @author abg667
 */
public interface StowageLocation extends AdminValue {
    
    int bay();
    int row();
    int tier();
    boolean isNull();
}
