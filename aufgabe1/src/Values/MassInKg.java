/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Values;

import static Values.Values.*;

/**
 *
 * @author abg667
 */
final class MassInKg extends  AbstractScalar implements Mass {
    //INTERNAL REPRESANTATION
    private final double kg;
    
    //OBJECT CREATION
    private MassInKg(double kg){
        this.kg = kg;
    }
    static Mass valueOf(double kg){
        return new MassInKg(kg);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Mass)) return false;
        return Double.compare(this.kg(), ((Mass)o).kg()) == 0;
    }

    @Override
    public int hashCode(){
        return hashDouble(this.kg());
    }

    @Override
    public String toString() {
        return String.format("%.2f",this.kg());
    }

    @Override
    public boolean isZero() {
      return this.equals(ZERO_MASS);
    }

    @Override
    public boolean isValid() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public double kg() {
        return kg;
    }

    @Override
    public double g() {
        return kg()/KG_IN_GRAMM;
    }

    @Override
    public double t() {
        return kg()/KG_IN_TONS;
    }

    @Override
    public double lbs() {
        return kg()*KG_IN_POUNDS;
    }

    @Override
    public int compareTo(Mass mass) {
        return Double.compare(this.kg(), mass.kg());
    }

    @Override
    public Mass add(Mass operand) {
        return new MassInKg(this.kg()+operand.kg());
    }

    @Override
    public Mass sub(Mass operand) {
        return new MassInKg(this.kg()-operand.kg());
    }

    @Override
    public Mass mul(double factor) {
        return new MassInKg(this.kg()*factor);
    }

    @Override
    public Mass div(double factor) {
        return new MassInKg(this.kg()/factor);
    }

    @Override
    public Mass invers() {
        return new MassInKg(-this.kg());
    }
    
    
    
   
}
