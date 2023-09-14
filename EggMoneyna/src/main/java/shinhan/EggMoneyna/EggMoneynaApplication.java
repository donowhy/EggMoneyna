package shinhan.EggMoneyna;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients
@SpringBootApplication
@EnableScheduling
public class EggMoneynaApplication {

	/**
	 * 해당 설정을 하지 않을 경우 AWS S3서비스가 실행되는 시점에 약간의 지연과 예외 메세지 발생
	 */
	static {
		System.setProperty("com.amazonaws.sdk.disableEc2Metadata", "true");
	}

	public static void main(String[] args) {
		SpringApplication.run(EggMoneynaApplication.class, args);
	}

	@Configuration
	@EnableJpaAuditing // JPA Auditing 활성화
	public class JpaConfig {
	}
}
