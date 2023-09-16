package shinhan.EggMoneyna.user.follow.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RelationParentChild {
	private String ParentToken;
	private Long pId;
	private String parentName;
	private Long cId;
	private String childName;


}
