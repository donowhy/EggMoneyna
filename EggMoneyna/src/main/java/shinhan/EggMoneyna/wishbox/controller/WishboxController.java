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
import shinhan.EggMoneyna.wishbox.service.dto.GetWishboxRequest;
import shinhan.EggMoneyna.wishbox.service.dto.GetWishboxResponse;
import shinhan.EggMoneyna.wishbox.service.dto.UpdateNicknameRequest;

import java.util.List;

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

    @Operation(summary = "위시박스 별명 변경", description = "위시박스 별명 변경", tags = { "Wishbox Controller" })
    @PostMapping("/nickname")
    public void updateNickname(@UserInfo UsersInfo usersInfo, @RequestBody UpdateNicknameRequest request){
        wishboxService.updateNickname(usersInfo.getId(), request);
    }

    @Operation(summary = "위시박스 삭제", description = "위시박스 삭제, 삭제하면 돈은 모계좌로 돌아간다.", tags = { "Wishbox Controller" })
    @PostMapping("/delete")
    public void deleteWishbox(@UserInfo UsersInfo usersInfo, @RequestBody GetWishboxRequest request){
        wishboxService.deleteWishbox(usersInfo.getId(), request);
    }

    @Operation(summary = "위시박스 단일 조회", description = "위시박스 단일 조회", tags = { "Wishbox Controller" })
    @PostMapping("/getOneWishbox")
    public GetWishboxResponse getOneWishbox (@UserInfo UsersInfo usersInfo, @RequestBody GetWishboxRequest request){
        return wishboxService.getOneWishbox(usersInfo.getId(), request);
    }

    @Operation(summary = "위시박스 전체 조회", description = "위시박스 전체 조회", tags = { "Wishbox Controller" })
    @PostMapping("/getWishbox")
    public List<GetWishboxResponse> getWishbox (@UserInfo UsersInfo usersInfo){
        return wishboxService.getWishbox(usersInfo.getId());
    }
}
