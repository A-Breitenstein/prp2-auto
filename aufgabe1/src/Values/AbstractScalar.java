/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Values;

/**
 *
 * @author abg667
 */
abstract class AbstractScalar extends AbstractValue implements PhysicsScalar{
    
    @Override
    public abstract boolean equals(Object o);
    
    @Override
    public abstract int hashCode();
    
    @Override
    public abstract String toString();
    
    protected static int hashDouble(double val){
        long longBits = Double.doubleToLongBits(val);
        return (int) (longBits ^ (longBits >>> 32));
    }
    
    
    
}
