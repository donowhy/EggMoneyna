package shinhan.EggMoneyna.monster.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shinhan.EggMoneyna.monster.entity.MonsterEncyclopedia;
import shinhan.EggMoneyna.monster.repository.MonsterEncyclopediaRepository;
import shinhan.EggMoneyna.user.child.entity.Child;
import shinhan.EggMoneyna.user.child.repository.ChildRepository;
import shinhan.EggMoneyna.users.entity.Users;
import shinhan.EggMoneyna.users.repository.UsersRepository;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MonsterEncyclopediaService {

    private final MonsterEncyclopediaRepository monsterEncyclopediaRepository;
    private final ChildRepository childRepository;

    public void setMonsterEncyclopedia(Long id){
        Child child = childRepository.findById(id).orElseThrow();

        MonsterEncyclopedia monsterEncyclopedia = MonsterEncyclopedia.builder()
                .DOREMI(false)
                .RINO(false)
                .SHOO(false)
                .SOL(false)
                .PLI(false)
                .LAY(false)
                .MOLI(false)
                .LULULALA(false)
                .child(child)
                .build();

        monsterEncyclopediaRepository.save(monsterEncyclopedia);

        child.setMonsterEcyclopedia(monsterEncyclopedia);
    }
}
