package shinhan.EggMoneyna.domain.member.service.dto;

import lombok.Getter;

@Getter
public class LoginDto {

    private Boolean isChecked;

    public LoginDto(Boolean isChecked) {
        this.isChecked = isChecked;
    }
}
