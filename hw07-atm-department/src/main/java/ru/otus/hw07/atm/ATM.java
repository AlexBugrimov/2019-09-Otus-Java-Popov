package ru.otus.hw07.atm;

import ru.otus.hw07.ATMDepartmentCommandListener;

import java.util.Collection;

/** Банкомат. */
public interface ATM extends Balance, ATMDepartmentCommandListener {

    /** Номер данного банкомата.*/
    int getNumber();

    /** Загрузка банкнот в банкомат. */
    void load(Collection<Banknote> banknotes);

    /** Выдача минимального количества банкнот по запрошенной сумме. */
    Collection<Banknote> cashOut(int amount);

    /** Сброс состояния банкомата до начального. */
    void reset();
}
