package com.adhiraj.dsaalgo.leetcode;

import java.util.Objects;

public class Triplet {
    public int first;
    public int second;
    public int third;

    public  Triplet (int first, int second, int third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Triplet))
            return false;
        Triplet x = (Triplet) o;
        if (this.first == x.first && this.second == x.second && this.third == x.third) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.first, this.second, this.third);
    }

    public String toString() {
        return "{" + first + ", " + second + ", " + third + "}";
    }
}