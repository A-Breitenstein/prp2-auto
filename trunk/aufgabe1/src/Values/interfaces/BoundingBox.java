/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Values.interfaces;

/**
 *
 * @author abg667
 */
public interface BoundingBox extends PhysicsScalar, Comparable<BoundingBox>, Operations<BoundingBox> {
    Length length();
    Length width();
    Length height();
    
    boolean fitsInto(BoundingBox boundingBox);
    double volume();
}
