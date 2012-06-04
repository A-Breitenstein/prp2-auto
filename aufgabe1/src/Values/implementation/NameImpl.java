/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Values.implementation;

import Values.interfaces.Name;

/**
 *
 * @author abg667
 */
final class NameImpl extends AbstractValue implements Name {

    private String name;
    
    //CONSTRUCTOR
    private NameImpl(String name){
    this.name = name;
    }
    public static Name valueOf(String name){
        return new NameImpl(name);
    }
    
    @Override
    public String nameString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Name)) return false;
        return nameString().equals(((Name)o).nameString());
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String toString() {
        return "Name: "+nameString();
    }
    
}
