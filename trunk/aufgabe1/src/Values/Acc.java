/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Values;

/**
 *
 * @author abg667
 * 
 */
public interface Acc extends PhysicsScalar, Comparable<Acc>
                                                , Operations<Acc> {
    
    double mss();
    
    Speed mul(TimeDiff timeDiff);
    Force mul(Mass mass);
    
    
}
