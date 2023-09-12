package shinhan.EggMoneyna.firebase;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shinhan.EggMoneyna.users.entity.Users;
import shinhan.EggMoneyna.users.repository.UsersRepository;

@RequiredArgsConstructor
@Service
public class FCMNotificationService {

    private final FirebaseMessaging firebaseMessaging;
    private final UsersRepository usersRepository;

    public String sendNotificationByToken(FCMNotificationRequestDto requestDto) {

        Users users = usersRepository.findById(requestDto.getTargetUserId()).orElseThrow();

        if (users.getFirebaseToken() != null) {
            Notification notification = Notification.builder()
                    .setTitle(requestDto.getTitle())
                    .setBody(requestDto.getBody())
                    .build();

            Message message = Message.builder()
                    .setToken(users.getFirebaseToken())
                    .setNotification(notification)
                    .build();

            try {
                firebaseMessaging.send(message);
            } catch (FirebaseMessagingException e) {
                throw new RuntimeException(e);
            }
        } else {
            return "Token 없머";
        }
        return "뭐";
    }
}
