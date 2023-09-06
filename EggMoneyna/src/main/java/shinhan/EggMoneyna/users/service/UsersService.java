package shinhan.EggMoneyna.users.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import shinhan.EggMoneyna.jwt.JwtService;
import shinhan.EggMoneyna.users.dto.SignUpRequestDto;
import shinhan.EggMoneyna.users.dto.UpdateRequestDto;
import shinhan.EggMoneyna.users.entity.Users;
import shinhan.EggMoneyna.users.repository.UsersRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UsersService {
    private final UsersRepository usersRepository;
    private final JwtService jwtService;

    public String save(SignUpRequestDto signUpRequestDto) {
        Users user = Users.builder()
                .isParents(signUpRequestDto.getIsParent())
                .userId(signUpRequestDto.getUserId())
                .nickName(signUpRequestDto.getNickName())
                .password("123")
                .pocketMoney(0)
                .build();

        usersRepository.save(user);
        log.info("User saved with ID = {}", signUpRequestDto.getUserId());
        return signUpRequestDto.getUserId();
    }

    public Users login(Long id) throws Exception {
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new Exception("User with ID " + id + " not found."));
        JwtService.TokenResponse<JwtService.TokenDataResponse> tokenResponse = jwtService.createToken(user.getUserId());
        user.setToken(tokenResponse.getData().getToken());
        return user;
    }

    public Users findUser(Long id) throws Exception {
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new Exception("User with ID " + id + " not found."));
        if (!jwtService.checkToken(user.getToken()).getCode().equals("200")) {
            throw new Exception("Invalid token for user with ID " + id);
        }
        return user;
    }

    public String delete(Long id) throws Exception {
        Users user = usersRepository.findById(id).orElseThrow(() -> new Exception("User with ID " + id + " not found."));
        usersRepository.delete(user);
        return "Success";
    }

    public Users update(String id, UpdateRequestDto updateRequestDto) throws Exception {
        Users user = usersRepository.findByUserId(id)
                .orElseThrow(() -> new Exception("User with ID " + id + " not found."));

        user.update(updateRequestDto.getNickName(), updateRequestDto.getLimitMoney(), updateRequestDto.getMoney(), updateRequestDto.getDate());
        return user;
    }
}
