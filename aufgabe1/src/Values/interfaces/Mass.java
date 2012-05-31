/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Values.interfaces;

/**
 *
 * @author abg667
 */
public interface Mass extends PhysicsScalar, Comparable<Mass>
                                                , Operations<Mass> {
    double  kg();
    double   g();
    double   t();
    double lbs();
    

}
