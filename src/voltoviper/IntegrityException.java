package voltoviper;

/**
 *
 * @author Christoph Nebendahl
 */
public class IntegrityException extends Exception{

    public IntegrityException(){

    }

    public IntegrityException(String text){
        super(text);
    }
}
