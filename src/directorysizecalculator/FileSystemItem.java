
//Defines the package for this class.
package directorysizecalculator;

/**
 *
 * @author Ahoura Minaeian
 */

//Defines the public, abstract base class for all file system entries.
public abstract class FileSystemItem {
    protected String name;
    //Protected field to store the item's name accessible by subclasses.
    protected Directory parent;
    //Protected field referencing the item's containing directory.
    
    //Constructor Used by subclasses to initialize the common fields (name and parent).
    public FileSystemItem(String name, Directory parent) {
        this.name = name;
        this.parent = parent;
    }
    
    //Public method to retrieve the item's name using Getter Method.
    public String getName() {
        return name;
    }
    
    //Public method to retrieve the item's parent directory reference using Getter Method
    public Directory getParent() {
        return parent;
    }
    
    //Allows the parent directory reference to be changed, necessary when moving an item using Setter Method
    public void setParent(Directory parent) {
        this.parent = parent;
        //Sets the new value for the parent field.
    }
    
    //Declares an Abstract method for getting the size.
    public abstract long getSize();
}