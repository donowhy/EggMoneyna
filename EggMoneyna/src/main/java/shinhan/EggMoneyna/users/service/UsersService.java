package shinhan.EggMoneyna.users.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import shinhan.EggMoneyna.account.dto.AccountCreateDto;
import shinhan.EggMoneyna.account.service.AccountService;
import shinhan.EggMoneyna.jwt.JwtProvider;
import shinhan.EggMoneyna.users.dto.SignUpRequestDto;
import shinhan.EggMoneyna.users.dto.UpdateRequestDto;
import shinhan.EggMoneyna.users.dto.returnTokenDto;
import shinhan.EggMoneyna.users.entity.Users;
import shinhan.EggMoneyna.users.repository.UsersRepository;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UsersService {
    private final UsersRepository usersRepository;
    private final JwtProvider jwtProvider;
    private final AccountService accountService;

    public returnTokenDto save(SignUpRequestDto signUpRequestDto) throws Exception {
        log.info("users save");
        Users parent = Users.builder()
                .isParents(signUpRequestDto.getIsParent())
                .userId(signUpRequestDto.getParentId())
                .nickName("부모")
                .password("123")
                .build();

        usersRepository.saveAndFlush(parent);

        Users child = Users.builder()
                .isParents(!signUpRequestDto.getIsParent())
                .userId(signUpRequestDto.getChildId())
                .nickName(signUpRequestDto.getNickName())
                .password("123")
                .pocketMoney(0)
                .build();

        usersRepository.saveAndFlush(child);


        parent.addChild(child);

        login(parent.getId());
        login(child.getId());

        AccountCreateDto accountCreateDto = AccountCreateDto.builder()
            .nickName(child.getNickName())
            .build();

        accountService.create(accountCreateDto, child.getUserId());
        return returnTokenDto.builder()
                .parentToken(jwtProvider.createToken(parent))
                .childToken(jwtProvider.createToken(child))
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

        user.update(updateRequestDto.getNickName(), updateRequestDto.getLimitMoney(), updateRequestDto.getMoney(), updateRequestDto.getDate());
        return user;
    }
    
}
