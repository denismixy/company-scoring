package ru.mikhailin.entity;

import lombok.Data;

@Data
public class ScoringOrder {
    private long id;
    private String inn;
    private int region;
    private long capital;
}
