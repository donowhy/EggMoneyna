package shinhan.EggMoneyna.jwt;


import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsersInfo {

    private Long id;
    private String username;
}
