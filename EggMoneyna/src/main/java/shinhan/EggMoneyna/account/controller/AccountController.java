package shinhan.EggMoneyna.account.controller;


import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import shinhan.EggMoneyna.account.dto.AccountCreateDto;
import shinhan.EggMoneyna.account.dto.CheckAccountRequestDto;
import shinhan.EggMoneyna.account.dto.DetailAccountResponseDto;
import shinhan.EggMoneyna.account.entity.Account;
import shinhan.EggMoneyna.account.service.AccountService;
import shinhan.EggMoneyna.global.response.Response;
import shinhan.EggMoneyna.jwt.UserInfo;
import shinhan.EggMoneyna.jwt.UsersInfo;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;


    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "내 계좌 조회",
        description = "Authorize만 되면 계좌 조회",
        tags = { "Account Controller" })
    @GetMapping()
    public ResponseEntity<Account> getAccount(@UserInfo UsersInfo usersInfo) {
        log.info("userinfo={}", usersInfo.getId());
        return ResponseEntity.ok(accountService.getAccount(usersInfo.getId()));

    }
//    @PostMapping("/sendCheck1")
//    public ResponseEntity<DetailAccountResponseDto> sendCheck1(@UserInfo UsersInfo usersInfo) {
//        return ResponseEntity.ok(accountService.sendCheck1(usersInfo.getId()));
//    }

//    @PostMapping("/checkAccount")
//    public ResponseEntity<Boolean> checkAccount(@UserInfo UsersInfo usersInfo, @RequestBody CheckAccountRequestDto requestDto) {
//        return ResponseEntity.ok(accountService.checkAccount(usersInfo.getId(), requestDto.getRandom()));
//    }

//    @GetMapping("/details")
//    public ResponseEntity<List<DetailAccountResponseDto>> getAccountDetail(@UserInfo UsersInfo usersInfo) {
//        return ResponseEntity.ok(accountService.getAccountDetail(usersInfo.getId()));
//    }
//

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "내 계좌 별명 부여",
        description = "Authorize 후 String 값",
        tags = { "Account Controller" })
    @PutMapping("/updateNickName")
    public ResponseEntity<Account> updateNickName(@RequestBody String name, @UserInfo UsersInfo usersInfo) {
        return ResponseEntity.ok(accountService.updateNickName(name, usersInfo.getId()));
    }


    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "내 계좌 삭제",
        description = "Authorize만 되면 계좌 삭제, 해커톤에서는 안쓰고 추후 대상이 되는 나이가 늘어나면 사용예정",
        tags = { "Account Controller" })
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@UserInfo UsersInfo usersInfo) {
        return ResponseEntity.ok(accountService.delete(usersInfo.getId()));
    }
//
//    @PostMapping("/payment")
//    public ResponseEntity<InAccount> payment(@UserInfo UsersInfo usersInfo, InAccount inAccount){
//        return ResponseEntity.ok(accountService.payment(usersInfo.getId(), inAccount));
//    }

}
