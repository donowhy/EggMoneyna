package shinhan.EggMoneyna.firebase;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/fcm")
public class FCMController {

    private final FCMNotificationService fcmNotificationService;

    @PostMapping()
    public String sendMessaging(@RequestBody FCMNotificationRequestDto requestDto){
        return fcmNotificationService.sendNotificationByToken(requestDto);
    }

}
