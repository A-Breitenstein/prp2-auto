
package Values;


public interface TimeDiff extends PhysicsScalar, Comparable<TimeDiff>
                                                , Operations<TimeDiff>  {
    
    double ms();
    double s();
    double m();
    double h();
    
    
    
}
