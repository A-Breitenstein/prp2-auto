/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Values;

/**
 *
 * @author abg667
 */
public interface Force  extends PhysicsScalar , Comparable<Force>, 
                                                Operations<Force>  {
    
    double n();
    double kn();

    Power mul(Speed speed);
    Acc div(Mass mass);
    Mass div(Acc acc);
}
