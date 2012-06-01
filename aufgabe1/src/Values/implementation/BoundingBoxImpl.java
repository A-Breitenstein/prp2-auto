/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Values.implementation;

import Values.interfaces.BoundingBox;
import Values.interfaces.Length;

/**
 *
 * @author abg667
 */
public class BoundingBoxImpl implements BoundingBox {
    
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
    public boolean isZero() {
        throw new UnsupportedOperationException("Not supported yet.");
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public BoundingBox sub(BoundingBox operand) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public BoundingBox mul(double factor) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public BoundingBox div(double factor) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public BoundingBox invers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public double volume() {
        return length().m()*width().m()*height().m();
    }
    
}
