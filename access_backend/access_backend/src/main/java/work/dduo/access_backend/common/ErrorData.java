package work.dduo.access_backend.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ErrorData {
    private Map<String, List<String>> errors;

    public ErrorData() {
        errors = new HashMap<>();
    }

    public Map<String, List<String>> getErrors() {
        return errors;
    }

    public void addError(String field, String message) {
        errors.computeIfAbsent(field, k -> new ArrayList<>()).add(message);
    }
}
