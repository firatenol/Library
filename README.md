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
src/
├── main/
│ └── java/com/example/Library/
│ ├── config/ # Конфигурации (Redis, Cache)
│ ├── Controller/ # REST контроллеры
│ ├── Dto/ # Data Transfer Objects
│ ├── Entity/ # JPA сущности
│ ├── exception/ # Глобальный обработчик ошибок
│ ├── map/ # MapStruct мапперы
│ ├── Repository/ # JPA репозитории
│ ├── Service/ # Бизнес-логика
│ └── LibraryApplication.java # Точка входа
│
└── test/
└── java/com/example/Library/
├── Controller/ # Тесты контроллеров
├── Service/ # Юнит-тесты сервисов
└── LibraryApplicationTests.java

## 📖 API Эндпоинты
Метод	Эндпоинт	Описание
GET	/api/books/{id}	Получить книгу по ID (с кэшированием)
GET	/api/books	Получить все книги
POST	/api/books	Создать новую книгу
DELETE	/api/books/{id}	Удалить книгу

