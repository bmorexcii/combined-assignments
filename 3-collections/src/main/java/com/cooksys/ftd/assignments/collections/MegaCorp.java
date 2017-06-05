package com.cooksys.ftd.assignments.collections;

import com.cooksys.ftd.assignments.collections.generators.MegaCorps;
import com.cooksys.ftd.assignments.collections.hierarchy.Hierarchy;
import com.cooksys.ftd.assignments.collections.model.Capitalist;
import com.cooksys.ftd.assignments.collections.model.FatCat;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;
import java.util.stream.Collectors;

public class MegaCorp implements Hierarchy<Capitalist, FatCat> {

	Set<Capitalist> hierarchy = new HashSet<Capitalist>();
	
    /**
     * Adds a given element to the hierarchy.
     * <p>
     * If the given element is already present in the hierarchy,
     * do not add it and return false
     * <p>
     * If the given element has a parent and the parent is not part of the hierarchy,
     * add the parent and then add the given element
     * <p>
     * If the given element has no parent but is a Parent itself,
     * add it to the hierarchy
     * <p>
     * If the given element has no parent and is not a Parent itself,
     * do not add it and return false
     *
     * @param capitalist the element to add to the hierarchy
     * @return true if the element was added successfully, false otherwise
     */
    @Override
    public boolean add(Capitalist capitalist) {
    	//checks if element has already been added to hierarchy or if element is null then return false
        if (has(capitalist) || capitalist == null)
        	return false;
        //if element is not an instance of FatCat and does not have a Parent, return false
        if (!(capitalist instanceof FatCat) && !capitalist.hasParent()) {
        	return false;
        }
        //checks if element has a parent and if element hasn't been added to hierarchy, add element to hierarchy
        if (!has(capitalist.getParent()) && capitalist.hasParent()){
        	add(capitalist.getParent());
        	hierarchy.add(capitalist);
        	return true;
        //if element has a parent and has been added to hierarchy
        }else if (capitalist.hasParent() && has(capitalist.getParent())){
        	return hierarchy.add(capitalist);
        }
        //if element is an instance of FatCat and has not been added to hierarchy, add it to the HashSet
        if (capitalist instanceof FatCat && !has(capitalist)){
        	hierarchy.add(capitalist);
        	return true;
        }else
        	return false;
    }


    /**
     * @param capitalist the element to search for
     * @return true if the element has been added to the hierarchy, false otherwise
     */
    @Override
    public boolean has(Capitalist capitalist) {
    	//checks if hierarchy contains element capitalist and returns boolean value
    	//returns true if statement is true or false if not
        return hierarchy.contains(capitalist);
    }

    /**
     * @return all elements in the hierarchy,
     * or an empty set if no elements have been added to the hierarchy
     */
    @Override
    public Set<Capitalist> getElements() {
    	//return all elements in hierarchy HashSet
    	return new HashSet<>(hierarchy);
    }

    /**
     * @return all parent elements in the hierarchy,
     * or an empty set if no parents have been added to the hierarchy
     */
    @Override
    public Set<FatCat> getParents() {
    	
    	Set<FatCat> parentSet = new HashSet<>();
    	//https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html?is-external=true
    	Iterator<Capitalist> iterator = hierarchy.iterator();
    	//.hasNext() returns true if iterator has more elements
    	while (iterator.hasNext()) {
    		//.next() returns next element in iteration
    		Capitalist capitalist = (Capitalist) iterator.next();
    		//checks if capitalist is an instance of a Class
    		if(capitalist instanceof FatCat)
    			//adds Parent"FatCat" to a HashSet
    			parentSet.add((FatCat) capitalist);
    		}return parentSet;
    }

    /**
     * @param fatCat the parent whose children need to be returned
     * @return all elements in the hierarchy that have the given parent as a direct parent,
     * or an empty set if the parent is not present in the hierarchy or if there are no children
     * for the given parent
     */
    @Override
    public Set<Capitalist> getChildren(FatCat fatCat) {
    	
        Set<Capitalist> gotChildren = new HashSet<>();
        //https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html?is-external=true
        Iterator<Capitalist> iterator = hierarchy.iterator();
        //.hasNext() returns true if iterator has more elements
        while (iterator.hasNext()) {
        	//.next() returns next element in iteration
        	Capitalist capitalist = (Capitalist) iterator.next();
        	//Calls Hierarchical.getParent() and compares object to fatCat
        	if (capitalist.getParent() == fatCat)
        		//add child"capitalist" to a HashSet
        		gotChildren.add(capitalist);
        }return gotChildren;
    }


	/**
     * @return a map in which the keys represent the parent elements in the hierarchy,
     * and the each value is a set of the direct children of the associate parent, or an
     * empty map if the hierarchy is empty.
     */
    @Override
    public Map<FatCat, Set<Capitalist>> getHierarchy() {
    	
    	return getParents().parallelStream().collect(Collectors.toMap(fat -> (FatCat) fat, fat -> getChildren(fat)));
    }

    /**
     * @param capitalist
     * @return the parent chain of the given element, starting with its direct parent,
     * then its parent's parent, etc, or an empty list if the given element has no parent
     * or if its parent is not in the hierarchy
     */
    @Override
    public List<FatCat> getParentChain(Capitalist capitalist) {       
    	//Create parentChain List
    	List<FatCat> parentChain = new LinkedList<FatCat>();
    	
    	//returns empty list if element has no parent
        if (!has(capitalist))
        	return parentChain;
        
        //get parent of element calling Hierarchical.getParent()
        FatCat parent = capitalist.getParent();

        do{//add parents to parentChain List
           	parentChain.add(parent);	
        	parent = parent.getParent();
        }while (parent.hasParent() && has(parent.getParent()));{

        	
        }return parentChain;
    }
}
