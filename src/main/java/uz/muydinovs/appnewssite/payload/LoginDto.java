package uz.muydinovs.appnewssite.payload;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    @NotNull(message = "Username shouldn't be emplty")
    private String username;

    @NotNull(message = "Pre password is wrong")
    private String password;

}
