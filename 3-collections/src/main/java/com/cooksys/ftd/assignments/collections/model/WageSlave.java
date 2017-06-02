package com.cooksys.ftd.assignments.collections.model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class WageSlave implements Capitalist {

	String wname;
	int wsalary;
	FatCat wowner;
	
    public WageSlave(String name, int salary) {
        this.wname = name;
        this.wsalary = salary;
    }

    public WageSlave(String name, int salary, FatCat owner) {
        this.wname= name;
        this.wsalary = salary;
        this.wowner = owner;
    }


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WageSlave other = (WageSlave) obj;
		if (wname == null) {
			if (other.wname != null)
				return false;
		} else if (!wname.equals(other.wname))
			return false;
		if (wowner == null) {
			if (other.wowner != null)
				return false;
		} else if (!wowner.equals(other.wowner))
			return false;
		if (wsalary != other.wsalary)
			return false;
		return true;
	}

	/**
     * @return the name of the capitalist
     */
    @Override
    public String getName() {
        return wname;
    }

    /**
     * @return the salary of the capitalist, in dollars
     */
    @Override
    public int getSalary() {
        return wsalary;
    }

    /**
     * @return true if this element has a parent, or false otherwise
     */
    @Override
    public boolean hasParent() {
        if(wowner instanceof Capitalist){
        	return true;
        }else{
        	return false;
        }
    }

    /**
     * @return the parent of this element, or null if this represents the top of a hierarchy
     */
    @Override
    public FatCat getParent() {
        if(wowner instanceof Capitalist){
        	return wowner;
        }else{
        	return null;
        }
    }
}
