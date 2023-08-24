package com.king.urban.event;

public class TelePhoneSource implements Source {

    private String id;

    private String name;

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

}
