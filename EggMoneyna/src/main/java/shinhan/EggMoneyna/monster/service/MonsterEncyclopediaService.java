package shinhan.EggMoneyna.monster.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import shinhan.EggMoneyna.monster.dto.MonsterDetailRequest;
import shinhan.EggMoneyna.monster.dto.MonsterDetailResponse;
import shinhan.EggMoneyna.monster.entity.EncyclopediaDetail;
import shinhan.EggMoneyna.monster.entity.MonsterEncyclopedia;
import shinhan.EggMoneyna.monster.repository.MonsterEncyclopediaRepository;
import shinhan.EggMoneyna.user.child.entity.Child;
import shinhan.EggMoneyna.user.child.repository.ChildRepository;
import shinhan.EggMoneyna.users.entity.Users;
import shinhan.EggMoneyna.users.repository.UsersRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MonsterEncyclopediaService {

    private final MonsterEncyclopediaRepository monsterEncyclopediaRepository;
    private final ChildRepository childRepository;

    private final EncyclopediaDetailService encyclopediaDetailService;
    public void save(Long id){
        Child child = childRepository.findById(id).orElseThrow();

        MonsterEncyclopedia monsterEncyclopedia = MonsterEncyclopedia.builder()
                .worldView("일 년 내내 밤하늘에서 찾아볼 수 있는 작은 곰자리는 북쪽 하늘의 대표적인 별자리로 알려져 있으며, 북극성은 작은 곰자리의 끝에 자리 잡고 있습니다.\\n예로부터 항해자들의 길잡이가 되어주던 북극성을 모티브로 개발된 신한 프렌즈.\\n시대를 앞장서서 도전해 나가는 탐험대의 이야기를 담아 신한이 리드하는 새로운 금융 가치를 이야기하게 될 것입니다.")
                .child(child)
                .build();

        monsterEncyclopediaRepository.saveAndFlush(monsterEncyclopedia);
        List<EncyclopediaDetail> save = encyclopediaDetailService.save(monsterEncyclopedia.getId());
        monsterEncyclopedia.EncyclopediaDetail(save);
        child.setMonsterEcyclopedia(monsterEncyclopedia);
    }

    public MonsterDetailResponse monsterDetail (Long id, MonsterDetailRequest request){
        log.info("monstername={}", request.getChracterName());
        Child child = childRepository.findById(id).orElseThrow();
        List<EncyclopediaDetail> encyclopediaDetails = child.getMonsterEncyclopedia().getEncyclopediaDetails();
        EncyclopediaDetail encyclopediaDetail = null;
        for (EncyclopediaDetail temp : encyclopediaDetails) {
            String englishName = temp.getMongName().replaceAll("[^A-Za-z0-9 ]", "").trim().replaceAll("\\s+", "");

            log.info("englishName={}, request character name={}", englishName, request.getChracterName());

            if(englishName.equalsIgnoreCase(request.getChracterName())){
                encyclopediaDetail = temp;
                break;
            }
        }

        log.info("monster={}", encyclopediaDetail.getMongName());
        return MonsterDetailResponse.builder()
                .chracterName(encyclopediaDetail.getMongName())
                .story(encyclopediaDetail.getMongStory())
                .withMeTime(LocalDate.now().toString())
                .registerDate(encyclopediaDetail.getRegisterDate())
                .build();

    }


}
