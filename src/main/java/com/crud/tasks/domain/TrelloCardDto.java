package com.crud.tasks.domain;

import lombok.Data;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Data
public class TrelloCardDto {

    private String name;
    private String description;
    private String pos;
    private String listId;
    private Badges badges;



}
