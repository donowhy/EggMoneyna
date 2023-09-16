package shinhan.EggMoneyna.users.service;

import com.google.firebase.auth.FirebaseAuth;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import shinhan.EggMoneyna.account.dto.AccountCreateDto;
import shinhan.EggMoneyna.account.service.AccountService;
import shinhan.EggMoneyna.jwt.JwtProvider;
import shinhan.EggMoneyna.users.dto.SignUpRequestDto;
import shinhan.EggMoneyna.users.dto.UpdateLimitMoneyRequestDto;
import shinhan.EggMoneyna.users.dto.UpdateRequestDto;
import shinhan.EggMoneyna.users.dto.returnTokenDto;
import shinhan.EggMoneyna.users.entity.Users;
import shinhan.EggMoneyna.users.repository.UsersRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UsersService {
    private final UsersRepository usersRepository;
    private final JwtProvider jwtProvider;
    private final AccountService accountService;
    private final FirebaseAuth firebaseAuth;

    public returnTokenDto save(SignUpRequestDto signUpRequestDto) throws Exception {
        log.info("users save");

        String uid = "some-uid";

//        String customToken = firebaseAuth.createCustomToken(uid); // 수정된 부분
//        log.info("customToken={}", customToken);

        Users parent = Users.builder()
                .isParents(signUpRequestDto.getIsParent())
                .userId(signUpRequestDto.getParentId())
                .nickName("부모")
                .password("123")
//                .firebaseToken(customToken)
                .build();

        usersRepository.saveAndFlush(parent);


//        customToken = firebaseAuth.createCustomToken(uid);


        Users child = Users.builder()
                .isParents(!signUpRequestDto.getIsParent())
                .userId(signUpRequestDto.getChildId())
                .nickName(signUpRequestDto.getNickName())
                .password("123")
                .pocketMoney(0)
//                .firebaseToken(customToken)
                .build();

        usersRepository.saveAndFlush(child);



        login(parent.getId());
        login(child.getId());

        parent.addChild(child);

        AccountCreateDto accountCreateDto = AccountCreateDto.builder()
            .nickName(child.getNickName())
            .build();

        accountService.childCreate( child.getId());
        return returnTokenDto.builder()
                .parentToken("jz")
                .childToken("jz")
                .build();
    }

    private Users login(Long id) throws Exception {
        log.info("user login");
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new Exception("User with ID " + id + " not found."));
        return user;
    }

    public Users findUser(Long id) throws Exception {

        return usersRepository.findById(id)
                .orElseThrow(() -> new Exception("User with ID " + id + " not found."));
    }

    public String delete(Long id) throws Exception {
        Users user = usersRepository.findById(id).orElseThrow(() -> new Exception("User with ID " + id + " not found."));
        usersRepository.delete(user);
        return "Success";
    }

    public Users update(Long id, UpdateRequestDto updateRequestDto) throws Exception {
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new Exception("User with ID " + id + " not found."));

        user.setAtParent(updateRequestDto.getNickName(), updateRequestDto.getMoney(), updateRequestDto.getDate());
        return user;
    }

    public Users updateLimitMoney(Long id, UpdateLimitMoneyRequestDto requestDto) throws Exception {
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new Exception("User with ID " + id + " not found."));

        user.setLimitMoney(requestDto.getLimitMoney());
        return user;
    }

    public Users myOneChilds(Long parentId, Long childId){
        return usersRepository.findChildByParentId(parentId, childId).orElseThrow();
    }

    public List<Users> myChilds(Long parentId){
        return usersRepository.findChildrenByParentId(parentId);
    }



    public Users addChild(Long parentId, SignUpRequestDto signUpRequestDto) throws Exception {
        // 부모 객체 찾기
        Users parent = usersRepository.findById(parentId)
                .orElseThrow(() -> new Exception("Parent with ID " + parentId + " not found."));

        // 아이 객체 생성
        Users child = Users.builder()
                .isParents(false)  // 아이이므로 false로 설정
                .userId(signUpRequestDto.getChildId())
                .nickName(signUpRequestDto.getNickName())
                .password("123")
                .pocketMoney(0)
                .build();

        // 아이 객체 저장
        usersRepository.saveAndFlush(child);

        // 양방향 관계 설정
        parent.addChild(child);

        // 변경 사항 저장
        usersRepository.saveAndFlush(parent);

        // 새로운 아이 계정 생성
        AccountCreateDto accountCreateDto = AccountCreateDto.builder()
                .nickName(child.getNickName())
                .build();

        accountService.childCreate(child.getId());

        // 새로 생성된 아이 객체 반환
        return child;
    }


    public void linkChildrenToParent(Long parentId, List<Long> childrenIds) throws Exception {
        // 부모 객체 찾기
        Users parent = usersRepository.findById(parentId)
                .orElseThrow(() -> new Exception("Parent with ID " + parentId + " not found."));

        // 각 아이 ID에 대해
        for (Long childId : childrenIds) {
            // 아이 객체 찾기
            Users child = usersRepository.findById(childId)
                    .orElseThrow(() -> new Exception("Child with ID " + childId + " not found."));

            // 아이가 해당 부모를 이미 가지고 있는지 확인
            if (!child.getParents().contains(parent)) {
                // 양방향 관계 설정
                parent.addChild(child);
                child.addParent(parent);
            }
        }

        // 변경 사항 저장
        usersRepository.saveAndFlush(parent);
    }

}
