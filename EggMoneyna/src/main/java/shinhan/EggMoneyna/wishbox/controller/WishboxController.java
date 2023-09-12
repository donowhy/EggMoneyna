package shinhan.EggMoneyna.wishbox.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shinhan.EggMoneyna.jwt.UserInfo;
import shinhan.EggMoneyna.jwt.UsersInfo;
import shinhan.EggMoneyna.wishbox.service.WishboxService;
import shinhan.EggMoneyna.wishbox.service.dto.CreateWishBoxRequestDto;

@Slf4j
@RestController
@RequestMapping("v1/wishbox")
@RequiredArgsConstructor
public class WishboxController {

    private final WishboxService wishboxService;

    @PostMapping("/create")
    public String create(@UserInfo UsersInfo usersInfo, @RequestBody CreateWishBoxRequestDto requestDto){
        wishboxService.create(usersInfo.getId(), requestDto);
        return "생성 성공";
    }
}
