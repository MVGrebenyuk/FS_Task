# FS_Task
Тестовое задание для МосБиржи

# Для добавления новых сервисов, типа  gosuslugi
Необходимо добавить в .json файл validationRules наименование новой структуры и поля для валидации
Каждая система может самостоятельно задавать проверку валидируемых полей
В application.yaml необходимо также включить наименование новой системы

# Для поиска по нескольким критериям отдал предпочтение Specification API
# Docker - compose готов к запуску

#JSON для тестирования запросов
{
    "bankId": 1,
    "surname": "test",
    "name": "Oleg",
    "lastName": "lastname",
    "birthday": [
        2022,
        10,
        10
    ],
    "passNumber": "1417 777777",
    "city": "Voronezh",
    "phone": "+79192301244",
    "email": "Maxim@mail.ru",
    "registration": "some address",
    "currentAddress": "someAddress"
}
