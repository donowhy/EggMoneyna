package shinhan.EggMoneyna.user.parent.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyChildsResponse {

    private Long id;
    private String childName;
    private int age;
}
