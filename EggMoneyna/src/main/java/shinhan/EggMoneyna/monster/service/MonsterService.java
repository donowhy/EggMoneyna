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
import shinhan.EggMoneyna.user.child.entity.Child;
import shinhan.EggMoneyna.user.child.repository.ChildRepository;
import shinhan.EggMoneyna.users.dto.UpdateRequestDto;
import shinhan.EggMoneyna.users.entity.Users;
import shinhan.EggMoneyna.users.repository.UsersRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class MonsterService {

    private final ChildRepository childRepository;
    private final MonsterRepository monsterRepository;
    private final MonsterEncyclopediaRepository monsterEncyclopediaRepository;

    // CREATE
    public MonsterSaveResponseDto save(MonsterSaveRequestDto monsterSaveRequestDto, Long id) {

        Child child = childRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

        log.info("name={}", Monster.getRandomMong());

        Monster monster = Monster.builder()
                .name(Monster.getRandomMong())
                .status(MonsterStatus.Egg)
                .benefit(monsterSaveRequestDto.getBenefitEnum())
                .child(child)
                .feel(Feel.Noting)
                .build();

        Monster savedMonster = monsterRepository.save(monster);

        child.setCntMonsters(child.getCntMonsters() + 1);

        return MonsterSaveResponseDto.of(savedMonster);
    }

    // READ
    public List<MonsterResponseDto> findById(Long id) {
        Child child = childRepository.findById(id).orElseThrow();
        List<Monster> monsters = child.getMonsters();

        List<MonsterResponseDto> monsterResponseDtos = new ArrayList<>();

        for (Monster monster : monsters) {
            monsterResponseDtos.add(
                    MonsterResponseDto.of(monster)
            );
        }
        return monsterResponseDtos;
    }


    // DELETE
    public String deleteById(Long id) {
        monsterRepository.deleteById(id);
        return "삭제 성공";
    }

    public String registration(Long id, Monster monster) {
        Child child = childRepository.findById(id).orElseThrow();
        if (monster.getExp() <= 1000) {
            throw new RuntimeException();
        }

        MonsterEncyclopedia monsterEncyclopedia = child.getMonsterEncyclopedia();

        try {
            String monsterNameKey = monster.getName().getKey();
            String methodName = "set" + monsterNameKey.substring(0, 1).toUpperCase() + monsterNameKey.substring(1).toLowerCase();

            Method method = MonsterEncyclopedia.class.getMethod(methodName, boolean.class);
            Method getMethod = MonsterEncyclopedia.class.getMethod("get" + methodName.substring(3));

            Boolean currentStatus = (Boolean) getMethod.invoke(monsterEncyclopedia);
            if (currentStatus == null || !currentStatus) {
                method.invoke(monsterEncyclopedia, true);
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Monster registration failed", e);
        }

        return "도감 등록 성공";
    }


}
