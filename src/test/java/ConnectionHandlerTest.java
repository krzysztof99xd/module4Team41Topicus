import database.ConnectionHandler;
import org.testng.annotations.Test;
import org.junit.jupiter.api.*;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ConnectionHandlerTest {

    ConnectionHandler connectionHandler = new ConnectionHandler();

    public ConnectionHandlerTest() throws SQLException {
    }

    @BeforeEach
    public void setUp() throws SQLException {
        connectionHandler = new ConnectionHandler();
    }

    @Test
    @Order(4)
    public void checkDBName(){
        assertNotNull(connectionHandler.getDbName());
    }

    @Test
    @Order(2)
    public void checkPassword(){
        assertNotNull(connectionHandler.getPassword());
    }

    @Test
    @Order(3)
    public void checkUrl(){
        assertNotNull(connectionHandler.getUrl());
    }

    @Test
    @Order(1)
    public void checkConnection() throws SQLException {
        assertNotNull(connectionHandler.getConnection());
    }

}
