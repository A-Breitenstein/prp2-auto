/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physobjects.interfaces;

/**
 *
 * @author abg667
 */
public interface Container extends Stowage<Pallet>, WithUniqueID, WithForm, WithStowLoc<Container>, Comparable<Container> {
    
}
