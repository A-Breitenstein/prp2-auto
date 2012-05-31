
package Values.implementation;

import Values.interfaces.Force;
import Values.interfaces.Power;
import Values.interfaces.Speed;
import static Values.implementation.Values.*;

final class PowerInW extends AbstractScalar implements Power {
    public static int InstanceCounter;
    public void finalize() throws Throwable{
        InstanceCounter --;
        super.finalize();
    }
    //INTERNAL REPRESANTION
    private final double w;
    //OBJECT CREATION
    private PowerInW(double w){
        this.w = w;InstanceCounter ++;
    }
    
    static Power valueOf(double w){
        return new PowerInW(w);
    }
    
    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Power)) return false;
        return Double.compare(this.w(), ((Power)o).w()) == 0;
    }

    @Override
    public int hashCode() {
        return hashDouble(this.w());
    }

    @Override
    public String toString() {
        return String.format("%.2f", this.w());
    }

    @Override
    public boolean isZero() {
        return this.equals(ZERO_POWER);
    }

    @Override
    public boolean isValid() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public double kw() {
        return this.w()/W_IN_KW;
    }

    @Override
    public double w() {
        return w;
    }

    @Override
    public double js() {
        return this.w();
    }

    @Override
    public int compareTo(Power power) {
        return Double.compare(this.w(), power.w());
    }

    @Override
    public Power add(Power operand) {
        return new PowerInW(this.w()+operand.w());
    }

    @Override
    public Power sub(Power operand) {
        return new PowerInW(this.w()-operand.w());
    }

    @Override
    public Power mul(double factor) {
        return new PowerInW(this.w()*factor);
    }

    @Override
    public Power div(double factor) {
        return new PowerInW(this.w()/factor);
    }

    @Override
    public Power invers() {
        return new PowerInW(-this.w());
    }

    @Override
    public Force div(Speed speed) {
        return ForceInN.valueOf(this.w()/speed.mps());
    } 
    
}
