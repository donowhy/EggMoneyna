//package shinhan.EggMoneyna.monster.Controller;
//
//import io.swagger.v3.oas.annotations.Operation;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import shinhan.EggMoneyna.monster.dto.MonsterResponseDto;
//import shinhan.EggMoneyna.monster.dto.MonsterSaveRequestDto;
//import shinhan.EggMoneyna.monster.dto.MonsterSaveResponseDto;
//import shinhan.EggMoneyna.monster.dto.MonsterUpdateResponseDto;
//import shinhan.EggMoneyna.monster.service.MonsterService;
//
//@Slf4j
//@RequiredArgsConstructor
//@RestController
//@RequestMapping("/v1")
////@RequestMapping
//public class MonsterController {
//    private MonsterService monsterService;
//
//    // 게시글 작성
//    @Operation(summary = "신한몽 생성", description = "신한몽이 생성됩니다.", tags = { "Monster Controller" })
//    @PostMapping("/monsters")
//    public ResponseEntity<MonsterSaveResponseDto> createMonster(@RequestBody MonsterSaveRequestDto requestDto) {
//        MonsterSaveResponseDto responseDto = monsterService.save(requestDto);
//        return ResponseEntity.ok(responseDto);
//    }
//
//    // READ
//    @Operation(summary = "신한몽 조회", description = "신한몽 조회.", tags = { "Monster Controller" })
//    @GetMapping("/{id}")
//    public ResponseEntity<MonsterResponseDto> getMonster(@PathVariable Long id) {
//        MonsterResponseDto responseDto = monsterService.findById(id);
//        return ResponseEntity.ok(responseDto);
//    }
//
//    // UPDATE
//    @Operation(summary = "신한몽 닉네임 변경", description = "신한몽 닉네임 변경.", tags = { "Monster Controller" })
//    @PutMapping("/{id}")
//    public ResponseEntity<MonsterUpdateResponseDto> updateMonster(@PathVariable Long id, @RequestBody String nickName) {
//        MonsterUpdateResponseDto updatedMonster = monsterService.update(id, nickName);
//        return ResponseEntity.ok(updatedMonster);
//    }
//
//    // DELETE
//    @Operation(summary = "신한몽 삭제", description = "신한몽이 삭제.", tags = { "Monster Controller" })
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteMonster(@PathVariable Long id) {
//        monsterService.deleteById(id);
//        return ResponseEntity.ok("삭제 성공");
//    }
//
//
//}
