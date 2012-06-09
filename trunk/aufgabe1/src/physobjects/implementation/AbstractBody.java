/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physobjects.implementation;

import Values.implementation.Values;
import Values.interfaces.BoundingBox;
import Values.interfaces.Mass;
import physobjects.interfaces.Body;

/**
 *
 * @author abg667
 */
abstract class AbstractBody implements Body {
    protected BoundingBox boundingBox = Values.ZERO_BB;
    protected Mass mass = Values.ZERO_MASS;
    
}
