package shinhan.EggMoneyna.account.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import shinhan.EggMoneyna.account.dto.*;
import shinhan.EggMoneyna.account.entity.Account;
import shinhan.EggMoneyna.account.entity.InAccount;
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

    @Operation(summary = "계좌 조회", description = "계좌 조회.", tags = { "Account Controller" })
    @GetMapping()
    public ResponseEntity<Account> getAccount(@UserInfo UsersInfo usersInfo) {
        log.info("userinfo={}", usersInfo.getId());
        return ResponseEntity.ok(accountService.getAccount(usersInfo.getId()));

    }

    @Operation(summary = "1원 이체", description = "본인 확인을 위한 계좌 1원 이체.", tags = { "Account Controller" })
    @PostMapping("/sendCheck1")
    public ResponseEntity<DetailAccountResponseDto> sendCheck1(@UserInfo UsersInfo usersInfo) {
        return ResponseEntity.ok(accountService.sendCheck1(usersInfo.getId()));
    }


    @Operation(summary = "1원 확인", description = "보낸 임의의 알파벳 4글자를 입력한 값과 맞는지 확인.", tags = { "Account Controller" })
    @PostMapping("/checkAccount")
    public ResponseEntity<Boolean> checkAccount(@UserInfo UsersInfo usersInfo, @RequestBody CheckAccountRequestDto requestDto) {
        return ResponseEntity.ok(accountService.checkAccount(usersInfo.getId(), requestDto.getRandom()));
    }


    @Operation(summary = "계좌 내역 조회", description = "계좌 내역 조회.", tags = { "Account Controller" })
    @GetMapping("/details")
    public ResponseEntity<List<DetailAccountResponseDto>> getAccountDetail(@UserInfo UsersInfo usersInfo) {
        return ResponseEntity.ok(accountService.getAccountDetail(usersInfo.getId()));
    }


    @Operation(summary = "계좌 닉네임 변경", description = "계좌의 닉네임을 변경한다.", tags = { "Account Controller" })
    @PutMapping("/updateNickName")
    public ResponseEntity<Account> updateNickName(@RequestBody String name, @UserInfo UsersInfo usersInfo) {
        return ResponseEntity.ok(accountService.updateNickName(name, usersInfo.getId()));
    }


    @Operation(summary = "계좌 삭제", description = "계좌 삭제.", tags = { "Account Controller" })
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@UserInfo UsersInfo usersInfo) {
        return ResponseEntity.ok(accountService.delete(usersInfo.getId()));
    }


    @Operation(summary = "결제", description = "결제, 만약 돈이 없으면 거부.", tags = { "Account Controller" })
    @PostMapping("/payment")
    public ResponseEntity<PaymentResponseDto> payment(@UserInfo UsersInfo usersInfo, @RequestBody PaymentRequestDto requestDto){
        return ResponseEntity.ok(accountService.payment(usersInfo.getId(), requestDto));
    }


    @Operation(summary = "결제 취소", description = "취소 시, 브랜드 위치, 회사명, 돈, 시간이 30일 지났는지 확인 후 취소.", tags = { "Account Controller" })
    @DeleteMapping("/cancelPayment")
    public ResponseEntity<String> cancelPayment(@UserInfo UsersInfo usersInfo, @RequestBody CancelRequestDto requestDto){
        return ResponseEntity.ok(accountService.cancel(usersInfo.getId(), requestDto));
    }
}
