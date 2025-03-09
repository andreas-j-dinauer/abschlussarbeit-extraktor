package extractor.persistance.source;

import java.util.Map;

public class IssueSource {

    private String id;
    private Map<String, Object> fields;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Object> getFields() {
        return fields;
    }

    public void setFields(Map<String, Object> fields) {
        this.fields = fields;
    }

    public String getSummary() {
        return fields.get("summary").toString();
    }
}