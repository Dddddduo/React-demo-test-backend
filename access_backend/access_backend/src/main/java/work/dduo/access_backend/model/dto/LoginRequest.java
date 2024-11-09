package work.dduo.access_backend.model.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;


}
