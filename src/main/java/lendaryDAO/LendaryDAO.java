package lendaryDAO;
import lendaryModel.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LendaryDAO {

    private static Map<String, LendaryModel> fileProvider = new HashMap<String, LendaryModel>();

    public LendaryDAO()  {
        LendaryModel file1 = null;
        try {
            file1 = new LendaryModel("11", "file1motheedijd");
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileProvider.put("1", file1);
    }
    public static Map<String, LendaryModel> getModel() {
        return fileProvider;
    }

}
