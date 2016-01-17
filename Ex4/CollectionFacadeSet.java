import java.util.Set;

/**
 * Wraps an underlying Collection and serves to both simplify its API and
 * give it a common type with the implemented SimpleHashSets.
 * @author ednussi
 */
public class CollectionFacadeSet extends java.lang.Object 
implements SimpleSet {

	// Initializing parameters
    public java.util.Collection<java.lang.String> collection;

    /**
     * Creates a new facade wrapping the specified collection.
     * @param collection - The Collection to wrap.
     */
    public CollectionFacadeSet(java.util.Collection<java.lang.String>
    collection){
        this.collection = collection;
    }
    
    @Override
    public boolean add(java.lang.String newValue) {
    	//making sure is set, otherwise checking if contains
    	if (!(collection instanceof Set)){
    		if(collection.contains(newValue)){
    			return false;
    		}
    	}
		return collection.add(newValue);
    }

    @Override
    public boolean contains(java.lang.String searchVal) {
        return collection.contains(searchVal);
    }

    @Override
    public boolean delete(java.lang.String toDelete) { 
        return collection.remove(toDelete); 
    }
    
    @Override
    public int size() {
        return collection.size();
    }
}
