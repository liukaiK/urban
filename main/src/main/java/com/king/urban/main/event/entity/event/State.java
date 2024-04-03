package com.king.urban.main.event.entity.event;

import javax.persistence.Embeddable;

@Embeddable
public abstract class State {

    private String state;

    public abstract void doAction();

}
