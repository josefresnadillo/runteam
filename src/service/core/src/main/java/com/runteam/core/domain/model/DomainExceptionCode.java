package com.runteam.core.domain.model;

public enum DomainExceptionCode {
    TOO_MUCH_TEAMS("Too much teams for subscription type"),
    TOO_MUCH_CHALLENGES("Too much challenges for subscription type");

    private final String msg;

    DomainExceptionCode(final String msg){
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
