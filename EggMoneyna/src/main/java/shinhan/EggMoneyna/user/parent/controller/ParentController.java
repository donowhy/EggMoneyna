package shinhan.EggMoneyna.user.parent.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import shinhan.EggMoneyna.jwt.UserInfo;
import shinhan.EggMoneyna.jwt.UsersInfo;
import shinhan.EggMoneyna.user.child.service.dto.ChildLoginRequest;
import shinhan.EggMoneyna.user.child.service.dto.returnToken;
import shinhan.EggMoneyna.user.parent.entity.Parent;
import shinhan.EggMoneyna.user.parent.service.ParentService;
import shinhan.EggMoneyna.user.parent.service.dto.ParentLoginRequest;
import shinhan.EggMoneyna.user.parent.service.dto.ParentResponse;
import shinhan.EggMoneyna.user.parent.service.dto.ParentSaveRequest;
import shinhan.EggMoneyna.user.parent.service.dto.returnParentToken;

@Slf4j
@RestController
@RequestMapping("/v1/parent")
@RequiredArgsConstructor
public class ParentController {

    private final ParentService parentService;

    @PostMapping("/save")
    public String save(@RequestBody ParentSaveRequest request){
        return parentService.save(request);
    }

    @PostMapping("/login")
    public returnParentToken login(@RequestBody ParentLoginRequest request){
        return parentService.login(request);
    }

    @PutMapping("/update/nickname")
    public void updateNickname (@UserInfo UsersInfo usersInfo, @RequestBody String nickname){
        parentService.updateNickname(usersInfo.getId(), nickname);
    }

    @PutMapping("/update/moneyDate")
    public void updatePocketMoneyDate (@UserInfo UsersInfo usersInfo, @RequestBody int day){
        parentService.updatePocketMoneyDate(usersInfo.getId(), day);
    }

    @PutMapping("/update/pocketMoney")
    public void updatePocketMoney (@UserInfo UsersInfo usersInfo, @RequestBody int money){
        parentService.updatePocketMoney(usersInfo.getId(), money);
    }

    @DeleteMapping("/delete")
    public void delete(@UserInfo UsersInfo usersInfo){
        parentService.delete(usersInfo.getId());
    }

    @GetMapping("/myInfo")
    public ParentResponse getMyInfo(@UserInfo UsersInfo usersInfo){
        return parentService.getMyInfo(usersInfo.getId());
    }

}
