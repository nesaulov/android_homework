package ru.esaulov.sport;

/**
 * Created by nesaulov on 22/01/2018.
 */

public class Sport {
    private String name;
    private String description;
    private int imageResourceId;

    public static final Sport[] sports = {
            new Sport("Футбол", "Спортивная игра двух команд (по одиннадцати игроков в каждой), состоящая в том, что игроки стараются ударами ноги загнать мяч в ворота противника.", R.mipmap.football),
            new Sport("Хоккей", "Командная игра на льду на коньках в небольшой мяч или шайбу, ударяемые клюшкой.", R.mipmap.hockey),
            new Sport("Волейбол", "Спортивная игра в мяч, перебрасываемый руками через сетку от одной команды к другой.", R.mipmap.volleyball),
            new Sport("Регби", "Спортивная командная игра с овальным мячом, который игроки передают друг другу руками и ногами, стараясь пересечь линию ворот противника.", R.mipmap.rugby),
            new Sport("Танцы", "Ритмичные, выразительные телодвижения, обычно выстраиваемые в определённую композицию и исполняемые с музыкальным сопровождением. ", R.mipmap.dancing),
            new Sport("Баскетбол", "Игра, в которой мяч забрасывают руками в подвешенную сетку (называемую корзиной) противной партии.", R.mipmap.basketball),
            new Sport("Теннис", "Спортивная игра маленьким мячом, который перебрасывается ракеткой через сетку, разделяющую площадку.", R.mipmap.tennis),
            new Sport("Плавание", "Вид спорта или спортивная дисциплина, заключающаяся в преодолении вплавь за наименьшее время различных дистанций.", R.mipmap.swimming),
            new Sport("Каратэ", "Спортивная японская борьба, система самозащиты без оружия.", R.mipmap.karate),
            new Sport("Бокс", "Кулачный бой в специальных перчатках по определённым правилам между двумя спортсменами.", R.mipmap.boxing)
    };

    private Sport(String name, String description, int imageResourceId) {
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String toString() {
        return this.name;
    }
}
