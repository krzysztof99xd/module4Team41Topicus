package lendaryModel;

import com.prowidesoftware.swift.model.mt.mt9xx.MT940;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    //field20 = transaction_reference_number
    //field25 =  account_identification
    //field28C = Sequence_number


    /**
     * Parses the MT490 file for the field 60F part
     * @param field60F
     * @param balance
     * @return the balance after the transaction after field 60F is parsed
     */
    public Balance parseField60(String field60F, Balance balance){
     

        balance.setStatement_date(getDate(field60F.substring(1,7)));
        balance.setFirst_balance(new Money(field60F.substring(7, 10), getFloat((field60F).substring(10)), field60F.substring(0).charAt(0)));

        return balance;
    }
    /**
     * Parses the MT490 file for the field 61 part
     * @param field61
     * @param transaction
     * @return all information about the transaction
     */
    public Transaction parseField61(String field61, Transaction transaction){
        int index = 0;
        String value_date = field61.substring(0,6);
        String year  = value_date.substring(0,2);


        String init_amount = field61.substring(11, 16);
        String final_amount = "";
        for(int i =0; i<init_amount.length(); i++){
            if(Character.isDigit(init_amount.charAt(i)) || init_amount.charAt(i) == ','){
                final_amount += init_amount.charAt(i);
            }
        }
        transaction.setValueDate(getDate(field61.substring(0,6)));
        transaction.setEntry_date(getDate(year + field61.substring(6,10)));
        transaction.setAmount(new Money(null,getFloat(final_amount), field61.substring(10,11).charAt(0)));

        if(field61.contains("//")) {
            index = field61.indexOf("//")+2;
            String customer_reference =  field61.substring(index,index+16);
            transaction.setCustomer_reference(customer_reference);
        }
        return transaction;
    }
    /**
     * @return nothing special, no structure for field 86 yet
     */
    public String parseField86(){
        return  "No structure for field 86 :(";
    }

    /**
     * Parses the MT490 file for the field 62F part
     * @param field62F
     * @param balance
     * @return the balance after the field 62F is parsed
     */
    public Balance parseField62F(String field62F, Balance balance){


        balance.setBooked_balance(new Money(field62F.substring(7,10), getFloat(field62F.substring(10)), field62F.substring(0,1).charAt(0)));
        return  balance;
    }

    /**
     * parses the MT490 file for the field 64 part
     * @param field64
     * @param balance
     * @return the balance after the field 64 is parsed
     */
    public  Balance parseField64 (String field64, Balance balance){


        balance.setFinal_balance(new Money(field64.substring(7,10), getFloat(field64.substring(10)),field64.substring(0,1).charAt(0)));
        balance.setClosing_date(getDate(field64.substring(1,7)));

        return  balance;
    }

    /**
     * tells if transaction is safe
     * @param filetoString
     * @return true when it is safe
     */
    public boolean isSafe(String filetoString) {
        if(filetoString.contains("alert(") || ((filetoString.toUpperCase().contains("DROP") || filetoString.toUpperCase().contains("DELETE") || filetoString.toUpperCase().contains("UPDATE")) && filetoString.toUpperCase().contains("FROM"))){
            return false;
        }
        return true;
    }

    /**
     * @param date
     * @return the date in the correct format
     */
    public Date getDate(String date){
        String year = "20"+ date.substring(0,2);
        String month = date.substring(2,4);
        String day = date.substring(4,6);
        return Date.valueOf(year+ "-" + month + "-" + day);
    }


    /**
     * turn a string to float with . not ,
     * @param s string 
     * @return a decimal number
     */
    public Float getFloat(String s){
        String s2 ="";
        for(int i = 0; i< s.length(); i++){
            if(s.charAt(i) == ','){
                s2 +='.';
            }
            else{
                s2 += s.charAt(i);
            }
        }
        return Float.parseFloat(s2+'F');
    }


}
