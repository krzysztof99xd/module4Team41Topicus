import com.prowidesoftware.swift.model.field.Field61;
import com.prowidesoftware.swift.model.mt.mt9xx.MT940;
import lendaryModel.Balance;
import lendaryModel.Parser;
import lendaryModel.Transaction;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParserTest {
    File file;
    Balance balance;
    Transaction transaction;
    MT940 mt940;
    Parser parser;

    public ParserTest() throws IOException {
        file = new File("src/main/java/lendaryDAO/mt940-npp-sample-file.940");
        balance = new Balance();
        transaction = new Transaction();
        mt940 = new MT940(file);
        parser = new Parser();
    }
    @Test
    public void checkFirst3Fields() throws IOException {
     assertNotNull(mt940.getField20().getValue());
     assertNotNull(mt940.getField25().getValue());
     assertNotNull(mt940.getField28C().getValue());
    }
    @Test
    public void checkField60(){
        balance = parser.parseField60(mt940.getField60F().getValue(), balance);
        assertNotNull(balance.getStatement_date());
        assertEquals("2019-02-05", balance.getStatement_date().toString());
        assertEquals(0.00, balance.getFirst_balance().getAmount());
    }
    @Test
    public void checkField61(){
        for (Field61 f61 : mt940.getField61()) {
            Transaction transaction = new Transaction();
            transaction = parser.parseField61(f61.getValue(), transaction);
            balance.addTransaction(transaction);
            assertNotNull(transaction.getAmount());
        }
    }
    @Test
    public void checkField62F(){
        balance = parser.parseField62F(mt940.getField62F().getValue(), balance);
        assertNotNull(balance.getBooked_balance());
    }
    @Test
    public void checkField64(){
        balance = parser.parseField64(mt940.getField64().getValue(), balance);
        assertNotNull(balance.getFinal_balance());
        assertEquals("2019-02-05", balance.getClosing_date().toString());
    }
}
