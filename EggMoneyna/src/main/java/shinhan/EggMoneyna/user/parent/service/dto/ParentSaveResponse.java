package shinhan.EggMoneyna.user.parent.service.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParentSaveResponse {
    private Long id;
    private String parentId;
    private Long accountNumber;
    private String Role;
}
