package shinhan.EggMoneyna.user.child.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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


    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "아이 생성 및 계좌 생성",
        description = "아이 이름만 넘기면 계정 생성 및 계좌 생성",
        tags = { "Develop Controller" })
    @PostMapping("/save")
    public ChildSaveResponse save (@RequestBody ChildSaveRequest request){
        return childService.save(request);
    }

//    @PostMapping("/login")
//    public returnToken login(@RequestBody ChildLoginRequest request){
//        return childService.login(request);
//    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "아이 정보 조회",
        description = "아이 정보 조회. Authorize만 한다면 내 정보와 관련된 모든 것을 불러올 수 있다.",
        tags = { "Child Controller" })
    @GetMapping("/getMyInfo")
    public ChildResponse getMyInfo(@UserInfo UsersInfo usersInfo){
        return childService.getMyInfo(usersInfo.getId());
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "임계치 변경",
        description = "임계치 변경, rq: Authorize와 임계치 값",
        tags = { "Child Controller" })
    @PutMapping("/update")
    public @ResponseBody void updateLimitMoney(@UserInfo UsersInfo usersInfo, @RequestBody UpdateLimitMoneyRequest request){
        childService.updateLimitMoney(usersInfo.getId(), request);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "계정 삭제",
        description = "계정 삭제.",
        tags = { "Child Controller" })
    @DeleteMapping("/delete")
    public @ResponseBody void deleteChild(@UserInfo UsersInfo usersInfo){
        childService.deleteChild(usersInfo.getId());
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "에그머니나 확인",
            description = "부모와 연결된 계좌인지 확인.",
            tags = { "Child Controller" })
    @PostMapping("/checkEggMoney")
    public @ResponseBody boolean checkEggMoney(@UserInfo UsersInfo usersInfo){
        return childService.checkEggMoney(usersInfo.getId());
    }


    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "아이 정보 홈",
            description = "아이 정보 홈. Authorize만 한다면 내 정보와 관련된 모든 것을 불러올 수 있다.",
            tags = { "Child Controller" })
    @GetMapping("/getMyHome")
    public GetChildHomeResponse getChildHome(@UserInfo UsersInfo usersInfo){
        return childService.getChildHome(usersInfo.getId());
    }
}
