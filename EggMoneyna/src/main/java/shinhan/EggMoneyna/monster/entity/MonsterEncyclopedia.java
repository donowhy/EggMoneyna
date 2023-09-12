package shinhan.EggMoneyna.monster.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shinhan.EggMoneyna.users.entity.Users;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MonsterEncyclopedia {

    @Id
    @GeneratedValue
    private Long id;
    private Boolean SOL;
    private Boolean MOLI;
    private Boolean RINO;
    private Boolean SHOO;
    private Boolean DOREMI;
    private Boolean LULULALA;
    private Boolean PLI;
    private Boolean LAY;

    @OneToOne
    @JoinColumn(name = "users_id")
    @JsonIgnore
    private Users users;

    public void setSol(Boolean SOL) {
        this.SOL = SOL;
    }

    public void setMoli(boolean MOLI) {
        this.MOLI = MOLI;
    }

    public void setRino(boolean RINO) {
        this.RINO = RINO;
    }
    public void setShoo(boolean SHOO) {
        this.SHOO = SHOO;
    }
    public void setDoremi(boolean DOREMI) {
        this.DOREMI = DOREMI;
    }public void setLululala(boolean LULULALA) {
        this.LULULALA = LULULALA;
    }public void setPli(boolean PLI) {
        this.PLI = PLI;
    }public void setLay(boolean LAY) {
        this.LAY = LAY;
    }
}
