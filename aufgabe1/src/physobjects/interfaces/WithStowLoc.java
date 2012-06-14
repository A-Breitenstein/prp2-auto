/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physobjects.interfaces;

import Values.interfaces.StowageLocation;

/**
 *
 * @author abg667
 */
public interface WithStowLoc<S> {
    StowageLocation loc();
    void setLocNull();
    void setLoc(S stowage,StowageLocation loc);
    S getStowageReference();
    
    
}
