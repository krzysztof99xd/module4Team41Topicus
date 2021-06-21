package exceptions;

public class WrongFileFormatException extends  Exception {
    public WrongFileFormatException(String msg){
        super(msg);
    }
}
