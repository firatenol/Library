# 📚 Library API

REST API для управления библиотечным каталогом с поддержкой кэширования Redis и тестированием.

## 🚀 Технологии

| Технология | Версия | Назначение |
|------------|--------|------------|
| Java | 21 | Основной язык |
| Spring Boot | 3.3.5 | Фреймворк |
| Spring Data JPA | - | Работа с БД |
| Spring Cache | - | Кэширование |
| PostgreSQL | 17 | Основная БД |
| Redis | 7 | Кэш |
| MapStruct | 1.5.5 | Маппинг DTO |
| Lombok | 1.18.28 | Генерация кода |
| Swagger/OpenAPI | 2.6.0 | Документация API |
| JUnit 5 / Mockito | - | Тестирование |
## 📁 Структура проекта
- **Library**
  - `config` — конфигурация приложения (Redis)
  - `controller` — REST API слой, обработка HTTP-запросов
  - `dto` — объекты передачи данных (Request / Response модели)
  - `entity` — JPA сущности, отображение таблиц базы данных
  - `repository` — доступ к PostgreSQL через Spring Data JPA
  - `service` — бизнес-логика приложения
  - `mapper` — преобразование Entity ↔ DTO (MapStruct)
  - `exception` — глобальная обработка ошибок и исключений
  - `LibraryApplication` — точка входа в Spring Boot приложение
## 📖 API Эндпоинты
| **Метод**	 | **Адрес**| **Что делает**|
|------------|--------|------------|
|GET|	/api/books/{id}|	Получить книгу (с кэшем)|
|GET|	/api/books	|Получить все книги|
|POST|	/api/books|	Создать книгу|
|DELETE|	/api/books/{id}	|Удалить книгу|
