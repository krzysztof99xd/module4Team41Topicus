package exceptions;

public class WrongFileFormatException extends  Exception {
    /**
     * sends message to user when wrong file is uploaded
     * @param msg
     */
    public WrongFileFormatException(String msg){
        super(msg);
    }
}
