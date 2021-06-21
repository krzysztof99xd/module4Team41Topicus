import lendaryModel.Analytics;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnalyticsTest {
    Analytics an;
    public AnalyticsTest(){
        an = new Analytics("testAccount");
    }
    
    @Test
    public void checkSetFirst(){
        Date date = new Date();
        long timeInMilliSeconds = date.getTime();
        java.sql.Date date1 = new java.sql.Date(timeInMilliSeconds);
        an.setfirst(20000,  date1, "EUR");

        assertEquals("testAccount", an.getAccountID());
        assertTrue(an.getDates().contains(date1));
    }

    @Test
    public void checkAddAmount(){
        Date date = new Date();
        long timeInMilliSeconds = date.getTime();
        java.sql.Date date1 = new java.sql.Date(timeInMilliSeconds);

        an.setfirst(2460,  date1, "EUR");

        java.sql.Date date2 = java.sql.Date.valueOf("2019-02-05");
        an.AddAmount(1200,  "D", date2);

        assertTrue(an.getDates().contains(date1));
        assertEquals(2460 - 1200, an.getAmounts().get(1) );
    }
}
