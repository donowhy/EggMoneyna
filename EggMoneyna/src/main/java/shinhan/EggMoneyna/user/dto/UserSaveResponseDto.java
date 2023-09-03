package shinhan.EggMoneyna.user.dto;

import lombok.Builder;

public class UserSaveResponseDto {
    private String success;

    @Builder
    public UserSaveResponseDto(String success) {
        this.success = success;
    }
}
