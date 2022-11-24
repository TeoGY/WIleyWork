package com.sg.classroster.ui;

public interface UserIO {
    void print(String msg);

    double readDouble(String prompt);

    double readDouble(String prompt, double min, double max);

    long readLong(String msgPrompt, long min, long max);

    float readFloat(String prompt);

    float readFloat(String  prompt, float min, float max);

    int readInt(String prompt);

    int readInt(String  prompt, float min, float max);

    int readInt(String msgPrompt, int min, int max);

    long readLong(String prompt);

    long readLong(String prompt, float min, float max);

    String readString(String prompt);
}
