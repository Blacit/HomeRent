package task.homerent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 Регистрацию по почте с подтверждением
 Повторный ввод пароль
 -------------------------------------
 Список свободных квартир, которые можно арендовать
 Сделать тесты на эти 2 контроллеры с арендой и списком свободных квартир
 Поработать с докером, развернуть своё приложение в нём
 Сделать сервис, который будет обращаться к моему. С очередями поработать, JMC, KAFKA и подобное.
 JMC почитать
 petclinic
 обработчик ошибок
 Использовать liquebase

 Сделал:
 1. Скрипты для БД, заполненные таблицы
 2. Убрал репозитории из контроллеры и сделал сервисы
 3. Авторизация по почте + паролю
 4. Добавил корректный вариант для отображения SQL-запросов в консоли
 5. Метод работает: поиск свободных квартир

 Вопросы:
 1. Как можно их всех объединить, просто в один файл закинуть? Как более корректно директорию назвать?
 2. Либо же вместо того, чтобы так инициализировать свою базу, лучше использовать liquebase, она ведь тоже позволяет создавать БД по параметрам с помощью SQL
 3. Как правильно назвать папку Controller, там ведь регистрация и авторизация + rest
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
