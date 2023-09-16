package shinhan.EggMoneyna.user.follow.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import shinhan.EggMoneyna.global.error.code.ErrorCode;
import shinhan.EggMoneyna.global.error.exception.BadRequestException;
import shinhan.EggMoneyna.jwt.JwtProvider;
import shinhan.EggMoneyna.user.follow.service.dto.RelationEggMoney;
import shinhan.EggMoneyna.user.follow.service.dto.RelationParentChild;
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
    private final JwtProvider jwtProvider;

    // 연관 관계 생성
    public RelationParentChild createRelation(Long parentId, Long childId) {
        Parent parent = parentRepository.findById(parentId).orElseThrow(() ->
                new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID)
        );
        Child child = childRepository.findById(childId).orElseThrow(() ->
                new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID)
        );

        Relation relation = Relation.builder()
                .parent(parent)
                .child(child)
                .build();
        log.info("여기까진={}", relation.getParent());
        parent.setIsRelation(true);
        child.setIsRelation(true);

        String parentToken = jwtProvider.createParentToken(parent);

        parentRepository.save(parent);
        childRepository.save(child);
        relationRepository.save(relation);

        return RelationParentChild.builder()
                .ParentToken(parentToken)
                .pId(parent.getId())
                .parentName(parent.getParentName())
                .cId(child.getId())
                .childName(child.getChildName())
                .build();
    }

    // 에그머니나 생성
    public RelationEggMoney createEggMoneyRelation(Long parentId, Long childId) {
        Parent parent = parentRepository.findById(parentId).orElseThrow(() ->
                new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID)
        );
        Child child = childRepository.findById(childId).orElseThrow(() ->
                new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID)
        );

        String childToken = jwtProvider.createChildToken(child);

        parent.setEggMoney(true);
        child.setEggMoney(true);

        parentRepository.save(parent);
        childRepository.save(child);

        return RelationEggMoney.builder()
                .pId(parent.getId())
                .parentName(parent.getParentName())
                .childToken(childToken)
                .cId(child.getId())
                .childName(child.getChildName())
                .build();
    }

    // 연관 관계 읽기
    public Relation getRelation(Long relationId) {
        return relationRepository.findById(relationId).orElseThrow(() ->
                new BadRequestException(ErrorCode.NOT_EXISTS_RELATION)
        );
    }

    // 연관 관계 삭제
    public void deleteRelation(Long parentid, Long childId) {

        Relation relation = relationRepository.findRelationByParentIdAndChildId(childId, parentid)
                .orElseThrow(() ->
                        new BadRequestException(ErrorCode.NOT_EXISTS_RELATION)
                );

        Parent parent = relation.getParent();
        Child child = relation.getChild();

        parent.setIsRelation(false);
        child.setIsRelation(false);

        parentRepository.save(parent);
        childRepository.save(child);

        relationRepository.delete(relation);
    }
}
