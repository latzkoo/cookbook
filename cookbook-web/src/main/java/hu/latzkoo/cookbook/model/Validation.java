package hu.latzkoo.cookbook.model;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class Validation {

    Map<String, String> errors;

    public Validation(HttpServletRequest request, String[] fields) {
        errors = new HashMap<>();

        for (String field : fields) {
            if ((request.getParameter(field) != null && request.getParameter(field).isEmpty()) ||
                    (request.getParameterValues(field) == null) || (request.getParameterValues(field) != null &&
                    request.getParameterValues(field).length <= 0)) {
                errors.put(field, "A mező kitöltése kötelező!");
            }
        }
    }

    public Map<String, String> getErrors() {
        return errors;
    }

}
