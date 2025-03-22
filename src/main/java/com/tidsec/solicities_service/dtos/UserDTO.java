package com.tidsec.solicities_service.dtos;
import lombok.*;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private Long idUser;
    private LocalDateTime dateCreate = LocalDateTime.now();
    private String identification;
    private String name;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private boolean enabled;
    private Integer status = 1;
}
