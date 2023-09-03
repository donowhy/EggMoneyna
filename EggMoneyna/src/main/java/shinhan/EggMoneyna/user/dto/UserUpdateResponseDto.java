package shinhan.EggMoneyna.user.dto;

import lombok.Builder;

public class UserUpdateResponseDto {
    private String nickName;

    @Builder
    public UserUpdateResponseDto(String nickName) {
        this.nickName = nickName;
    }
}
