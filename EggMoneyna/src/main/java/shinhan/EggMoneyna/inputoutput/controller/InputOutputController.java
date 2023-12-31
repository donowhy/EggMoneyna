package shinhan.EggMoneyna.inputoutput.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shinhan.EggMoneyna.inputoutput.dto.*;
import shinhan.EggMoneyna.inputoutput.service.InputOutputService;
import shinhan.EggMoneyna.jwt.UserInfo;
import shinhan.EggMoneyna.jwt.UsersInfo;

import java.io.IOException;

@RestController
@RequestMapping("/v1/inputoutput")
@RequiredArgsConstructor
public class InputOutputController{
    private final InputOutputService inputOutputService;

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "입금 추가", description = "사용자 계좌에 입금이 추가됩니다.", tags = { "InputOutput Controller" })
    @PostMapping("/in")
    public ResponseEntity<AddInputOutputResponseDto> addInput(@UserInfo UsersInfo usersInfo, @RequestBody AddInputOutRequestDto addInputOutRequestDto) throws IOException{
        return ResponseEntity.ok(inputOutputService.addInput(usersInfo.getId(), addInputOutRequestDto));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "출금 추가", description = "사용자 계좌에 출금이 추가됩니다.", tags = { "InputOutput Controller" })
    @PostMapping("/out")
    public ResponseEntity<AddInputOutputResponseDto> addOutput(@UserInfo UsersInfo usersInfo, @RequestBody AddInputOutRequestDto addInputOutRequestDto) throws IOException{
        return ResponseEntity.ok(inputOutputService.addOutput(usersInfo.getId(), addInputOutRequestDto));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "당일 입금 조회", description = "/yyyy-MM-dd로 조회한 사용자 계좌에 해당하는 날짜의 입금내역을 조회합니다.", tags = { "InputOutput Controller" })
    @PostMapping("/in/{inputDate}")
    public ResponseEntity<InputOutputResponseDto> getInput(@UserInfo UsersInfo usersInfo, @PathVariable String inputDate) {
        return ResponseEntity.ok(inputOutputService.getInput(usersInfo.getId(), inputDate));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "당일 출금 조회", description = "/yyyy-MM-dd로 조회한 사용자 계좌에 해당하는 날짜의 출금내역을 조회합니다.", tags = { "InputOutput Controller" })
    @PostMapping("/out/{outputDate}")
    public ResponseEntity<InputOutputResponseDto> getOutput(@UserInfo UsersInfo usersInfo, @PathVariable String outputDate) {
        return ResponseEntity.ok(inputOutputService.getOutput(usersInfo.getId(), outputDate));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "당일 입출금 조회", description = "/yyyy-MM-dd로 조회한 사용자 계좌에 해당하는 날짜의 출금내역을 조회합니다.", tags = { "InputOutput Controller" })
    @PostMapping("/all/{inputOutputDate}")
    public ResponseEntity<InputOutputResponseDto> getInputOutput(@UserInfo UsersInfo usersInfo, @PathVariable String inputOutputDate) {
        return ResponseEntity.ok(inputOutputService.getInputOutput(usersInfo.getId(), inputOutputDate));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "해당월 총 출금 금액 조회", description = "/yyyy-MM 형식으로 입력하게 되면 입력 달에 해당하는 총 출금금액을 조회합니다.", tags = { "InputOutput Controller" })
    @PostMapping("/total/out/{inputOutputDate}")
    public ResponseEntity<MonthOutputResponseDto> getTotalMonthOutput(@UserInfo UsersInfo usersInfo, @PathVariable String inputOutputDate) {
        return ResponseEntity.ok(inputOutputService.getTotalMonthOutput(usersInfo.getId(), inputOutputDate));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "해당월 총 입금 금액 조회", description = "/yyyy-MM 형식으로 입력하게 되면 입력 달에 해당하는 총 입금금액을 조회합니다.", tags = { "InputOutput Controller" })
    @PostMapping("/total/in/{inputOutputDate}")
    public ResponseEntity<MonthOutputResponseDto> getTotalMonthInput(@UserInfo UsersInfo usersInfo, @PathVariable String inputOutputDate) {
        return ResponseEntity.ok(inputOutputService.getTotalMonthInput(usersInfo.getId(), inputOutputDate));
    }

//    @GetMapping("/test")
//    public ResponseEntity<String> getTest() throws IOException, ParseException {
//        return ResponseEntity.ok(inputOutputService.getApi());
//    }

}
