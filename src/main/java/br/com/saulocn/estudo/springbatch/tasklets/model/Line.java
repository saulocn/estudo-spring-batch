package br.com.saulocn.estudo.springbatch.tasklets.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Line implements Serializable {
    private String name;
    private LocalDate birthDay;
    private Long age;

    public Line(final String name, final LocalDate birthDay, final Long age) {
        this.name = name;
        this.birthDay = birthDay;
        this.age = age;
    }
    public Line(final String name, final LocalDate birthDay) {
        this.name = name;
        this.birthDay = birthDay;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(final LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(final Long age) {
        this.age = age;
    }

    @Override public String toString() {
        final StringBuilder builder = new StringBuilder()//
                .append("Line [")//
                .append("name=\"")//
                .append(name + "\"")//
                .append(",birthDay=")//
                .append(birthDay)//
                .append(",age=")//
                .append(age)//
                .append("]");
        return builder.toString();
    }
}
