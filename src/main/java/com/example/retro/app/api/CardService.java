package com.example.retro.app.api;

import com.example.retro.domain.Color;

public interface CardService {
    /**
     * Изменить текст карточки
     * @param id - id редактируемой карточки
     * @param text - новый текст
     */
    void changeText(Long id, String text);
    /**
     * Изменить цвет карточки
     * @param color - новый цвет
     */
    void changeColor(Color color);

    void upVote();
}
