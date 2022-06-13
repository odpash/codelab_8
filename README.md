# [ИТМО ПИиКТ] Программирование (лр.7, лр.8)

## Варианты: 
* Лабораторная №7 (PostgreSql) - 3114922
* Лабораторная №8 (Swing + Java class) - 3114923
* Лабораторная №8 (JavaFX + Config file) - 3114924

## Задания
### Задания по лабораторной работе №7:
1) Организовать хранение коллекции в реляционной СУБД (PostgresQL). Убрать хранение коллекции в файле.
2) Для генерации поля id использовать средства базы данных (sequence).
3) Обновлять состояние коллекции в памяти только при успешном добавлении объекта в БД
4) Все команды получения данных должны работать с коллекцией в памяти, а не в БД
5) Организовать возможность регистрации и авторизации пользователей. У пользователя есть возможность указать пароль.
6) Пароли при хранении хэшировать алгоритмом SHA-256
7) Запретить выполнение команд не авторизованным пользователям.
8) При хранении объектов сохранять информацию о пользователе, который создал этот объект.
9) Пользователи должны иметь возможность просмотра всех объектов коллекции, но модифицировать могут только принадлежащие им.
10) Для идентификации пользователя отправлять логин и пароль с каждым запросом.
11) Необходимо реализовать многопоточную обработку запросов.
#### Дополнительные условия:
* Для многопоточного чтения запросов использовать Fixed thread pool
* Для многопотчной обработки полученного запроса использовать Cached thread pool
* Для многопоточной отправки ответа использовать Cached thread pool
* Для синхронизации доступа к коллекции использовать синхронизацию чтения и записи с помощью java.util.concurrent.locks.ReentrantLock

### Общее задание по лабораторной работе №8:
#### В функционал клиента должно входить:
1) Окно с авторизацией/регистрацией.
2) Отображение текущего пользователя.
3) Таблица, отображающая все объекты из коллекции
4) Каждое поле объекта - отдельная колонка таблицы.
5) Строки таблицы можно фильтровать/сортировать по значениям любой из колонок. Сортировку и фильтрацию значений столбцов реализовать с помощью Streams API.
6) Поддержка всех команд из предыдущих лабораторных работ.
7) Область, визуализирующую объекты коллекции
8) Объекты должны быть нарисованы с помощью графических примитивов с использованием Graphics, Canvas или аналогичных средств графической библиотеки.
9) При визуализации использовать данные о координатах и размерах объекта.
10) Объекты от разных пользователей должны быть нарисованы разными цветами.
11) При нажатии на объект должна выводиться информация об этом объекте.
12) При добавлении/удалении/изменении объекта, он должен автоматически появиться/исчезнуть/измениться  на области как владельца, так и всех других клиентов. 
13) При отрисовке объекта должна воспроизводиться согласованная с преподавателем анимация.
14) Возможность редактирования отдельных полей любого из объектов (принадлежащего пользователю). Переход к редактированию объекта возможен из таблицы с общим списком объектов и из области с визуализацией объекта.
15) Возможность удаления выбранного объекта (даже если команды remove ранее не было).


### Дополнительные условия (Swing + Java class):
* Интерфейс должен быть реализован с помощью библиотеки Swing
* Графический интерфейс клиентской части должен поддерживать русский, румынский, латвийский и испанский (Мексика) языки / локали. Должно обеспечиваться корректное отображение чисел, даты и времени в соответстии с локалью. Переключение языков должно происходить без перезапуска приложения. Локализованные ресурсы должны храниться в классе.

### Дополнительные условия (JavaFx + Config file):
* Интерфейс должен быть реализован с помощью библиотеки JavaFX
* Графический интерфейс клиентской части должен поддерживать русский, эстонский, болгарский и английский (Ирландия) языки / локали. Должно обеспечиваться корректное отображение чисел, даты и времени в соответстии с локалью. Переключение языков должно происходить без перезапуска приложения. Локализованные ресурсы должны храниться в файле свойств.


