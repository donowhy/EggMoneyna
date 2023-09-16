package shinhan.EggMoneyna.inputoutput.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shinhan.EggMoneyna.inputoutput.dto.InputOutputResponseDto;
import shinhan.EggMoneyna.inputoutput.dto.MonthOutputResponseDto;
import shinhan.EggMoneyna.inputoutput.service.InputOutputParentService;
import shinhan.EggMoneyna.jwt.UserInfo;
import shinhan.EggMoneyna.jwt.UsersInfo;

@RestController
@RequestMapping("/v1/inputoutput")
@RequiredArgsConstructor
public class InputOutputParentController {
    private final InputOutputParentService inputOutputParentService;

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "부모: 당일 입금 조회", description = "/yyyy-MM-dd로 조회한 사용자 계좌에 해당하는 날짜의 입금내역을 조회합니다.", tags = { "InputOutput Controller" })
    @PostMapping("/{childId}/in/{inputDate}")
    public ResponseEntity<InputOutputResponseDto> getInput(@UserInfo UsersInfo usersInfo, @PathVariable Long childId ,@PathVariable String inputDate) {
        return ResponseEntity.ok(inputOutputParentService.getInput(usersInfo.getId(), childId, inputDate));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "부모: 당일 출금 조회", description = "/yyyy-MM-dd로 조회한 사용자 계좌에 해당하는 날짜의 출금내역을 조회합니다.", tags = { "InputOutput Controller" })
    @PostMapping("/{childId}/out/{outputDate}")
    public ResponseEntity<InputOutputResponseDto> getOutput(@UserInfo UsersInfo usersInfo, @PathVariable Long childId ,@PathVariable String outputDate) {
        return ResponseEntity.ok(inputOutputParentService.getOutput(usersInfo.getId(), childId, outputDate));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "부모: 당일 입출금 조회", description = "/yyyy-MM-dd로 조회한 사용자 계좌에 해당하는 날짜의 출금내역을 조회합니다.", tags = { "InputOutput Controller" })
    @PostMapping("/{childId}/all/{inputOutputDate}")
    public ResponseEntity<InputOutputResponseDto> getInputOutput(@UserInfo UsersInfo usersInfo, @PathVariable Long childId ,@PathVariable String inputOutputDate) {
        return ResponseEntity.ok(inputOutputParentService.getInputOutput(usersInfo.getId(), childId, inputOutputDate));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "부모: 해당월 총 출금 금액 조회", description = "/yyyy-MM 형식으로 입력하게 되면 입력 달에 해당하는 총 출금금액을 조회합니다.", tags = { "InputOutput Controller" })
    @PostMapping("/{childId}/total/out/{inputOutputDate}")
    public ResponseEntity<MonthOutputResponseDto> getTotalMonthOutput(@UserInfo UsersInfo usersInfo, @PathVariable Long childId ,@PathVariable String inputOutputDate) {
        return ResponseEntity.ok(inputOutputParentService.getTotalMonthOutput(usersInfo.getId(), childId, inputOutputDate));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "부모: 해당월 총 입금 금액 조회", description = "/yyyy-MM 형식으로 입력하게 되면 입력 달에 해당하는 총 입금금액을 조회합니다.", tags = { "InputOutput Controller" })
    @PostMapping("/{childId}/total/in/{inputOutputDate}")
    public ResponseEntity<MonthOutputResponseDto> getTotalMonthInput(@UserInfo UsersInfo usersInfo, @PathVariable Long childId ,@PathVariable String inputOutputDate) {
        return ResponseEntity.ok(inputOutputParentService.getTotalMonthInput(usersInfo.getId(), childId, inputOutputDate));
    }
}
