
package Values;

public interface Length extends PhysicsScalar,Comparable <Length>,
                                                Operations <Length> {
    double ft();
    double  m();
    double km();
    double nm();

    TimeDiff div(Speed speed);
    Speed div(TimeDiff time);


}
