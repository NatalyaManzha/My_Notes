Задание:
Приложение «Заметки» должно иметь возможность:
• Добавлять архивы (наборы заметок);
• Добавлять заметки в архивы;
• Просматривать заметки.
Важно, чтобы пользователь мог в любой момент выйти с экрана выбора архива, заметок или заметки и попасть на предыдущий список.
Итого получится пять экранов:
1. Выбор архива.
2. Создание архива.
3. Выбор заметки.
4. Создания заметки.
5. Экран заметки. На экране заметки должна быть возможность вывода текста заметки.
Эти пять экранов условно разделим на две группы:
1. Выбор элемента из списка и создание объекта.
2. Выбор архива, выбор заметки, экран заметки — это меню выбора.
Например, выбор архива выглядит так:

Список архивов:
0. Создать архив
1. Открыть архив
2. Выход

Такое количество экранов похоже на навигацию в реальном приложении — у пользователя всегда есть возможность выйти с любого экрана или пойти дальше.
Важно: выход из программы есть только один. Во всех остальных случаях мы возвращаемся на предыдущий экран.
Выбор пункта меню на каждом экране должен корректно реагировать на неправильный ввод:
• Если человек ввёл не цифру, то программа должна сказать ему, что следует вводить цифру, и ещё раз показать пункты меню.
• Если человек ввёл цифру, но такого элемента нет на экране, то программа должна сказать, что такой цифры нет, снова показать пункты меню и предложить ввести корректный символ.
Для каждого экрана нужно написать всю эту логику. Но мы знаем, как избежать повторов в коде, поэтому все экраны с выбором элементов в меню сделаем через общий код.
Можно заметить, что у экранов выбора общая навигация и ввод. Именно это и надо вынести в отдельный класс.
Критерии успешного выполнения задания:
• Есть меню с возможностью добавления и просмотра архивов.
• Есть меню с возможностью добавления и просмотра заметок.
• Есть возможность добавлять и просматривать текст заметок.
• Не должно быть повторений одного и того же кода. Вся логика по считыванию ввода пользователя и вывода пунктов на экран должна переиспользовать один и тот же код.
• Ошибочный ввод пользователя должен корректно обрабатываться.
• Приложение не позволяет создавать меню или заметку без имени (с пустым именем).
• Приложение не позволяет создавать заметку без содержания (с пустым текстом).
• Из любого меню можно выйти и попасть на предыдущее меню или выйти из программы, если это просмотр архива.
• Приложение успешно компилируется и выполняется без ошибок.
• Весь код не написан в одном файле Main.
• Весь код приложения написан на Kotlin.
Подсказки
1. Рекомендуем начать с самого первого меню — архивы. Затем переместить общую логику в отдельный класс и свериться с требованиями к заданию. Далее оставшиеся меню будет написать намного легче.
2. Каждое меню советуем делать в отдельном файле, чтобы проще было ориентироваться.
3. Для переиспользования общего кода рекомендуем использовать отдельный класс, который содержит код:
◦ По выводу пунктов меню;
◦ По чтению пользовательского ввода;
◦ По выполнению определённых операций на выбор пункта меню.
Для всего этого советуем использовать изменяемый список, который содержит в себе название пункта и лямбду, которая вызовется при выборе этого пункта.
4. Для ввода стоит использовать бесконечный цикл, который повторяется до тех пор, пока пользователь не выберет выход.
5. Все данные должны храниться в памяти и удаляться после завершения программы.
6. Само чтение из консоли можно реализовать через Scanner. Для этого добавьте в начало файла import java.util.Scanner и в месте кода, где хотите прочитать строчку из консоли, введите Scanner(System.`in`).nextLine().
