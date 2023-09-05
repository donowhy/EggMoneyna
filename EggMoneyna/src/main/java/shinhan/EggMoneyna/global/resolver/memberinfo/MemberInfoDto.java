package shinhan.EggMoneyna.global.resolver.memberinfo;

import lombok.Builder;
import lombok.Getter;
import shinhan.EggMoneyna.domain.member.constant.Role;

@Getter @Builder
public class MemberInfoDto {

    private Long memberId;
    private Role role;

}
