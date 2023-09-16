package shinhan.EggMoneyna.wishbox.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import shinhan.EggMoneyna.jwt.UserInfo;
import shinhan.EggMoneyna.jwt.UsersInfo;
import shinhan.EggMoneyna.wishbox.service.WishboxService;
import shinhan.EggMoneyna.wishbox.service.dto.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("v1/wishbox")
@RequiredArgsConstructor
public class WishboxController {

    private final WishboxService wishboxService;

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "위시박스 생성", description = "위시박스 생성 시 가상계좌 생성", tags = { "Wishbox Controller" })
    @PostMapping("/create")
    public Long create(@UserInfo UsersInfo usersInfo, @RequestBody CreateWishBoxRequestDto requestDto){
        return wishboxService.create(usersInfo.getId(), requestDto);

    }

//    @SecurityRequirement(name = "Bearer Authentication")
//    @Operation(summary = "위시박스 별명 변경", description = "위시박스 별명 변경", tags = { "Wishbox Controller" })
//    @PutMapping("/nickname")
//    public void updateNickname(@UserInfo UsersInfo usersInfo, @RequestBody UpdateNicknameRequest request){
//        wishboxService.updateNickname(usersInfo.getId(), request);
//    }


    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "위시박스 삭제", description = "위시박스 삭제, 삭제하면 돈은 모계좌로 돌아간다.", tags = { "Wishbox Controller" })
    @DeleteMapping("/delete")
    public void deleteWishbox(@UserInfo UsersInfo usersInfo, @RequestBody GetWishboxRequest request){
        wishboxService.deleteWishbox(usersInfo.getId(), request);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "위시박스 단일 조회", description = "위시박스 단일 조회", tags = { "Wishbox Controller" })
    @GetMapping("/getOneWishbox")
    public GetWishboxResponse getOneWishbox (@UserInfo UsersInfo usersInfo, @RequestBody GetWishboxRequest request){
        return wishboxService.getOneWishbox(usersInfo.getId(), request);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "위시박스 전체 조회", description = "위시박스 전체 조회", tags = { "Wishbox Controller" })
    @GetMapping("/getWishbox")
    public List<GetWishboxResponse> getWishbox (@UserInfo UsersInfo usersInfo){
        return wishboxService.getWishbox(usersInfo.getId());
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "위시박스에서 내 계좌로 입금 ", description = "위시박스에서 내 계좌로 입금, 내 위시박스 계좌 Long 타입 필요", tags = { "Wishbox Controller" })
    @PostMapping("/sendMoneyMyAccount")
    public SendMoneyMyAccountResponse sendMoneyMyAccount(@UserInfo UsersInfo usersInfo, @RequestBody SendMoneyMyAccountRequest request){
        return wishboxService.sendMoneyMyAccount(usersInfo.getId(), request);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "내 계좌에서 위시박스로 입금 , description = 내 계좌에서 위시박스로 입금, 내 위시박스 계좌 Long 타입 필요", tags = { "Wishbox Controller" })
    @PostMapping("/sendMoneyMyVirtualAccount")
    public SendMoneyMyAccountResponse sendMoneyMyVirtualAccount(@UserInfo UsersInfo usersInfo, @RequestBody SendMoneyMyAccountRequest request){
        return wishboxService.sendMoneyMyVirtualAccount(usersInfo.getId(), request);
    }
}
