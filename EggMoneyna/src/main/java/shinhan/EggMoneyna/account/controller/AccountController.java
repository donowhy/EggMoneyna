package shinhan.EggMoneyna.account.controller;

import javax.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import shinhan.EggMoneyna.account.dto.AccountCreateDto;
import shinhan.EggMoneyna.account.entity.Account;
import shinhan.EggMoneyna.account.service.AccountService;
import shinhan.EggMoneyna.jwt.JwtProvider;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/account")
public class AccountController {

   private final AccountService accountService;
   private final JwtProvider jwtProvider;

    private String getId(HttpServletRequest httpServletRequest) {
        String header = httpServletRequest.getHeader("Authorization");
        String id = (String) jwtProvider.parseJwtToken(header).get("sub");
        return id;
    }

    @GetMapping("")
   public Account getAccount (HttpServletRequest httpServletRequest){
        String id = getId(httpServletRequest);
        return accountService.getAccount(id);
   }

   @DeleteMapping()
   public String delete (HttpServletRequest httpServletRequest){
       String id = getId(httpServletRequest);
       return accountService.delete(id);
   }

   @PutMapping()
   public Account updateNickName( String name, HttpServletRequest httpServletRequest){
       String id1 = getId(httpServletRequest);
       return accountService.updateNickName(name, id1);
   }
}
