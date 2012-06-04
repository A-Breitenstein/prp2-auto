/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Values.implementation;

import Values.interfaces.UniqueID;

/**
 *
 * @author abg667
 */
final class UniqueIdImpl extends AbstractValue implements UniqueID {

    private long id;
    //erstmal auf dem schlechten weg
    public static long autoIncrementNumber = 0;
    
    
    private UniqueIdImpl(){
        autoIncrementNumber++;
        this.id = autoIncrementNumber;
        
    }
    public static UniqueID valueOf(){
        return new UniqueIdImpl();
    }
    
    @Override
    public long idNumber() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof UniqueID)) return false;
        return Long.compare(idNumber(), ((UniqueID)o).idNumber() ) == 0;
    }

    @Override
    public int hashCode() {
        return hashDouble((double) idNumber());
    }

    @Override
    public String toString() {
        return "UniqueID: "+idNumber();
    }

    @Override
    public int compareTo(UniqueID o) {
        return Long.compare(this.idNumber(), o.idNumber());
    }
    
}
