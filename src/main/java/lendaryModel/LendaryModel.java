package lendaryModel;

import com.prowidesoftware.swift.model.mt.mt9xx.MT940;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.IOException;

@XmlRootElement
public class LendaryModel {
    private String id;
    private String name;


    public LendaryModel(String id, String name) throws IOException {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {return name;}
}
