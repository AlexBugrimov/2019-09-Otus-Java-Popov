# Домашнее задание №15 "MessageSystem"
## Условие
Добавить систему обмена сообщениями в ДЗ про веб сервер с IoC контейнером.
Пересылать сообщения из вебсокета в DBService и обратно.

## Решение
1. Скопировано решение из `hw13-ioc`.
2. Скопирована система сообщений из репозитория вебинара.
3. Доработана конфигурация бинов для возможности использования системы сообщений.
4. Создан контроллер `UserWSController` для взаимодействия с фронтендом через вебсокет. Контроллер принимает
запросы и вызывает методы `FrontendService`, который отправляет сообщения в систему обмена сообщениями. 
При получении ответов происходит отправка результатов в вебсокеты. Реализованы методы для получения данных 1 и
всех пользователей, а также создание нового пользователя.
5. Доработана страница админа для работы с вебсокетом через библиотеку STOMP.
6. Для входа использовать login: admin, password: 123