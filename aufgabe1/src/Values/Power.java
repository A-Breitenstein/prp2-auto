
package Values;


public interface Power extends PhysicsScalar, Comparable<Power>
                                                , Operations<Power> {
    
    double kw();
    double w();
    double js();

    Force div(Speed speed);
}
