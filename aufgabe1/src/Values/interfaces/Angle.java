/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Values.interfaces;

/**
 *
 * @author abg667
 */
public interface Angle extends PhysicsScalar, Comparable<Angle>, Operations<Angle> {
    double deg();
    double rad();
    
    Angle mul(TimeDiff timediff);
}
