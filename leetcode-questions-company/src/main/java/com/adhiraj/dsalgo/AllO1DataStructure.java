package com.adhiraj.dsalgo;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

// LC-432
public class AllO1DataStructure {
}

class AllOne {

    Map<String, KeyFrequency> keyCount = new HashMap<>();
    TreeSet<KeyFrequency> keySet = new TreeSet<>(new KeyFrequencyComparator());

    public AllOne() {

    }

    public void inc(String key) {
        KeyFrequency kFreq = keyCount.get(key);
        if (kFreq == null) {
            kFreq = new KeyFrequency(key);
            keyCount.put(key, kFreq);
            keySet.add(kFreq);
        }
        keySet.remove(kFreq);
        kFreq.inc();
        keySet.add(kFreq);
    }

    public void dec(String key) {
        KeyFrequency kFreq = keyCount.get(key);
        keySet.remove(kFreq);
        kFreq.dec();

        if (kFreq.frequency != 0) keySet.add(kFreq);
        else keyCount.remove(kFreq.key);
    }

    public String getMaxKey() {
        if (keySet.isEmpty()) return "";
        return keySet.last().key;
    }

    public String getMinKey() {
        if (keySet.isEmpty()) return "";
        return keySet.first().key;
    }
}

class KeyFrequencyComparator implements Comparator<KeyFrequency> {
    public int compare(KeyFrequency a, KeyFrequency b) {
        int cmp = Integer.compare(a.frequency, b.frequency);
        if (cmp == 0) cmp = String.CASE_INSENSITIVE_ORDER.compare(a.key, b.key);
        return cmp;
    }
}

class KeyFrequency {
    String key;
    int frequency;

    KeyFrequency(String key) {
        this.key = key;
        this.frequency = 0;
    }

    void inc() {
        frequency++;
    }

    void dec() {
        frequency--;
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
