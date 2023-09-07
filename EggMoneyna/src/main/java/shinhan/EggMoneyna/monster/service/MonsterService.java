package shinhan.EggMoneyna.monster.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import shinhan.EggMoneyna.monster.dto.MonsterResponseDto;
import shinhan.EggMoneyna.monster.dto.MonsterSaveRequestDto;
import shinhan.EggMoneyna.monster.dto.MonsterSaveResponseDto;
import shinhan.EggMoneyna.monster.dto.MonsterUpdateResponseDto;
import shinhan.EggMoneyna.monster.entity.Monster;
import shinhan.EggMoneyna.monster.entity.enumType.Feel;
import shinhan.EggMoneyna.monster.entity.enumType.MonsterStatus;
import shinhan.EggMoneyna.monster.repository.MonsterRepository;
import shinhan.EggMoneyna.users.dto.UpdateRequestDto;
import shinhan.EggMoneyna.users.entity.Users;
import shinhan.EggMoneyna.users.repository.UsersRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class MonsterService {

    private final UsersRepository usersRepository;
    private final MonsterRepository monsterRepository;

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

        return MonsterSaveResponseDto.of(savedMonster);
    }

    // READ
    public MonsterResponseDto findById(Long id, Long myId) {
        Users users = usersRepository.findById(myId).orElseThrow();
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
