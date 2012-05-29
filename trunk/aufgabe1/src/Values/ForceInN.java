
package Values;

import static Values.Values.*;

public class ForceInN extends AbstractScalar implements Force {
    //INTERNAL REPRESANTATION
    private final double n;
    //OBJECT CREATION
    private ForceInN(double n){
        this.n = n;
    }
    /** Class Force: FactoryMethode 
     * @param n Eine Kraft 
     * @return Force
     * 
     */
    static Force valueOf(double n){
        return new ForceInN(n); 
    }
    
    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Force)) return false;
        return Double.compare(this.n(), ((Force)o).n()) == 0;
    }

    @Override
    public int hashCode() {
        return hashDouble(this.n());
    }

    @Override
    public String toString() {
        return String.format("%.2f", this.n());
    }

    @Override
    public boolean isZero() {
        return this == ZERO_FORCE;
    }

    @Override
    public boolean isValid() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public double n() {
        return n;
    }

    @Override
    public double kn() {
        return this.n()*NEWTON_IN_KNEWTON;
    }

    @Override
    public Power mul(Speed speed) {
        return PowerInW.valueOf(this.n()/speed.mps());
    }

    @Override
    public Acc div(Mass mass) {
        return AccInMss.valueOf(this.n()/mass.kg());
    }

    @Override
    public Mass div(Acc acc) {
        return MassInKg.valueOf(this.n()/acc.mss());
    }

    @Override
    public int compareTo(Force o) {
        return Double.compare(this.n(), o.n());
    }

    @Override
    public Force add(Force operand) {
        return new ForceInN(this.n() + operand.n());
    }

    @Override
    public Force sub(Force operand) {
        return new ForceInN(this.n()- operand.n());
    }

    @Override
    public Force mul(double factor) {
        return new ForceInN(this.n()*factor);
    }

    @Override
    public Force div(double factor) {
        return new ForceInN(this.n()/factor);
    }

    @Override
    public Force invers() {
        return new ForceInN(-this.n());
    }
    
}
