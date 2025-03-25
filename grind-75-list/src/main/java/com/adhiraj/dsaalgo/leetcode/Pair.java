package com.adhiraj.dsaalgo.leetcode;

import lombok.ToString;

import java.util.Objects;

@ToString
public class Pair {

    public int first;
    public int second;

    public  Pair (int first, int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (!(o instanceof Pair))
            return false;

        Pair x = (Pair) o;

        if (this.first == x.first && this.second == x.second) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.first, this.second);
    }
}
