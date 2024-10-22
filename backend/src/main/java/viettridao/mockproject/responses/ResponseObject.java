package viettridao.mockproject.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ResponseObject
 * Version: 1.0
 * Date: 5/21/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/21/2024 diepit9x Create
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseObject {
    private int status;
    private String message;
    private Object data;
}
