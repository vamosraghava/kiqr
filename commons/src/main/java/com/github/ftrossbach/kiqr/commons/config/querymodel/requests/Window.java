package com.github.ftrossbach.kiqr.commons.config.querymodel.requests;

/**
 * Created by ftr on 16/03/2017.
 */
public class Window implements Comparable<Window>{

    private long startMs;
    private long endMs;
    private String value;


    public Window(long startMs, long endMs, String value) {
        this.startMs = startMs;
        this.endMs = endMs;
        this.value = value;
    }

    public Window() {
    }

    public long getStartMs() {
        return startMs;
    }

    public void setStartMs(long startMs) {
        this.startMs = startMs;
    }

    public long getEndMs() {
        return endMs;
    }

    public void setEndMs(long endMs) {
        this.endMs = endMs;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int compareTo(Window o) {
        return Long.valueOf(startMs).compareTo(endMs);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Window window = (Window) o;

        if (startMs != window.startMs) return false;
        return endMs == window.endMs;
    }

    @Override
    public int hashCode() {
        int result = (int) (startMs ^ (startMs >>> 32));
        result = 31 * result + (int) (endMs ^ (endMs >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Window{" +
                "startMs=" + startMs +
                ", endMs=" + endMs +
                '}';
    }
}
