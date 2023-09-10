package shinhan.EggMoneyna.monster.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shinhan.EggMoneyna.monster.entity.MonsterEncyclopedia;
import shinhan.EggMoneyna.monster.repository.MonsterEncyclopediaRepository;
import shinhan.EggMoneyna.users.entity.Users;
import shinhan.EggMoneyna.users.repository.UsersRepository;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MonsterEncyclopediaService {

    private final MonsterEncyclopediaRepository monsterEncyclopediaRepository;
    private final UsersRepository usersRepository;

}
