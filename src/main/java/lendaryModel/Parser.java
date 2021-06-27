package lendaryModel;

import com.prowidesoftware.swift.model.mt.mt9xx.MT940;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    //field20 = transaction_reference_number
    //field25 =  account_identification
    //field28C Sequence_number

    public Balance parseField60(String field60F, Balance balance){
        //opening balance

//        char debit_or_credit_mark= field60F.substring(0).charAt(0);
//        String statement_date = field60F.substring(1,7);
//        String currency = field60F.substring(7, 10);
//        String amount  = field60F.substring(10);

        balance.setStatement_date(getDate(field60F.substring(1,7)));
        balance.setFirst_balance(new Money(field60F.substring(7, 10), getFloat((field60F).substring(10)), field60F.substring(0).charAt(0)));

        return balance;
    }

    public Transaction parseField61(String field61, Transaction transaction){
        int index = 0;
        String value_date = field61.substring(0,6);
        String year  = value_date.substring(0,2);

//        String entry_date = field61.substring(6,10);
//        char debit_credit  = field61.substring(10,11).charAt(0);

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

    public String parseField86(){
        return  "No structure for field 86 :(";
    }
    public Balance parseField62F(String field62F, Balance balance){
//Booked funds

//        String debit_credit = field62F.substring(0,1);
//        String balance_date = field62F.substring(1,7);
//        String currency = field62F.substring(7,10);
//        String booked_amount = field62F.substring(10);

        balance.setBooked_balance(new Money(field62F.substring(7,10), getFloat(field62F.substring(10)), field62F.substring(0,1).charAt(0)));
        return  balance;
    }

    /**
     * 
     * @param field64
     * @param balance
     * @return
     */
    public  Balance parseField64 (String field64, Balance balance){
        //available funds

//        char debit_credit = field64.substring(0,1).charAt(0);
//        String final_date = field64.substring(1,7);
//        String currency = field64.substring(7,10);
//        String available_amount = field64.substring(10);

        balance.setFinal_balance(new Money(field64.substring(7,10), getFloat(field64.substring(10)),field64.substring(0,1).charAt(0)));
        balance.setClosing_date(getDate(field64.substring(1,7)));

        return  balance;
    }
    public boolean isSafe(String filetoString) {
        if(filetoString.contains("alert(") || ((filetoString.toUpperCase().contains("DROP") || filetoString.toUpperCase().contains("DELETE") || filetoString.toUpperCase().contains("UPDATE")) && filetoString.toUpperCase().contains("FROM"))){
            return false;
        }
        return true;
    }

    public Date getDate(String date){
        String year = "20"+ date.substring(0,2);
        String month = date.substring(2,4);
        String day = date.substring(4,6);
        return Date.valueOf(year+ "-" + month + "-" + day);
    }
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
