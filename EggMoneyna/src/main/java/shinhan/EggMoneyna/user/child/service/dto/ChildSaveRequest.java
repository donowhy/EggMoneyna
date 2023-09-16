package shinhan.EggMoneyna.user.child.service.dto;

import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Getter
public class ChildSaveRequest {

    private String childId;
    private Boolean isGirl;
    private LocalDate birthday;

}
