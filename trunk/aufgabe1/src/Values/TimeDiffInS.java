
package Values;

import static Values.Values.*;

public class TimeDiffInS extends AbstractScalar implements TimeDiff {

    //INTERNAL REPRESENTATION
    private final double seconds;
    
    //Object
    private TimeDiffInS(double seconds){
        this.seconds = seconds;
    }
    
    public static TimeDiff valueOf(double seconds){
        return new TimeDiffInS(seconds);
    }
    
    
    
    
    @Override
    public boolean isZero() {
        return this == ZERO_TIMEDIFF;
    }

    @Override
    public boolean isValid() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public double ms() {
        return this.s()/MS_IN_SECONDS;
    }

    @Override
    public double s() {
        return seconds;
    }

    @Override
    public double m() {
        return this.s()/SECONDS_IN_MINUTES;
    }

    @Override
    public double h() {
        return this.s()/SECONDS_IN_HOURS;
    }

    @Override
    public int compareTo(TimeDiff timediff) {
        return Double.compare(this.s(),timediff.s());
    }

    @Override
    public TimeDiff add(TimeDiff operand) {
        return new TimeDiffInS(this.s()+operand.s());
    }

    @Override
    public TimeDiff sub(TimeDiff operand) {
        return new TimeDiffInS(this.s()-operand.s());
    }


    @Override
    public TimeDiff mul(double factor) {
        return new TimeDiffInS(this.s()*factor);
    }

    @Override
    public TimeDiff div(double factor) {
       return new TimeDiffInS(this.s()/factor);
    }

    @Override
    public TimeDiff invers() {
       return new TimeDiffInS(-this.s()); 
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof TimeDiff)) return false;
        return Double.compare(this.s(), ((TimeDiff)o).s()) == 0;
    }

    @Override
    public int hashCode() {
        return hashDouble(this.s());
    }

    @Override
    public String toString() {
        return String.format("%.2f",this.s());
    }
    
}
