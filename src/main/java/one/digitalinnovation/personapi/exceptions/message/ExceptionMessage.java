package one.digitalinnovation.personapi.exceptions.message;

import com.google.gson.JsonParser;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionMessage extends RuntimeException {
    private String error;
    private String message;
    private String timestamp;
    private String status;

    @Override
    public String toString() {
        return JsonParser.parseString(
                "{" +
                        "'message':'" + message + "'," +
                        "'erro':'" + error + "'," +
                        "'timestamp':'" + timestamp + "'," +
                        "'status':'" + status + "'" +
                        "}"
        ).toString();
    }
}
