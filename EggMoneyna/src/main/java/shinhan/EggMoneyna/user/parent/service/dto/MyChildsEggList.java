package shinhan.EggMoneyna.user.parent.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyChildsEggList {
    private Long childId;
    private String childName;
    private int balance;
}
