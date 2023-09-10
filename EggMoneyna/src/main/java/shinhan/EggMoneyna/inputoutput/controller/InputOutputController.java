package shinhan.EggMoneyna.inputoutput.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shinhan.EggMoneyna.inputoutput.dto.InputOutRequestDto;
import shinhan.EggMoneyna.inputoutput.dto.InputOutputResponseDto;
import shinhan.EggMoneyna.jwt.UserInfo;
import shinhan.EggMoneyna.jwt.UsersInfo;

@RestController
@RequestMapping("/v1/inputoutput")
public class InputOutputController {

    @Operation(summary = "입금 추가", description = "사용자 계좌에 입금이 추가됩니다.", tags = { "InputOutput Controller" })
    @PostMapping("/in")
    public ResponseEntity<InputOutputResponseDto> getIn(@UserInfo UsersInfo usersInfo, @RequestBody InputOutRequestDto inputOutRequestDto) {
        return ResponseEntity.ok(new InputOutputResponseDto());
    }

    @Operation(summary = "출금 추가", description = "사용자 계좌에 출금이 추가됩니다.", tags = { "InputOutput Controller" })
    @PostMapping("/out")
    public ResponseEntity<InputOutputResponseDto> getOut(@UserInfo UsersInfo usersInfo, @RequestBody InputOutRequestDto inputOutRequestDto) {
        return ResponseEntity.ok(new InputOutputResponseDto());
    }
}
