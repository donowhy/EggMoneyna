package shinhan.EggMoneyna.user.child.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shinhan.EggMoneyna.account.entity.Account;
import shinhan.EggMoneyna.monster.entity.Monster;
import shinhan.EggMoneyna.monster.entity.MonsterEncyclopedia;
import shinhan.EggMoneyna.wishbox.entity.WishBox;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChildResponse {
    private String childId;

    private int pocketMoney;

    private int limitMoney;

    private int pocketMoneyDate;

    @OneToOne(fetch = FetchType.LAZY)
    private Account account;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Monster monster;

    private int cntMonsters;

    @OneToOne(fetch = FetchType.LAZY)
    private MonsterEncyclopedia monsterEncyclopedia;

    @OneToMany(mappedBy = "child")
    private List<WishBox> wishBoxes;

    private String firebaseToken;

}
