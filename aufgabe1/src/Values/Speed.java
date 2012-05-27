/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Values;

/**
 *
 * @author abg667
 */
public interface Speed extends PhysicsScalar, Comparable<Speed>,
                                                Operations<Speed> {
    double mps();
    double kmh();
    double mph();
    double kn();
    
    Length mul(TimeDiff timediff);
    
}
