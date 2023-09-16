package shinhan.EggMoneyna.monster.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import shinhan.EggMoneyna.monster.dto.HistoryRequest;
import shinhan.EggMoneyna.monster.dto.HistoryResponse;
import shinhan.EggMoneyna.monster.entity.History;
import shinhan.EggMoneyna.monster.repository.HistoryRepository;
import shinhan.EggMoneyna.user.child.entity.Child;
import shinhan.EggMoneyna.user.child.repository.ChildRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class HistoryService {

    private final HistoryRepository historyRepository;
    private final ChildRepository childRepository;

    public HistoryResponse save (Long id, HistoryRequest request){

        Child child = childRepository.findById(id).orElseThrow();

        String[] plusExp = {"출석체크", "부모님의 칭찬", "임계치 지키미"};

        int[] exp = {5, 10, 20};

        History history = History.builder()
                .content(plusExp[request.getNumber()])
                .exp(exp[request.getNumber()])
                .monster(child.getMonster())
                .prefix(child.getMonster().getExp() + exp[request.getNumber()])
                .build();

        historyRepository.saveAndFlush(history);
        return HistoryResponse.builder()
                .content(plusExp[request.getNumber()])
                .exp(exp[request.getNumber()])
                .prefix(child.getMonster().getExp() + exp[request.getNumber()])
                .build();
    }
}
