package com.selflearning.englishcourses.util;

public enum  QuestionType implements Option {

    TEXT(0, "Text"),
    AUDIO(1, "Audio"),
    IMAGE(3, "Image");

    private int value;

    private String label;

    QuestionType(int value, String label) {
        this.value = value;
        this.label = label;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String getLabel() {
        return label;
    }
}
