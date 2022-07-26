package com.crud.tasks.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class TrelloValidatorTest {

    @Autowired
    private TrelloValidator trelloValidator;

    @Test
    public void validateCardTest(){
        //Given
        TrelloCard trelloCard1 = new TrelloCard("test", "task_test", "top", "1");
        TrelloCard trelloCard2 = new TrelloCard("task", "task_test", "top", "2");

        //When

        //Then
        trelloValidator.validateCard(trelloCard1);
        trelloValidator.validateCard(trelloCard2);
    }

    @Test
    public void shouldValidateTrelloBoards() {
        //Given
        TrelloBoard trelloBoard1 = new TrelloBoard("1", "test", new ArrayList<>());
        TrelloBoard trelloBoard2 = new TrelloBoard("2", "task1", new ArrayList<>());
        TrelloBoard trelloBoard3 = new TrelloBoard("3", "task2", new ArrayList<>());

        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(trelloBoard1);
        trelloBoardList.add(trelloBoard2);
        trelloBoardList.add(trelloBoard3);

        //When
        List<TrelloBoard> trelloBoardListAfterValidate = trelloValidator.validateTrelloBoards(trelloBoardList);

        //Then
        assertEquals(2, trelloBoardListAfterValidate.size());
    }
}
