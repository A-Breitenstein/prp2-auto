
package Values.implementation;

import Values.interfaces.Force;
import Values.interfaces.Mass;
import Values.interfaces.TimeDiff;
import Values.interfaces.Acc;
import Values.interfaces.Speed;
import static Values.implementation.Values.*;

final class AccInMss extends AbstractScalar implements Acc {
    /**<b>*#++++#*INTERNAL REPRESANTATION*#++++#*</b>*/
    private final double mss;
    //OBJECT CONSTRUCTORS
    private AccInMss(double mss){
        this.mss = mss;
    }
    static Acc valueOf(double mss){
        return new AccInMss(mss);
    }
    
    
    
    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Acc)) return false;
        return Double.compare(this.mss(), ((Acc)o).mss()) == 0;
    }

    @Override
    public int hashCode() {
        return hashDouble(this.mss());
    }

    @Override
    public String toString() {
    return String.format("%.2f", this.mss());
    }

    @Override
    public boolean isZero() {
        return this.equals(ZERO_ACC);
    }

    @Override
    public boolean isValid() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public double mss() {
        return mss;
    }

    @Override
    public Speed mul(TimeDiff timeDiff) {
        return SpeedInMpS.valueOf(this.mss()*timeDiff.s());
    }

    @Override
    public Force mul(Mass mass) {
        return ForceInN.valueOf(this.mss()*mass.kg());
    }

    @Override
    public int compareTo(Acc o) {
        return Double.compare(this.mss(), o.mss());
    }

    @Override
    public Acc add(Acc operand) {
        return new AccInMss(this.mss()+operand.mss());
    }

    @Override
    public Acc sub(Acc operand) {
        return new AccInMss(this.mss()-operand.mss());
    }

    @Override
    public Acc mul(double factor) {
        return new AccInMss(this.mss()*factor);
    }

    @Override
    public Acc div(double factor) {
        return new AccInMss(this.mss()/factor);
    }

    @Override
    public Acc invers() {
        return new AccInMss(-this.mss());
    }
    
}
