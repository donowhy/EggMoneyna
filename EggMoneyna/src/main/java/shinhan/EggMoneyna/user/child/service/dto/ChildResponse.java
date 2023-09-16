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
    private String childName;

    private int pocketMoney;

    private int limitMoney;

    private int pocketMoneyDate;

    private Account account;

    private Monster monster;

    private int cntMonsters;

    private MonsterEncyclopedia monsterEncyclopedia;

    private List<WishBox> wishBoxes;

    private String firebaseToken;

}
