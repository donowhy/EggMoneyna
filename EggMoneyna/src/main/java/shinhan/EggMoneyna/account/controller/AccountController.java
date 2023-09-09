package shinhan.EggMoneyna.account.controller;

import javax.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import shinhan.EggMoneyna.account.dto.AccountCreateDto;
import shinhan.EggMoneyna.account.entity.Account;
import shinhan.EggMoneyna.account.service.AccountService;
import shinhan.EggMoneyna.jwt.JwtProvider;
import shinhan.EggMoneyna.jwt.UserInfo;
import shinhan.EggMoneyna.jwt.UsersInfo;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/account")
public class AccountController {

   private final AccountService accountService;
   private final JwtProvider jwtProvider;

    private Account getId(@UserInfo UsersInfo usersInfo) {
        return accountService.getAccount(usersInfo.getId());
    }


   @DeleteMapping()
   public String delete (@UserInfo UsersInfo usersInfo){
       return accountService.delete(usersInfo.getId());
   }

   @PutMapping()
   public Account updateNickName( String name, @UserInfo UsersInfo usersInfo){
       return accountService.updateNickName(name, usersInfo.getId());
   }
}
