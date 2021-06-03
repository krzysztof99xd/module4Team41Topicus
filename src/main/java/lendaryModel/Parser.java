package lendaryModel;

import com.prowidesoftware.swift.model.mt.mt9xx.MT940;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    public List<String> parseField60F(String field60F){
      List<String> result = new ArrayList<>();

        String debit_credit  = field60F.substring(0);
        String statement_date = field60F.substring(1,7);
        String currency = field60F.substring(7, 10);
        String amount  = field60F.substring(10);

        result.add(debit_credit);
        result.add(statement_date);
        result.add(currency);
        result.add(amount);
        return result;
    }

    public List<String> parseField61(String field61){
        int index = 0;
        List<String> result = new ArrayList<>();

        String value_date = field61.substring(0,6);
        String entry_date = field61.substring(6,10);
        String debit_credit  = field61.substring(10,11);
        String amount = field61.substring(11, 16);

        result.add(value_date);
        result.add(entry_date);
        result.add(debit_credit);
        result.add(amount);

        if(field61.contains("//")) {
            index = field61.indexOf("//")+2;
            String customer_reference =  field61.substring(index,index+16);
            result.add(customer_reference);
        }

        return result;
    }

    public String parseField86(){
        return  "No structure for field 86 :(";
    }
    public List<String> parseField2F(String field62F){

        List<String> result = new ArrayList<>();

        String debit_credit = field62F.substring(0,1);
        String balance_date = field62F.substring(1,7);
        String currency = field62F.substring(7,10);
        String amount = field62F.substring(10);

        result.add(debit_credit);
        result.add(balance_date);
        result.add(currency);
        result.add(amount);

        return  result;
    }

    public  List<String> parseField64 (String field64){
        List<String> result = new ArrayList<>();

        String debit_credit = field64.substring(0,1);
        String balance_date = field64.substring(1,7);
        String currency = field64.substring(7,10);
        String amount = field64.substring(10);

        result.add(debit_credit);
        result.add(balance_date);
        result.add(currency);
        result.add(amount);

        return  result;
    }
}
