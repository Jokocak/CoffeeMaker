package edu.ncsu.csc.CoffeeMaker.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Inventory for the coffee maker. Inventory is tied to the database using
 * Hibernate libraries. See InventoryRepository and InventoryService for the
 * other two pieces used for database support.
 *
 * @author Kai Presler-Marshall
 */
@Entity
public class Inventory extends DomainObject {

    /** id for inventory entry */
    @Id
    @GeneratedValue
    private Long                   id;
    /**
     * field to hold all Ingredients in users inventory.
     */
    @OneToMany ( cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    private final List<Ingredient> ingredients;

    /**
     * Empty constructor for Hibernate
     */
    public Inventory () {
        this.ingredients = new ArrayList<Ingredient>();
    }

    /**
     * creates a new inventory given a list of ingredients to base the inventory
     * off of
     *
     * @param ingredients
     *            the ingredients to base the new inventory off of
     */
    public Inventory ( final List<Ingredient> ingredients ) {
        this.ingredients = new ArrayList<Ingredient>();
        for ( final Ingredient igt : ingredients ) {
            this.ingredients.add( igt );
        }
    }
    
    // Constructor for deep copy
    public Inventory( Inventory other ) {
        this.ingredients = new ArrayList<>();
        for ( Ingredient ing : other.getIngredientList() ) {
            this.ingredients.add( new Ingredient( ing ) );
        }
    }

    /**
     * Returns the ID of the entry in the DB
     *
     * @return long
     */
    @Override
    public Long getId () {
        return id;
    }

    /**
     * Set the ID of the Inventory (Used by Hibernate)
     *
     * @param id
     *            the ID
     */
    public void setId ( final Long id ) {
        this.id = id;
    }

    /**
     * Getter method to get the list of ingredients
     *
     * @return List of Ingredients
     */
    public List<Ingredient> getIngredientList () {
        return ingredients;
    }

    /**
     * Returns true if there are enough ingredients to make the beverage.
     *
     * @param r
     *            recipe to check if there are enough ingredients
     * @return true if enough ingredients to make the beverage
     */
    public boolean enoughIngredients ( final Recipe r ) {
        // mapping necessary to avoid quadratic time complexity
        // maps ingredient name to position in ingredients list.
        final TreeMap<String, Integer> idxMap = new TreeMap<String, Integer>();
        for ( int i = 0; i < ingredients.size(); i++ ) {
            idxMap.put( ingredients.get( i ).getName(), i );
        }

        for ( final Ingredient recipeIgt : r.getIngredients() ) {
            final Integer idx = idxMap.get( recipeIgt.getName() );
            if ( idx == null ) {
                return false;
            }
            if ( ingredients.get( idx ).getUnits() < recipeIgt.getUnits() ) {
                return false;
            }
        }
        return true;
    }
    
    public boolean enoughIngredients( final Set<Recipe> recipes ) {
    	// Map ingredient name to position in ingredients list
    	final TreeMap<String, Integer> idxMap = new TreeMap<String, Integer>();
    	for ( int i = 0; i < ingredients.size(); i++ ) {
    		idxMap.put( ingredients.get( i ).getName(), i);
    	}
    	
    	// Iterate through each recipe tracking the ingredients included
    	final TreeMap<String, Integer> neededIngredients = new TreeMap<String, Integer>();
    	for ( Recipe recipe : recipes ) {
    		for ( Ingredient i : recipe.getIngredients() ) {
    			// Update ingredient count or add ingredient to map
    			if (neededIngredients.get(i.getName()) == null) {
    				neededIngredients.put(i.getName(), i.getUnits());
    			} else {
    				neededIngredients.put(i.getName(), neededIngredients.get(i.getName()) + i.getUnits());
    			}
    		}
    	}
    	
    	// Check if we have enough of each ingredient
    	for ( final String ingredient : neededIngredients.keySet() ) {
    		Integer ingredientIdx = idxMap.get( ingredient );
    		
    		// Check if ingredient exists
    		if ( ingredientIdx == null ) {
    			return false;
    		}
    		
    		// Check if we have enough ingredients for this item
    		if ( ingredients.get( ingredientIdx ).getUnits() < neededIngredients.get(ingredient)) {
    			return false;
    		}
    	}
    	
    	// Return true if made down here
    	return true;
    }
    
    public boolean enoughIngredients( final Map<String, Integer> ingredientList ) {
    	// Map ingredient name to position in ingredients list
    	final TreeMap<String, Integer> idxMap = new TreeMap<String, Integer>();
    	for ( int i = 0; i < ingredients.size(); i++ ) {
    		idxMap.put( ingredients.get( i ).getName(), i);
    	}
    	
    	// Check if we have enough of each ingredient
    	for ( final String ingredient : ingredientList.keySet() ) {
    		Integer ingredientIdx = idxMap.get( ingredient );
    		
    		// Check if ingredient exists
    		if ( ingredientIdx == null ) {
    			return false;
    		}
    		
    		// Check if we have enough ingredients for this item
    		if ( ingredients.get( ingredientIdx ).getUnits() < ingredientList.get(ingredient)) {
    			return false;
    		}
    	}
    	
    	// Return true if made down here
    	return true;
    }

    /**
     * Removes the ingredients used to make the specified recipe. Assumes that
     * the user has checked that there are enough ingredients to make
     *
     * @param r
     *            recipe to make
     * @return true if recipe is made.
     */
    public boolean useIngredients(final Set<Recipe> recipes) {
        // Map ingredient name -> total needed
        final TreeMap<String, Integer> idxMap = new TreeMap<>();
        for (int i = 0; i < ingredients.size(); i++) {
            idxMap.put(ingredients.get(i).getName(), i);
        }

        final TreeMap<String, Integer> neededIngredients = new TreeMap<>();
        for (Recipe recipe : recipes) {
            for (Ingredient i : recipe.getIngredients()) {
                neededIngredients.put(i.getName(), neededIngredients.getOrDefault(i.getName(), 0) + i.getUnits());
            }
        }

        // Check sufficiency
        for (final String ingredient : neededIngredients.keySet()) {
            Integer idx = idxMap.get(ingredient);
            if (idx == null || ingredients.get(idx).getUnits() < neededIngredients.get(ingredient)) {
                return false;
            }
        }

        // Deduct
        for (final String ingredient : neededIngredients.keySet()) {
            Integer idx = idxMap.get(ingredient);
            Ingredient invIgt = ingredients.get(idx);
            invIgt.setUnits(invIgt.getUnits() - neededIngredients.get(ingredient));
        }
        return true;
    }
    
    public boolean useIngredients( final Map<String, Integer> ingredientList ) {
        // Map ingredient name -> total needed
        final TreeMap<String, Integer> idxMap = new TreeMap<>();
        for (int i = 0; i < ingredients.size(); i++) {
            idxMap.put(ingredients.get(i).getName(), i);
        }

        // Check sufficiency
        for (final String ingredient : ingredientList.keySet()) {
            Integer idx = idxMap.get(ingredient);
            if (idx == null || ingredients.get(idx).getUnits() < ingredientList.get(ingredient)) {
                return false;
            }
        }

        // Deduct
        for (final String ingredient : ingredientList.keySet()) {
            Integer idx = idxMap.get(ingredient);
            Ingredient invIgt = ingredients.get(idx);
            invIgt.setUnits(invIgt.getUnits() - ingredientList.get(ingredient));
        }
        return true;
    }

    /**
     * Creates a new ingredient and adds it to the inventory
     *
     * @return true if successful, false if not
     */
    public boolean addIngredient ( final String name, final Integer units ) {
        if ( units < 0 ) {
            throw new IllegalArgumentException( "Amount cannot be negative" );
        }
        if ( ingredients.stream().anyMatch( ( igt ) -> name.equals( igt.getName() ) ) ) {
            return false;
        }
        // if we get more time we can add in sorted order! then we don't need a
        // map for our check function

        ingredients.add( new Ingredient( name, units ) );

        return true;
    }

    /**
     * adds stock of the given ingredient to the inventory
     *
     * @param name
     *            the name of the ingredient to add stock to
     * @param units
     *            the units of stock to add
     * @return true if successful, false if not
     */
    public boolean stockIngredient ( final String name, final Integer units ) {
        if ( units < 0 ) {
            throw new IllegalArgumentException( "Amount cannot be negative" );
        }

        for ( final Ingredient igt : ingredients ) {
            if ( igt.getName().equals( name ) ) {
                igt.setUnits( igt.getUnits() + units );
                return true;
            }
        }
        return false;
    }

    /**
     * Returns how many units of an ingredient there are in the inventory
     *
     * @param name
     *            the name of the ingredient we want to find the units of
     * @return the amount of the ingredient in the Inventory
     */
    public int ingredientAmount ( final String name ) {
        for ( final Ingredient ing : ingredients ) {
            if ( ing.getName().equals( name ) ) {
                return ing.getUnits(); // returns # of units of an ingredient in
                                       // the inventory, if it exists
            }
        }
        // if this returns, we know something went wrong
        // Could be replaced with an exception
        return -1;
    }

    /**
     * Returns a string describing the current contents of the inventory.
     *
     * @return String
     */
    @Override
    public String toString () {
        final StringBuffer buf = new StringBuffer();
        for ( final Ingredient i : ingredients ) {
            buf.append( i.getName() );
            buf.append( ": " );
            buf.append( i.getUnits() );
            buf.append( '\n' );
        }
        return buf.toString();
    }

}
