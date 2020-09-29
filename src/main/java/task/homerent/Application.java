package task.homerent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 Регистрацию по почте с подтверждением
 Повторный ввод пароль
 -------------------------------------
 Поработать с докером, развернуть своё приложение в нём
 Сделать сервис, который будет обращаться к моему. С очередями поработать, JMC, KAFKA и подобное.
 JMC почитать
 Как правильно назвать папку Controller, там ведь регистрация и авторизация + rest
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
