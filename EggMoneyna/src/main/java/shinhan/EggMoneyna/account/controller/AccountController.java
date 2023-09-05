//package shinhan.EggMoneyna.account.controller;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.*;
//import shinhan.EggMoneyna.account.dto.AccountCreateDto;
//import shinhan.EggMoneyna.account.entity.Account;
//import shinhan.EggMoneyna.account.service.AccountService;
//
//@Slf4j
//@RequiredArgsConstructor
//@RestController
//@RequestMapping("/v1")
//public class AccountController {
//
//    private final AccountService accountService;
//
//    @PostMapping("/create")
//    public Long create (@RequestBody AccountCreateDto accountCreateDto){
//        return accountService.create(accountCreateDto);
//    }
//
//    @GetMapping("/{id}")
//    public Account getAccount (@PathVariable Long id){
//        return accountService.getAccount(id);
//    }
//
//    @DeleteMapping("/{id}")
//    public String delete (@PathVariable Long id){
//        return accountService.delete(id);
//    }
//
//    @PutMapping("/{id}")
//    public Account updateNickName(Long id, String name){
//        return accountService.updateNickName(id, name);
//    }
//}
