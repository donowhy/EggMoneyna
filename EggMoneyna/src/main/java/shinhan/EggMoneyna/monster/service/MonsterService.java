package shinhan.EggMoneyna.monster.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shinhan.EggMoneyna.monster.dto.MonsterResponseDto;
import shinhan.EggMoneyna.monster.dto.MonsterSaveRequestDto;
import shinhan.EggMoneyna.monster.dto.MonsterSaveResponseDto;
import shinhan.EggMoneyna.monster.dto.MonsterUpdateResponseDto;
import shinhan.EggMoneyna.monster.entity.Monster;
import shinhan.EggMoneyna.monster.entity.enumType.MonsterStatus;
import shinhan.EggMoneyna.monster.repository.MonsterRepository;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class MonsterService {

    private MonsterRepository monsterRepository;



    // CREATE
    public MonsterSaveResponseDto save(MonsterSaveRequestDto monsterSaveRequestDto) {
        Monster monster = Monster.builder()
                .name(Monster.getRandomMong())
                .nickName(monsterSaveRequestDto.getNickName())
                .status(MonsterStatus.Egg)
                .benefit(monsterSaveRequestDto.getBenefitEnum())
                .build();

        Monster savedMonster = monsterRepository.save(monster);

        return MonsterSaveResponseDto.of(savedMonster);
    }

    // READ
    public MonsterResponseDto findById(Long id) {
        Monster monster = monsterRepository.findById(id).orElse(null);


        return MonsterResponseDto.of(monster);
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
}
