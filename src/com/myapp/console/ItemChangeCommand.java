package com.myapp.console;

import com.myapp.model.Item2d;

/**
 * Команда "Change item".
 * Реалізує шаблон Command.
 * 
 * Автор: xone
 * Версія: 1.0
 */
public class ItemChangeCommand implements ICommand {
    /**
     * Об'єкт, що обробляється (шаблон Command).
     */
    private Item2d targetItem;

    /**
     * Параметр команди (шаблон Command).
     */
    private double scaleFactor;

    /**
     * Встановлює значення поля {@linkplain ItemChangeCommand#targetItem}.
     *
     * @param item значення для поля targetItem
     * @return нове значення поля targetItem
     */
    public Item2d setTargetItem(Item2d item) {
        return this.targetItem = item;
    }

    /**
     * Повертає значення поля {@linkplain ItemChangeCommand#targetItem}.
     *
     * @return значення поля targetItem
     */
    public Item2d getTargetItem() {
        return targetItem;
    }

    /**
     * Встановлює значення поля {@linkplain ItemChangeCommand#scaleFactor}.
     *
     * @param factor значення для поля scaleFactor
     * @return нове значення поля scaleFactor
     */
    public double setScaleFactor(double factor) {
        return this.scaleFactor = factor;
    }

    /**
     * Повертає значення поля {@linkplain ItemChangeCommand#scaleFactor}.
     *
     * @return значення поля scaleFactor
     */
    public double getScaleFactor() {
        return scaleFactor;
    }

    @Override
    public void execute() {
        targetItem.setY(targetItem.getY() * scaleFactor);
    }
}
