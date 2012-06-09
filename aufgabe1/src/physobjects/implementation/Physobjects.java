/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physobjects.implementation;

import Values.implementation.Values;
import Values.interfaces.StowageLocation;
import physobjects.interfaces.Container;
import physobjects.interfaces.Pallet;
import physobjects.interfaces.Stowage;

/**
 *
 * @author abg667
 */
public final class Physobjects {

    private Physobjects(){}
    // CONTAINER OBJECTS
    public static Container container(){
        return ContainerImpl.createContainer(); 
    }
    public static Container nullContainer(){
        return NullContainer.nullContainer();
    }
    public static Container nonContainer(){
        return NonContainer.nonContainer();
    }
    
    //PALLET OBJECTS
    public static Pallet pallet(){
        return PalletImpl.pallet();
    }
    public static Pallet nullPallet(){
        return NullPallet.nullPallet();
    }
    public static Pallet nonPallet(){
        return NonPallet.nonPallet();
    }
    //STOWAGE OBJECTS
    public static Stowage<Pallet> palletStowage(){
        return new Bounded3DimStackImpl<Pallet>(1,9,3);
    }
    
    public static void main(String[] args) {
        Container a = container();
        StowageLocation loc = Values.stowageLocation(0, 0, 0);
        a.load(0,0,pallet());
        a.load(0,0,pallet());
        a.load(0,0,pallet());
        a.load(0,0,pallet());
        System.out.println(a.get(loc));
        System.out.println(pallet());
        
    }
}
