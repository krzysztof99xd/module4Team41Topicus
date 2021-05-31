package lendaryModel;

import com.prowidesoftware.swift.model.mt.mt9xx.MT940;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.IOException;

@XmlRootElement
public class LendaryModel {
    private String id;
    private String name;
    private String field;


    public LendaryModel(String id, String name, String field) throws IOException {
        this.id = id;
        this.name = name;
        this.field = field;
    }


    public String getId() {
        return id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {return name;}
}
