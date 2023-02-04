package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // задание полей
    float telescope = 14000; // стоимость телескопа
    int account = 1000; // счёт пользователя
    float wage = 2500; // заработная плата в месяц
    int percentFree = 500; // доля заработной платы на любые траты
    float percentBank = 5; // годовая процентная ставка за ипотеку
    float[] monthlyPayments = new float[120]; // создание массива ежемемесячных платежей на 10 лет

    // метод подсчёта стоимости квартиры с учётом первоначального взноса
    private float apartmentPriceWithContribution() {
        return telescope - account; // возврат подсчитанного значения
    }

    // метод подсчёта ежемесячных трат на ипотеку (зар.плата, процент своб.трат)
    public float mortgageCosts(float amount, int percent) {
        return (amount * percent) / 100;
    }

    // метод подсчёта времени выплаты ипотеки (сумма долга, сумма платежа, годовой процент)
    // и заполнение массива monthlyPayments[] ежемесячными платежами
    public int countMonth(float total, float mortgageCosts, float percentBankYear) {

        float percentBankMonth = percentBankYear / 12; // подсчёт ежемесячного процента банка за ипотеку
        int count = 0; // счётчик месяцев выплаты ипотеки

        // алгоритм расчёта ипотеки
        while (total > 0) {
            count++; // добавление нового месяца платежа
            total = (total + (total * percentBankMonth) / 100) - mortgageCosts; // вычисление долга с учётом выплаты и процента
            // заполнение массива ежемесячными платежами за ипотеку
            if (total > mortgageCosts) { // если сумма долга больше ежемесячного платежа, то
                monthlyPayments[count - 1] = mortgageCosts; // в массив добавляется целый платёж
            } else { // иначе
                monthlyPayments[count - 1] = total; // в массив добавляется платёж равный остатку долга
            }
        }

        return count;
    }

    // создание дополнительных полей для вывода на экран полученных значений
    private TextView countOut; // поле вывода количества месяцев до покупки телескопа

    // вывод на экран полученных значений
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) { // создание жизненного цикла активности
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // присваивание жизненному циклу активити представления activity_main

        // присваивание переменным активити элементов представления activity_main
        countOut = findViewById(R.id.countOut); // вывод информации количества месяцев выплаты ипотеки

        // запонение экрана
        // 1) вывод количества месяцев выплаты ипотеки
        countOut.setText("Телескоп можно купить через " + countMonth(apartmentPriceWithContribution(), mortgageCosts(wage, percentFree), percentBank) + " месяца");



    }
}