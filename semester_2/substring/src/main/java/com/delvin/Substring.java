package com.delvin;

import java.util.*;
import java.util.function.ToIntFunction;

/**
 * 1. Boyer-Moore: {@link #boyerMoore(String, String)} <br>
 * 2. Rabin-Karp: {@link #rabinKarp(String, String, ToIntFunction)} <br>
 * 3. Knuth-Morris-Prat: {@link #knuthMorrisPratt(String, String)} <br>
 * 4. FSM: {@link #FSM(String, String)}
 */
public class Substring {
    /**
     * Find substring with Boyer-Moore algorithm
     * 
     * @param string    source string for search
     * @param substring pattern for search
     * @return list of offsets
     */
    public static List<Integer> boyerMoore(String string, String substring) {
        if (string == null || substring == null)
            throw new IllegalArgumentException("Strings can't be null's");

        int substringLength = substring.length();
        int stringLength = string.length();

        if (substringLength > stringLength || stringLength == 0)
            return new ArrayList<Integer>();

        if (substringLength == 0)
            throw new IllegalArgumentException("Substring for search can't be empty");

        Map<Character, Integer> offsets = new HashMap<>();

        for (int i = 1; i <= substringLength - 1; i++) {
            char ch = substring.charAt(substringLength - i - 1);
            if (!offsets.containsKey(ch))
                offsets.put(ch, i);
        }

        char ch = substring.charAt(substringLength - 1);
        if (!offsets.containsKey(ch))
            offsets.put(ch, substringLength);

        List<Integer> result = new ArrayList<>();

        int i = substringLength - 1;
        while (i < stringLength) {
            if (string.charAt(i) == substring.charAt(substringLength - 1)) {
                if (boyerMoorePrivate(string, i, substring)) {
                    result.add(i - substringLength + 1);
                    i++;
                } else
                    i += offsets.get(substring.charAt(substringLength - 1));
            } else
                i += offsets.getOrDefault(string.charAt(i), substringLength);
        }

        return result;
    }

    private static boolean boyerMoorePrivate(String string, int index, String substring) {
        for (int i = substring.length() - 1; i >= 0; i--) {
            if (string.charAt(index) == substring.charAt(i))
                index--;
            else
                return false;
        }
        return true;
    }

    /**
     * Find substring with Rabin-Karp algorithm
     * 
     * @param string
     * @param substring
     * @param hashFunction
     * @return
     */
    public static List<Integer> rabinKarp(String string, String substring, ToIntFunction<String> hashFunction) {
        if (string == null || substring == null || hashFunction == null)
            throw new IllegalArgumentException("Params can't be null");

        int stringLength = string.length();
        int substringLength = substring.length();

        if (substringLength > stringLength || stringLength == 0)
            return new ArrayList<Integer>();

        if (substringLength == 0)
            throw new IllegalArgumentException("Substring for search can't be empty");

        Integer hashSubstring = hashFunction.applyAsInt(substring);

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i <= stringLength - substringLength; i++) {
            String sub = string.substring(i, i + substringLength);
            Integer hashSub = hashFunction.applyAsInt(sub);
            if (hashSubstring.equals(hashSub))
                result.add(i);
        }
        return result;
    }

    /**
     * Find substring with Knuth Morris Pratt algorithm
     * 
     * @param string    string for search
     * @param substring pattern for search
     * @return
     */
    public static List<Integer> knuthMorrisPratt(String string, String substring) {
        if (string == null || substring == null)
            throw new IllegalArgumentException("Params can't be null");

        int stringLength = string.length();
        int substringLength = substring.length();

        if (substringLength > stringLength || stringLength == 0)
            return new ArrayList<Integer>();

        if (substringLength == 0)
            throw new IllegalArgumentException("Substring for search can't be empty");

        List<Integer> result = new ArrayList<>();
        int[] offsetTable = getOffsetTableByPrefix(substring);

        int vPointer = 0;
        for (int i = 0; i < stringLength; i++) {
            while (true) {
                if (substring.charAt(vPointer) == string.charAt(i)) {
                    vPointer++;
                    if (vPointer == substringLength) {
                        i = i + 1 - substringLength;
                        result.add(i);
                        vPointer = 0;
                    }
                    break;
                }
                if (vPointer == 0)
                    break;

                vPointer = offsetTable[vPointer - 1];
            }
        }

        return result;
    }

    private static int[] getOffsetTableByPrefix(String string) {
        int[] table = new int[string.length()];
        int k = 0;
        for (int i = 1; i < string.length(); i++) {
            while (k > 0 && string.charAt(i) != string.charAt(k))
                k = table[k - 1];
            if (string.charAt(i) == string.charAt(k))
                k++;
            table[i] = k;
        }
        return table;
    }

    /**
     * Find substring with Finite State Machine
     * 
     * @param string
     * @param substring
     * @return
     */
    public static List<Integer> FSM(String string, String substring) {
        if (string == null || substring == null)
            throw new IllegalArgumentException("Params can't be null");

        int stringLength = string.length();
        int substringLength = substring.length();

        if (substringLength > stringLength || stringLength == 0)
            return new ArrayList<Integer>();

        if (substringLength == 0)
            throw new IllegalArgumentException("Substring for search can't be empty");

        char[] chars = substring.toCharArray();
        Set<Character> charsSet = new HashSet<>();

        for (char c : chars)
            charsSet.add(c);

        List<Character> uniqueCharsList = new ArrayList<>(charsSet);
        int uniqueChars = charsSet.size();

        int[][] stateTable = new int[substringLength + 1][uniqueChars];

        for (int state = 0; state < substringLength; state++)
            for (int i = 0; i < uniqueChars; i++)
                stateTable[state][i] = getNextFSMState(substring, state, uniqueCharsList.get(i));

        List<Integer> result = new ArrayList<>();
        int state = 0;
        for (int i = 0; i < stringLength; i++) {
            if (!charsSet.contains(string.charAt(i))) {
                state = 0;
                continue;
            }

            state = stateTable[state][uniqueCharsList.indexOf(string.charAt(i))];
            if (state == substringLength) {
                i = i - substringLength + 1;
                result.add(i);
                state = 0;
            }
        }

        return result;
    }

    private static int getNextFSMState(String string, int state, char chr) {
        if (state < string.length() && chr == string.charAt(state))
            return state + 1;

        for (int newState = state; newState > 0; newState--) {
            if (string.charAt(newState - 1) == chr) {
                int i;
                for (i = 0; i < newState - 1; i++)
                    if (string.charAt(i) != string.charAt(state - newState + 1 + i))
                        break;
                if (i == newState - 1)
                    return newState;
            }
        }

        return 0;
    }

}