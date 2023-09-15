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
	private Long pId;
	private String parentId;
	private Long cId;
	private String childId;

}
