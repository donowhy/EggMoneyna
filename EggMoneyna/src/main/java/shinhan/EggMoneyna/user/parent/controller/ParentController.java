package shinhan.EggMoneyna.user.parent.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import shinhan.EggMoneyna.jwt.UserInfo;
import shinhan.EggMoneyna.jwt.UsersInfo;
import shinhan.EggMoneyna.user.parent.service.ParentService;
import shinhan.EggMoneyna.user.parent.service.dto.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/parent")
@RequiredArgsConstructor
public class ParentController {

    private final ParentService parentService;

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "부모 생성 및 계좌 생성",
        description = "부모 이름만 넘기면 계정 생성 및 계좌 생성 (돈 5,000,000)",
        tags = { "Parent Controller" })
    @PostMapping("/save")
    public ParentSaveResponse save(@RequestBody ParentSaveRequest request){
        return parentService.save(request);
    }

//    @PostMapping("/login")
//    public returnParentToken login(@RequestBody ParentLoginRequest request){
//        return parentService.login(request);
//    }


    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "아이 별명 설정",
        description = "아이 별명 설정, String값",
        tags = { "Parent Controller" })
    @PutMapping("/update/nickname")
    public void updateNickname (@UserInfo UsersInfo usersInfo, @RequestBody String nickname){
        parentService.updateNickname(usersInfo.getId(), nickname);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "아이 용돈 `언제`줄지 변경",
        description = "아이 용돈 언제줄지 변경, int값 하나만 넘김",
        tags = { "Parent Controller" })
    @PutMapping("/update/moneyDate")
    public void updatePocketMoneyDate (@UserInfo UsersInfo usersInfo, @RequestBody int day){
        parentService.updatePocketMoneyDate(usersInfo.getId(), day);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "아이 용돈 `얼마`줄지 변경",
        description = "아이 용돈 얼마줄지 변경, int값 하나만 넘김",
        tags = { "Parent Controller" })
    @PutMapping("/update/pocketMoney")
    public void updatePocketMoney (@UserInfo UsersInfo usersInfo, @RequestBody int money){
        parentService.updatePocketMoney(usersInfo.getId(), money);
    }

    @Operation(summary = "부모 계정 삭제",
        description = "Authorize 후 부모 계정",
        tags = { "Parent Controller" })
    @DeleteMapping("/delete")
    public void delete(@UserInfo UsersInfo usersInfo){
        parentService.delete(usersInfo.getId());
    }


    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "부모 자신의 정보 조회",
        description = "Authorize만 되면 정보 조회",
        tags = { "Parent Controller" })
    @GetMapping("/myInfo")
    public ParentResponse getMyInfo(@UserInfo UsersInfo usersInfo){
        return parentService.getMyInfo(usersInfo.getId());
    }

    @Operation(summary = "내 자녀 모두 조회",
            description = "내 자녀의 이름 나이 성별 , 성별이 true면 여자",
            tags = { "Parent Controller" })
    @GetMapping("/findMyChilds")
    public List<MyChildsResponse> findMyChilds(@UserInfo UsersInfo usersInfo){
        return parentService.findMyChilds(usersInfo.getId());
    }

    @Operation(summary = "한 자녀 조회",
            description = "한 자녀에 대해서 계좌 및 계좌 내역, 이름, 생일, 나이, 용돈 일자, 용돈이 얼마인지" ,
            tags = { "Parent Controller" })
    @GetMapping("/findMyOneChild")
    public MyChildResponse findMyOneChild(@UserInfo UsersInfo usersInfo){
        return parentService.findMyOnechild(usersInfo.getId());
    }
}
