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
 petclinic
 обработчик ошибок

 Вопросы:
 1. Как правильно назвать папку Controller, там ведь регистрация и авторизация + rest
 2. Какие тесты можно ещё придумать
 3. Баг:
 Загружаю приложение вместе с базой данных.
 Делаю запрос на аренду квартиры (с этим тестил) и ловлю ошибку:  ОШИБКА: повторяющееся значение ключа нарушает ограничение уникальности “contract_pkey”
 Также номер id (1) 2,3,4,5 и так далее, пока есть в таблице столько, сколько было во время создания.
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
