//package shinhan.EggMoneyna.monster.dto;
//
//
//import lombok.Builder;
//import shinhan.EggMoneyna.monster.entity.Monster;
//
//
//public class MonsterResponseDto {
//    private String name;
//
//    private String nickName;
//
//    private String status;
//
//    private int point;
//
//    private String feel;
//
//    private int exp;
//
//    private String benefit;
//
//    @Builder
//    public MonsterResponseDto(String name, String nickName, String status, int point, String feel, int exp, String benefit) {
//        this.name = name;
//        this.nickName = nickName;
//        this.status = status;
//        this.point = point;
//        this.feel = feel;
//        this.exp = exp;
//        this.benefit = benefit;
//    }
//
//    public static MonsterResponseDto of(Monster monster) {
//        return MonsterResponseDto.builder()
//                .name(monster.getName().getTitle()) // Assuming the name in Monster is of type ShinhanMong and has a getTitle method.
//                .nickName(monster.getNickName())
//                .status(monster.getStatus().toString()) // Assuming status is an Enum
//                .point(monster.getPoint())
//                .feel(monster.getFeel().toString()) // Assuming feel is an Enum
//                .exp(monster.getExp())
//                .benefit(monster.getBenefit().toString()) // Assuming benefit is an Enum
//                .build();
//    }
//
//}
