package shinhan.EggMoneyna.user.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shinhan.EggMoneyna.user.dto.*;
import shinhan.EggMoneyna.user.entity.User;
import shinhan.EggMoneyna.user.repository.UserRepository;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserSaveResponseDto save(UserSaveRequestDto userSaveRequestDto){

        userRepository.save(User.builder()
                        .userId(userSaveRequestDto.getName())
                        .password(userSaveRequestDto.getPassword())
                        .nickName(userSaveRequestDto.getNickName())
                        .isParent(userSaveRequestDto.getIsParent())
                        .pocketMoneyDate(userSaveRequestDto.getPocketMoneyDate())
                        .build());

        return UserSaveResponseDto.builder()
                .success("성공")
                .build();
    }

    public UserUpdateResponseDto nickNameUpdate (Long id, UserUpdateRequestDto userUpdateRequestDto){
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);

        return new UserUpdateResponseDto(user.setNickName(userUpdateRequestDto.getNickname()));
    }

    public UserResponseDto userRead (Long id){
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);
        if(user.getIsParent().equals(false)){
            throw new RuntimeException("아이가 아닙니다.");
        }
        return UserResponseDto
                .userResponseDto(user);
    }
}
