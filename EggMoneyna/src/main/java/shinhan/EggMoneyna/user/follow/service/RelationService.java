package shinhan.EggMoneyna.user.follow.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shinhan.EggMoneyna.user.parent.entity.Parent;
import shinhan.EggMoneyna.user.child.entity.Child;
import shinhan.EggMoneyna.user.follow.entity.Relation;
import shinhan.EggMoneyna.user.parent.repository.ParentRepository;
import shinhan.EggMoneyna.user.child.repository.ChildRepository;
import shinhan.EggMoneyna.user.follow.repository.RelationRepository;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class RelationService {

    private final RelationRepository relationRepository;

    private final ParentRepository parentRepository;

    private final ChildRepository childRepository;

    // 연관 관계 생성
    public Relation createRelation(Long parentId, Long childId) {
        Parent parent = parentRepository.findById(parentId).orElseThrow(() ->
                new RuntimeException("Parent not found")
        );
        Child child = childRepository.findById(childId).orElseThrow(() ->
                new RuntimeException("Child not found")
        );

        Relation relation = Relation.builder()
                .parent(parent)
                .child(child)
                .build();
        log.info("여기까진={}", relation.getParent());
        parent.setIsRelation(true);
        child.setIsRelation(true);

        parentRepository.save(parent);
        childRepository.save(child);

        return relationRepository.save(relation);
    }

    // 연관 관계 생성
    public Relation createFollowRelation(Long parentId, Long childId) {
        Parent parent = parentRepository.findById(parentId).orElseThrow(() -> new RuntimeException("Parent not found"));
        Child child = childRepository.findById(childId).orElseThrow(() -> new RuntimeException("Child not found"));

        Relation relation = Relation.builder()
                .parent(parent)
                .child(child)
                .build();

        parent.setIsRelation(true);
        child.setIsRelation(true);

        parentRepository.save(parent);
        childRepository.save(child);

        return relationRepository.save(relation);
    }

    // 연관 관계 읽기
    public Relation getRelation(Long relationId) {
        return relationRepository.findById(relationId).orElseThrow(() -> new RuntimeException("Relation not found"));
    }

    // 연관 관계 업데이트 - 예시로 Parent의 아이 닉네임을 변경하는 메서드를 작성했습니다
    public Relation updateRelation(Long relationId, String newChildNickname) {
        Relation relation = relationRepository.findById(relationId).orElseThrow(() -> new RuntimeException("Relation not found"));
        Parent parent = relation.getParent();
        parent.setNickname(newChildNickname);
        parentRepository.save(parent);
        return relation;
    }

    // 연관 관계 삭제
    public void deleteRelation(Long relationId) {
        Relation relation = relationRepository.findById(relationId).orElseThrow(() -> new RuntimeException("Relation not found"));
        Parent parent = relation.getParent();
        Child child = relation.getChild();

        parent.setIsRelation(false);
        child.setIsRelation(false);

        parentRepository.save(parent);
        childRepository.save(child);

        relationRepository.deleteById(relationId);
    }
}
