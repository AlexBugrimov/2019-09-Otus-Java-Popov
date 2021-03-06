package ru.otus.hw07;

import ru.otus.hw07.atm.ATM;

/** Департамент ATM. */
public interface ATMDepartment {

    /** Поиска банкомата по номеру. */
    ATM find(int number);

    /** Восстановление всех ATM до начального состояния. */
    void resetATMs();

    /** Получение суммы остатков со всех АТМ департамента. */
    int collectBalance();
}
