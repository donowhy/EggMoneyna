package shinhan.EggMoneyna.user.follow.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RelationEggMoney {
	private Long pId;
	private String parentId;
	private String childToken;
	private Long cId;
	private String childId;


}
