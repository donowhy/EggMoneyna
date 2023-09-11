package shinhan.EggMoneyna.monster.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import shinhan.EggMoneyna.monster.dto.MonsterResponseDto;
import shinhan.EggMoneyna.monster.dto.MonsterSaveRequestDto;
import shinhan.EggMoneyna.monster.dto.MonsterSaveResponseDto;
import shinhan.EggMoneyna.monster.dto.MonsterUpdateResponseDto;
import shinhan.EggMoneyna.monster.entity.Monster;
import shinhan.EggMoneyna.monster.entity.MonsterEncyclopedia;
import shinhan.EggMoneyna.monster.entity.enumType.Feel;
import shinhan.EggMoneyna.monster.entity.enumType.MonsterStatus;
import shinhan.EggMoneyna.monster.repository.MonsterEncyclopediaRepository;
import shinhan.EggMoneyna.monster.repository.MonsterRepository;
import shinhan.EggMoneyna.users.dto.UpdateRequestDto;
import shinhan.EggMoneyna.users.entity.Users;
import shinhan.EggMoneyna.users.repository.UsersRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class MonsterService {

    private final UsersRepository usersRepository;
    private final MonsterRepository monsterRepository;
    private final MonsterEncyclopediaRepository monsterEncyclopediaRepository;

    // CREATE
    public MonsterSaveResponseDto save(MonsterSaveRequestDto monsterSaveRequestDto, Long id) {

        Users users = usersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

        log.info("name={}", Monster.getRandomMong());

        Monster monster = Monster.builder()
                .name(Monster.getRandomMong())
                .nickName(monsterSaveRequestDto.getNickName())
                .status(MonsterStatus.Egg)
                .benefit(monsterSaveRequestDto.getBenefitEnum())
                .user(users)
                .feel(Feel.Noting)
                .build();

        Monster savedMonster = monsterRepository.save(monster);

        users.setCntMonsters(users.getCntMonsters() + 1);

        return MonsterSaveResponseDto.of(savedMonster);
    }

    // READ
    public List<MonsterResponseDto> findById(Long id) {
        Users users = usersRepository.findById(id).orElseThrow();
        List<Monster> monsters = users.getMonsters();

        List<MonsterResponseDto> monsterResponseDtos = new ArrayList<>();

        for (Monster monster : monsters) {
            monsterResponseDtos.add(
                    MonsterResponseDto.of(monster)
            );
        }
        return monsterResponseDtos;
    }


    // UPDATE
    public MonsterUpdateResponseDto update(Long id, String nickName) {
        Monster monster = monsterRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Invalid monster Id: " + id));

        monster.setNickName(nickName);

        return MonsterUpdateResponseDto.of(monster);
    }

    // DELETE
    public String deleteById(Long id) {
        monsterRepository.deleteById(id);
        return "삭제 성공";
    }

    public String registration(Long id, Monster monster){
        Users users = usersRepository.findById(id).orElseThrow();
        if(monster.getExp() <= 1000){
            throw new RuntimeException();
        }
        MonsterEncyclopedia monsterEncyclopedia = users.getMonsterEncyclopedia();
        if(monster.getName().getKey().equals("sol")){
            monsterEncyclopedia.setSol(true);
        }
        if(monster.getName().getKey().equals("moli")){
            monsterEncyclopedia.setMoli(true);
        }
        if(monster.getName().getKey().equals("rino")){
            monsterEncyclopedia.setRino(true);
        }
        if(monster.getName().getKey().equals("shoo")){
            monsterEncyclopedia.setShoo(true);
        }
        if(monster.getName().getKey().equals("doremi")){
            monsterEncyclopedia.setDoremi(true);
        }
        if(monster.getName().getKey().equals("lululala")){
            monsterEncyclopedia.setLululala(true);
        }
        if(monster.getName().getKey().equals("pli")){
            monsterEncyclopedia.setPli(true);
        }
        if(monster.getName().getKey().equals("lay")){
            monsterEncyclopedia.setLay(true);
        }

        return "도감 등록 성공";
    }

}
