package com.king.urban.event.entity.event;


import jakarta.persistence.Embeddable;

@Embeddable
public abstract class State {

    private String state;

    public abstract void doAction();

}
