package shinhan.EggMoneyna.user.child.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChildSaveResponse {
    private Long id;
    private String childId;
    private Boolean isGirl;
    private LocalDate birthday;

}
