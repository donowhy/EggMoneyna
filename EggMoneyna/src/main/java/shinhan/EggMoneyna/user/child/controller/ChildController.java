package shinhan.EggMoneyna.user.child.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import shinhan.EggMoneyna.jwt.UserInfo;
import shinhan.EggMoneyna.jwt.UsersInfo;
import shinhan.EggMoneyna.user.child.service.ChildService;
import shinhan.EggMoneyna.user.child.service.dto.*;

@Slf4j
@RestController
@RequestMapping("/v1/child")
@RequiredArgsConstructor
public class ChildController {

    private final ChildService childService;

    @PostMapping("/save")
    public ChildSaveResponse save (@RequestBody ChildSaveRequest request){
        return childService.save(request);
    }

    @PostMapping("/login")
    public returnToken login(@RequestBody ChildLoginRequest request){
        return childService.login(request);
    }

    @GetMapping("/getMyInfo")
    public ChildResponse getMyInfo(@UserInfo UsersInfo usersInfo){
        return childService.getMyInfo(usersInfo.getId());
    }

    @PutMapping("/update")
    public @ResponseBody void updateLimitMoney(@UserInfo UsersInfo usersInfo, @RequestBody UpdateLimitMoneyRequest request){
        childService.updateLimitMoney(usersInfo.getId(), request);
    }

    @DeleteMapping("/delete")
    public @ResponseBody void deleteChild(@UserInfo UsersInfo usersInfo){
        childService.deleteChild(usersInfo.getId());
    }
}
