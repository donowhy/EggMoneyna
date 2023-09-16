package shinhan.EggMoneyna.monster.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shinhan.EggMoneyna.monster.entity.EncyclopediaDetail;
import shinhan.EggMoneyna.monster.entity.MonsterEncyclopedia;
import shinhan.EggMoneyna.monster.repository.EncyclopediaDetailRepository;
import shinhan.EggMoneyna.monster.repository.MonsterEncyclopediaRepository;
import shinhan.EggMoneyna.user.child.entity.Child;
import shinhan.EggMoneyna.user.child.repository.ChildRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EncyclopediaDetailService {

    private final EncyclopediaDetailRepository encyclopediaDetailRepository;
    private final ChildRepository childRepository;
    public List<EncyclopediaDetail> save (Long id){

        String[] MONG_NAMES = {
                "북극성의 여향 작가 `쏠(SOL)`",
                "식물 카페의 느긋한 사장님 ‘몰리 (MOLI)’",
                "바다 탐험가 ‘파도잡이 무피(MUPI)’",
                "숲속의 천재 과학자 ‘리프(LEAF)’",
                "산속의 명상가 ‘연못의 윈디(WINDI)’",
                "사막의 무한 도전자 ‘모래바람 루키(ROOKIE)’",
                "구름 위의 자유로운 화가 ‘하늘 그리는 리린(RIRIN)’",
                "바위산의 힘센 전사 ‘바위심장 스톤(STONE)’"
        };

        String[] MONG_STORIES = {
                "우주 곳곳을 누비며 여행을 즐기는 북극성의 용감한 트래블러 쏠!\n 쏠의 블로그 [걸어서 우주 속으로]는 얼마 전 ‘여행할 때 꼭 참고하는 블로그 1위’이고\n그동안의 여행기를 담은 책 [먹고 사랑하고 여행하라]는 ‘여행서적 부문 베스트셀러’라고 합니다.\n모험을 사랑하는 프로 여행작가 쏠의 이번 행선지는 다름 아닌 지구!\n다른 별들과는 달리, 유난히 푸르게 빛나는 지구가 늘 궁금했다는데요.여행과 모험을 사랑하는 쏠의 지구 여행기, 궁금하지 않나요?",
                "항상 느긋하고 세상 태평한 식물 집사 두더지 몰리!\n몸과 마음을 가꾸기 위해 필라테스와 요가도 수준급이고 유기농 음식만 먹는다고 하는데요,\n광합성을 하며 명상을 할 때 유난히 둥그래 보이는 몸매 때문에 물개로 종종 오해를 받는다고 합니다.\n특히 식물 키우기에 일가견이 있는 몰리는 최근 제주의 오름 근처에 식물 카페를 오픈했어요.\n몰리가 직접 내려주는 수준급 핸드드립 커피를 마시며 진정한 휴식을 즐기러 가보는 것은 어떨까요?",
                "미스터리 아티스트 `리노(RINO)`", "조그만 몸집의 꼬마 아티스트 리노!\n늘 무언가를 조용히 그리곤 있으며, 'Bank씨'라는 예명으로 골목에 그래피티를 남기는 예술가로도 유명해지고 있어요.\n주말엔 미술관을 찾아 영감을 얻으며, 그림을 통해 세상 사람들을 행복하게 하는 꿈을 키우고 있죠.\n리노의 꿈이 이루어지길 기대해 봅니다!",
                "수줍은 디지털 크리에이터 `슈(SHOO)'", "소심하면서도 말수가 적은 슈, 하지만 SNS에서는 활발한 부캐로 활약 중이에요!\n혼자서 근사한 음식을 차리며 먹방을 즐기는 슈, 그의 꿈은 수줍음을 극복하고 크리에이터로 성장하는 것이랍니다.\n친구들에게 희망과 위안을 주는 슈의 콘텐츠, 계속 지켜봅시다!",
                "We Will Rock you! 3인조 락 밴드 `도레미(DO RE MI)'", "기타 실력자 '도', 파워 보컬 '레', 드러머 '미'로 구성된 '도레미 밴드'의 세쌍둥이!\n세계적으로도 주목받고 있는 이들은 음악 제작에도 도전하려고 하지만 아직은 고민 중이에요.\n힙합 부엉이 레이와의 콜라보로 새로운 앨범을 준비 중이랍니다.\n기대가 높아지네요!",
                "즐겁고 행복한 물개 쌍둥이 루루와 라라!\n바다를 사랑하며, 태닝을 즐기고, 서핑을 하며 물놀이를 즐기는 이들은 바다의 자유로운 영혼을 가지고 있어요.\n친구들에게는 조개껍데기 목걸이를 선물하며, 오늘도 열심히 트레이닝하고 있답니다.\n루루라라의 바다 모험, 기대되지 않나요?",
                "고양이와 닮은 라쿤 플리!\n성격이 좋아서 가끔 오해받기도 하지만, 낙천적으로 그런 건 금방 잊어버리는 편이에요.\n고민 상담도 잘 들어주며, 쏠이 지구에 온 것을 기념해 여행 가이드도 해준 담백한 친구랍니다.\n플리의 유쾌한 모험, 함께 해볼까요?",
                "쿨하게 말을 내뱉는 힙합 부엉이 레이!\n다정하면서도 솔직한 성격으로, 많은 이들이 친구가 되고 싶어해요.\n스트릿 댄스를 즐기며, 최근에는 '도레미 밴드'와 콜라보하여 앨범을 프로듀싱하고 있답니다.\n레이와 도레미 밴드의 환상적인 조화, 기대하시죠!"
        };

        List<EncyclopediaDetail> ed = new ArrayList<>();

        Child child = childRepository.findById(id).orElseThrow();
        MonsterEncyclopedia monsterEncyclopedia = child.getMonsterEncyclopedia();

        for(int i=0; i<MONG_NAMES.length; i++) {
            EncyclopediaDetail detail = EncyclopediaDetail.builder()
                    .isRegister(false)
                    .mongName(MONG_NAMES[i])
                    .mongStory(MONG_STORIES[i])
                    .registerDate(LocalDateTime.now().toString())
                    .monsterEncyclopedia(monsterEncyclopedia)
                    .build();

            ed.add(detail);

            encyclopediaDetailRepository.save(detail);


        }
        return ed;
    }
}
