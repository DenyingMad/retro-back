package com.example.retro.app.impl;

import com.example.retro.adapter.jpa.CardRepository;
import com.example.retro.app.api.CardService;
import com.example.retro.domain.Card;
import com.example.retro.domain.Color;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;

    @Override
    @Transactional
    public void changeText(Long id, String text) {
        Card card = cardRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException(String.format("Card with id %s not found", id)));
        card.setText(text);
    }

    @Override
    public void changeColor(Color color) {
    }

    @Override
    public void upVote() {

    }
}
