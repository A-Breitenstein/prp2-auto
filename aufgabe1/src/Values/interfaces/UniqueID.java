/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Values.interfaces;

/**
 *
 * @author abg667
 */
public interface UniqueID extends AdminValue, Comparable<UniqueID> {
    
    long idNumber();
}
