/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physobjects.implementation;

import physobjects.interfaces.WithStowLoc;
import java.util.List;
import Values.implementation.Values;
import Values.interfaces.BoundingBox;
import physobjects.interfaces.Body;
import Values.interfaces.Mass;
import physobjects.interfaces.Stowage;
import Values.interfaces.StowageLocation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import physobjects.interfaces.WithForm;
import physobjects.interfaces.WithUniqueID;
import static com.google.common.base.Preconditions.*;
import com.google.common.base.Predicates;
import Values.implementation.Values.*;
import Values.interfaces.UniqueID;

/**
 *
 * @author abg667
 */// 
final class Bounded3DimStackImpl<E> implements Stowage<E> {
    public ArrayList<ArrayList<ArrayList<E>>> staples;
    private Mass emptyMass = Values.ZERO_MASS;
    private Mass maxMass = Values.ZERO_MASS;
    private BoundingBox BB = Values.ZERO_BB; 
    private final int bays,rows,tiers;
    private final Object stowageReference;


   
    //CONSTRUCTOR
    public Bounded3DimStackImpl(int bays,int rows,int tiers, List<E> elem, Object o){
       checkArgument(true);
       stowageReference = o;
       this.bays = bays;
       this.rows = rows;
       this.tiers = tiers;
       int y =0;
        staples = new ArrayList<ArrayList<ArrayList<E>>>();
        for(int i = 0;i < bays;i++){
            staples.add(new ArrayList<ArrayList<E>>());
            for (int j = 0; j < rows; j++) {
                staples.get(i).add(new ArrayList<E>());
                for (int k = 0; k < tiers; k++) {
                    E elemE = elem.get(y);
                    ((WithStowLoc)elemE).setLoc(stowageReference, Values.stowageLocation(i, j, k));
                    staples.get(i).get(j).add(elemE);
                    y++;
                    
                }
            }
        }
    }

    public static Bounded3DimStackImpl createStowage(int bays,int rows,int tiers,Object caller){
        
        return new Bounded3DimStackImpl(bays,rows,tiers,null,caller);
    }
    
    @Override
    public boolean load(int bayNo, int rowNo, E elem) {
        int tier;
        for (E e : staples.get(bayNo).get(rowNo)) {
            if( ((WithForm)e).isFree()){
                   tier =  ((WithStowLoc)e).loc().tier();
                   return load(bayNo, rowNo, tier, elem);       
                }
        }
        return false;
    }
    @Override
    public boolean load(int bayNo, int rowNo, int tierNo, E elem) {
        
        checkElementIndex(rowNo, rows);
        checkElementIndex(bayNo, bays);
        checkElementIndex(tierNo, tiers);
        boolean loaded = false;
        
            if(((WithForm)staples.get(bayNo).get(rowNo).get(tierNo)).isFree()){
                ((WithStowLoc)elem).setLoc(stowageReference, Values.stowageLocation(bayNo, rowNo, tierNo));
                staples.get(bayNo).get(rowNo).set(tierNo,elem);
                loaded = true;
                if(Physobjects.DEBUG){
                    System.out.println(elem.getClass().getSimpleName()+((WithUniqueID)elem).uniqueID()+": Loaded in: b: "+bayNo+" r: "+rowNo+" t: "+tierNo);
                }
            }
            else{
               if(Physobjects.DEBUG){
                    System.out.println(elem.getClass().getSimpleName()+((WithUniqueID)elem).uniqueID()+":  Loaded FAILED in: b: "+bayNo+" r: "+rowNo+" t: "+tierNo);
                }
            }
        return loaded;
    }
    @Override
    public boolean load(E elem) {
        boolean loaded = false;
        for(int i = 0; (i< bays);i++){
            for (int j = 0; (j < rows); j++) {
                    if((tierIsEmpty(i, j) || !(tierIsFull(i, j)))){
                        return load(i,j,elem);
                        
                    }
            }
            
        }
        return loaded;
 
    }
    private boolean isGivenStowageLocationValid(int bayNo, int rowNo, int tierNo){
        return ( (0 <= bayNo) && (bayNo < bays) &&
                 (0 <= rowNo) && (rowNo < rows) &&
                 (0 <= tierNo) && (tierNo < tiers) );
    }
    @Override
    public boolean isEmpty() {
     for (ArrayList<ArrayList<E>> arrayList : staples) {
            for (ArrayList<E> arrayList1 : arrayList) {
                for (E e : arrayList1) {
                    if(!((WithForm)e).isFree())return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean isFull() {
        for (ArrayList<ArrayList<E>> arrayList : staples) {
            for (ArrayList<E> arrayList1 : arrayList) {
                for (E e : arrayList1) {
                    if(((WithForm)e).isFree())return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean tierIsEmpty(int bay, int row) {
        checkElementIndex(row, rows);
        checkElementIndex(bay, bays);
        for (E elem : staples.get(bay).get(row)) {
            if(!((WithForm)elem).isFree())return false;
        }
        return true;
    }

    @Override
    public boolean tierIsFull(int bay, int row) {
        checkElementIndex(row, rows);
        checkElementIndex(bay, bays);
        for (E elem : staples.get(bay).get(row)) {
            if(((WithForm)elem).isFree())return false;
        }
        return true;
        
    }

    @Override
    public boolean contains(Object elem) {
        
        for (ArrayList<ArrayList<E>> arrayList : staples) {
            for (ArrayList<E> arrayList1 : arrayList) {
                for (E e : arrayList1) {
                    if(e.equals(elem))
                        return true;
                }
            }
        }
        
        return false;
    }

    @Override
    public boolean containsAll(Collection<? extends E> coll) {
        if(coll == null) return false;

        for (E e : coll) {
               if(e == null || !contains(e))return false;
        }
        return true;
    }

    @Override
    public E get(StowageLocation stowLoc) {
        int bay, row,tier;
        
        bay  = stowLoc.bay();
        row  = stowLoc.row();      
        tier = stowLoc.tier();
        checkElementIndex(tier, tiers);
        checkElementIndex(row, rows);
        checkElementIndex(bay, bays);
        if(((WithForm) staples.get(bay).get(row).get(tier)).isOccupied()) 
            return staples.get(stowLoc.bay()).get(stowLoc.row()).get(stowLoc.tier());
       return null;
    }
    

    @Override
    public Set<E> getAll() {
        Set<E> set = new HashSet<E>();
        for (ArrayList<ArrayList<E>> arrayList : staples) {
            for (ArrayList<E> arrayList1 : arrayList) {
                for (E e : arrayList1) {
                    if(((WithForm)e).isOccupied()) set.add(e);
                    
                }
            }
        }
        return set;
    }

    @Override
    public StowageLocation locationOf(E elem) {
        for (ArrayList<ArrayList<E>> arrayList : staples) {
            for (ArrayList<E> arrayList1 : arrayList) {
                for (E e : arrayList1) {
                    if(e.equals(elem))
                    return ((WithStowLoc)elem).loc();
                }
            }
        }

        return Values.stowageLocation(-1, -1, -1);
    }

    @Override
    public BoundingBox getBoundingBox() {
        return BB;
    }

    @Override
    public Mass emptyMass() {
      return emptyMass;
    }

    @Override
    public Mass maxMass() {
        return maxMass;
    }
//TODO: ?
    @Override
    public boolean loadAll(Collection<E> coll) {
        boolean loaded = false;
        if(coll.size() <= freeSpace()){
            for (E e : coll) {
                load(e);
            }
            
        loaded = true;
        }
        String output;
        if(loaded){
            output = coll.size()+" Palleten geladen!";
        }else{
            output = "Keine Palleten geladen, nicht genug Platz vorhanden!";    
        }
        
        System.out.println(output);
        return loaded;
    }

    @Override
    public void printStack() {
        String tmpstr = "";
        for (int i = 0; i < bays; i++) {
            tmpstr = tmpstr + "# B:"+i+"-------------------------------------------------- \n";
            for (int j = 0; j < rows; j++) {
                tmpstr = tmpstr + "R:"+j+" [";
                for (int k = 0; k < tiers;k++) {
                   tmpstr = tmpstr +" "+((WithForm)staples.get(i).get(j).get(k)).print()+" "; 
                    
                }
                tmpstr = tmpstr +"],";
                if((j+1)%3==0) tmpstr = tmpstr + "\n";
            }
        }
        System.out.println(tmpstr);
    }
    
    public int freeSpace(){
        int space = 0;

        for (ArrayList<ArrayList<E>> arrayList : staples) {
            for (ArrayList<E> arrayList1 : arrayList) {
                for (E e : arrayList1) {
                    if(((WithForm)e).isFree())
                        space++;
                }
            }
        }
        return space;
    }

    @Override
    public boolean load(StowageLocation loc, E elem) {
        return load(loc.bay(),loc.row(), loc.tier(), elem);
    }

    @Override
    public Mass mass() {
      Mass accu = Values.ZERO_MASS;
        for (ArrayList<ArrayList<E>> arrayList : staples) {
            for (ArrayList<E> arrayList1 : arrayList) {
                for (E e : arrayList1) {
                    accu = accu.add(((Body) e).mass());
                }
            }
  
        }
      
      return accu;
    }


}


