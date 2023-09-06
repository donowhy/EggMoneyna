//package shinhan.EggMoneyna.monster.entity;
//
//
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import shinhan.EggMoneyna.monster.entity.enumType.Benefit;
//import shinhan.EggMoneyna.monster.entity.enumType.Feel;
//import shinhan.EggMoneyna.monster.entity.enumType.MonsterStatus;
//import shinhan.EggMoneyna.monster.entity.enumType.ShinhanMong;
//import shinhan.EggMoneyna.user.entity.User;
//
//import javax.persistence.*;
//import java.util.Random;
//
//@Entity
//@Getter
//@NoArgsConstructor
//public class Monster {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    private Long id;
//
//    private ShinhanMong name;
//
//    private String nickName;
//
//    @Enumerated(EnumType.STRING)
//    private MonsterStatus status;
//
//    private int point;
//
//    @Enumerated(EnumType.STRING)
//    private Feel feel;
//
//    private int exp;
//
//    @Enumerated(EnumType.STRING)
//    private Benefit benefit;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    private User user;
//
//    @Builder
//    public Monster(Long id, ShinhanMong name, String nickName, MonsterStatus status, int point, Feel feel, int exp, Benefit benefit, User user) {
//        this.id = id;
//        this.name = name;
//        this.nickName = nickName;
//        this.status = status;
//        this.point = point;
//        this.feel = feel;
//        this.exp = exp;
//        this.benefit = benefit;
//        this.user = user;
//    }
//
//    public static ShinhanMong getRandomMong() {
//        ShinhanMong[] mongs = ShinhanMong.values();
//        int randomIndex = new Random().nextInt(mongs.length);
//        return mongs[randomIndex];
//    }
//
//    public static MonsterStatus getMonsterStatus(String status) {
//        return MonsterStatus.Egg;
//    }
//
//    public void setNickName(String name) {
//        this.nickName = name;
//    }
//}
