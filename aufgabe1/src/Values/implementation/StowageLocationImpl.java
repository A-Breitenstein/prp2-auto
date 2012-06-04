/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Values.implementation;

import Values.interfaces.StowageLocation;

/**
 *
 * @author abg667
 */
final class StowageLocationImpl extends AbstractValue implements StowageLocation {

    private int bay,row,tier;
    //CONSTRUCTOR
    private StowageLocationImpl(int bay,int row,int tier){
        this.bay = bay;
        this.row = row;
        this.tier = tier;
    }
    
    public static StowageLocation valueOf(int bay,int row,int tier){
        return new StowageLocationImpl(bay, row, tier);
    }
    
    @Override
    public int bay() {
        return bay;
    }

    @Override
    public int row() {
        return row;
    }

    @Override
    public int tier() {
        return tier;
    }

    @Override
    public boolean isNull() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof StowageLocation)) return false;
        StowageLocation sl = (StowageLocation)o;
        return (bay() == sl.bay()) && (row() == sl.row() )&& (tier() == sl.tier());
    }

    @Override
    public int hashCode() {
       return hashDouble((double) bay()+row()+tier());
    }

    @Override
    public String toString() {
        return "StowageLocation: bay: "+bay()+" row: "+row()+" tier: "+tier();
    }
    
}
