# Экспертная система для определения болезни по симптомам #

### Средства разработки: ###
* Java 8
* Spring MVC
* JDBC
* MySQL
* Thymeleaf
* jQuery
* Bulma.css
* Font Awesome
* Gradle

Инструкция:
```bash
$ ./gradlew build
$ cd build/libs
$ java -jar tk.burdukowsky.es-1.0-SNAPSHOT.jar
```

Приложение будет доступно по адресу: `http://localhost:8080`.

Для работы приложения требуется база данных MySQL. Скрипты создания и вставки данных находятся в директории `sql` (`create_database.sql` и `insert_data.sql`). Конфигурация подключения задается в файле "`src/main/java/tk/burdukowsky/es/config/ContextConfiguration.java`".

**Для наполнения БД используются данные, которые носят лишь демонстрационный характер. Не рекомендуется использовать ЭС с этими данными для реальной постановки диагноза.**
