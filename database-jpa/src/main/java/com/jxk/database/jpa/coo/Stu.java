package com.jxk.database.jpa.coo;

import lombok.*;

@Builder
@Data
public class Stu   {

    private Integer age;
    private String name;
    private Integer flag;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stu stu = (Stu) o;

        if (age != null ? !age.equals(stu.age) : stu.age != null) return false;
        return name != null ? name.equals(stu.name) : stu.name == null;
    }

    @Override
    public int hashCode() {
        int result = age != null ? age.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
