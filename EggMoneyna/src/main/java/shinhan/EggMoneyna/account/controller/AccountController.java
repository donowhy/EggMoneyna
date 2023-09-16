package shinhan.EggMoneyna.account.controller;


import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import shinhan.EggMoneyna.account.dto.*;
import shinhan.EggMoneyna.account.entity.Account;
import shinhan.EggMoneyna.account.service.AccountService;
import shinhan.EggMoneyna.account.service.dto.Send1CertRequest;
import shinhan.EggMoneyna.jwt.UserInfo;
import shinhan.EggMoneyna.jwt.UsersInfo;

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


    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "1원 이체",
            description = "값 3개 반환",
            tags = { "Account Controller" })
    @PostMapping("/send1CertParent")
    public Send1CertResponse send1Cert (@RequestBody Send1CertRequest request){
        return accountService.send1Cert(request);
    }


    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "부모 계좌에 1원 인증 확인",
            description = "Authorize, 계정에 있는 3글자 인증 번호 입력",
            tags = { "Account Controller" })
    @PostMapping("/chekcParent1Cert")
    public Check1CertParentResponse checkParentAccount(@RequestBody Long number, @RequestBody  String random){
        return accountService.checkParentAccount(number, random);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "아이 계좌에 1원 인증 확인",
            description = "Authorize, 계정에 있는 3글자 인증 번호 입력",
            tags = { "Account Controller" })
    @PostMapping("/checkChild1Cert")
    public Check1CertChildResponse checkChildAccount(@RequestBody Long number,@RequestBody String random){
        return accountService.checkChildAccount(number, random);
    }

}
