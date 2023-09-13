package shinhan.EggMoneyna.wishbox.controller;

import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "위시박스 생성", description = "위시박스 생성 시 가상계좌 생성", tags = { "Wishbox Controller" })
    @PostMapping("/create")
    public String create(@UserInfo UsersInfo usersInfo, @RequestBody CreateWishBoxRequestDto requestDto){
        wishboxService.create(usersInfo.getId(), requestDto);
        return "생성 성공";
    }
}
