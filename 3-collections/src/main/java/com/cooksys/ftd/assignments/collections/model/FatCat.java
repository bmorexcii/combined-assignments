package com.cooksys.ftd.assignments.collections.model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class FatCat implements Capitalist {


	private String fname;
	private int fsalary;
	private FatCat fowner;
	
	
    public FatCat(String name, int salary) {
        this.fname = name;
        this.fsalary = salary;
    }

    public FatCat(String name, int salary, FatCat owner) {
        this.fname = name;
        this.fsalary = salary;
        this.fowner = owner;
    }


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FatCat other = (FatCat) obj;
		if (fname == null) {
			if (other.fname != null)
				return false;
		} else if (!fname.equals(other.fname))
			return false;
		if (fowner == null) {
			if (other.fowner != null)
				return false;
		} else if (!fowner.equals(other.fowner))
			return false;
		if (fsalary != other.fsalary)
			return false;
		return true;
	}

	/**
     * @return the name of the capitalist
     */
    @Override
    public String getName() {
        return fname;
    }

    /**
     * @return the salary of the capitalist, in dollars
     */
    @Override
    public int getSalary() {
        return fsalary;
    }

    /**
     * @return true if this element has a parent, or false otherwise
     */
    @Override
    public boolean hasParent() {
        if(fowner instanceof Capitalist){
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
        if(fowner instanceof Capitalist){
        	return fowner ;
        }else{
        	return null;
        }
    }
}
