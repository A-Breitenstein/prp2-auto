/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Values.implementation;

import Values.interfaces.BoundingBox;
import Values.interfaces.Length;
import static Values.implementation.Values.*;

/**
 *
 * @author abg667
 */
final class BoundingBoxImpl extends AbstractValue implements BoundingBox {
    
    //INTERNAL REPRESENTATION
    private Length length;
    private Length width;
    private Length height;
    //private CONSTRUCTOR
    private BoundingBoxImpl(Length length, Length width, Length heigth){
        this.length = length;
        this.width = width;
        this.height = heigth;
    }

    
    public static BoundingBoxImpl valueOf(Length length, Length width, Length heigth){
        return new BoundingBoxImpl(length, width, heigth);
    }
    @Override
    public Length length() {
        return length;
    }

    @Override
    public Length width() {
        return width;
    }

    @Override
    public Length height() {
        return height;
    }

    @Override
    public boolean fitsInto(BoundingBox boundingBox) {
        return compareTo(boundingBox) == 0;
    }

    @Override
    public boolean isZero(){
        return this.equals(ZERO_BB);
    }

    @Override
    public boolean isValid() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int compareTo(BoundingBox o) {
        return Double.compare(volume(), o.volume());
    }

    @Override
    public BoundingBox add(BoundingBox operand) {
        return valueOf(this.length().add(operand.length()),this.width().add(operand.width()),this.height().add(operand.height()));
    }

    @Override
    public BoundingBox sub(BoundingBox operand) {
         return valueOf(this.length().sub(operand.length()),this.width().sub(operand.width()),this.height().sub(operand.height()));
    }

    @Override
    public BoundingBox mul(double factor) {
        return valueOf(this.length().mul(factor),this.width().mul(factor),this.height().mul(factor));
    }

    @Override
    public BoundingBox div(double factor) {
        return valueOf(this.length().div(factor),this.width().div(factor),this.height().div(factor));
    }

    @Override
    public BoundingBox invers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public double volume() {
        return length().m()*width().m()*height().m();
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof BoundingBox)) return false;
          BoundingBox  bb =((BoundingBox)o);
        return this.height().equals(bb.height()) &&
               this.width().equals(bb.width()) &&
               this.length().equals(bb.length()) ;
                 
    }

    @Override
    public int hashCode() {
        return hashDouble((double) length().m()+width().m()+height().m());
    }

    @Override
    public String toString() {
        return "BoundingBox: length: "+length()+" width: "+width()+" height: "+height();
    }
    
}
