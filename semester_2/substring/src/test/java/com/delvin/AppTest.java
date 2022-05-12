package com.delvin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.*;

import org.junit.Test;

public class AppTest {
    @Test
    public void test_BoyerMoor_1() {
        String string = "Simple string for basic test";
        String substring = "string";

        List<Integer> result = new ArrayList<>();
        result.add(7);
        assertEquals(result, Substring.boyerMoore(string, substring));
    }

    @Test
    public void test_BoyerMoor_2() {
        String string = "Another simple string for just proof of concept work with simple words";
        String substring = "simple";

        List<Integer> result = new ArrayList<>();
        result.add(8);
        result.add(58);
        assertEquals(result, Substring.boyerMoore(string, substring));
    }

    @Test
    public void test_BoyerMoor_3() {
        String string = "One morning, when Gregor Samsa woke from troubled dreams, he found himself transformed in his bed into a horrible vermin. He lay on his armour-like back, and if he lifted his head a little he could see his brown belly, slightly domed and divided by arches into stiff sections. The bedding was hardly able to cover it and seemed ready to slide off any moment. His many legs, pitifully thin compared with the size of the rest of him, waved about helplessly as he looked. \"What's happened to me?\" he thought. It wasn't a dream. His room, a proper human room although a little too small, lay peacefully between its four familiar walls. A collection of textile samples lay spread out on the table - Samsa was a travelling salesman - and above it there hung a picture that he had recently cut out of an illustrated magazine and housed in a nice, gilded frame. It showed a lady fitted out with a fur hat and fur boa who sat upright, raising a heavy fur muff that covered the whole of her lower arm towards the viewer. Gregor then turned to look out the window at the dull weather. Drops";
        String substring = "dream";

        List<Integer> result = new ArrayList<>();
        result.add(50);
        result.add(518);
        assertEquals(result, Substring.boyerMoore(string, substring));
    }

    @Test
    public void test_RabinKarp_1() {
        String string = "Simple string for basic test";
        String substring = "string";

        List<Integer> result = new ArrayList<>();
        result.add(7);
        assertEquals(result, Substring.rabinKarp(string, substring, String::hashCode));
    }

    @Test
    public void test_RabinKarp_2() {
        String string = "Another simple string for just proof of concept work with simple words";
        String substring = "simple";

        List<Integer> result = new ArrayList<>();
        result.add(8);
        result.add(58);
        assertEquals(result, Substring.rabinKarp(string, substring, String::hashCode));
    }

    @Test
    public void test_RabinKarp_3() {
        String string = "One morning, when Gregor Samsa woke from troubled dreams, he found himself transformed in his bed into a horrible vermin. He lay on his armour-like back, and if he lifted his head a little he could see his brown belly, slightly domed and divided by arches into stiff sections. The bedding was hardly able to cover it and seemed ready to slide off any moment. His many legs, pitifully thin compared with the size of the rest of him, waved about helplessly as he looked. \"What's happened to me?\" he thought. It wasn't a dream. His room, a proper human room although a little too small, lay peacefully between its four familiar walls. A collection of textile samples lay spread out on the table - Samsa was a travelling salesman - and above it there hung a picture that he had recently cut out of an illustrated magazine and housed in a nice, gilded frame. It showed a lady fitted out with a fur hat and fur boa who sat upright, raising a heavy fur muff that covered the whole of her lower arm towards the viewer. Gregor then turned to look out the window at the dull weather. Drops";
        String substring = "dream";

        List<Integer> result = new ArrayList<>();
        result.add(50);
        result.add(518);
        assertEquals(result, Substring.rabinKarp(string, substring, String::hashCode));
    }

    @Test
    public void test_KnuthMorrisPrat_1() {
        String string = "Simple string for basic test";
        String substring = "string";

        List<Integer> result = new ArrayList<>();
        result.add(7);
        assertEquals(result, Substring.knuthMorrisPratt(string, substring));
    }

    @Test
    public void test_KnuthMorrisPrat_2() {
        String string = "Another simple string for just proof of concept work with simple words";
        String substring = "simple";

        List<Integer> result = new ArrayList<>();
        result.add(8);
        result.add(58);
        assertEquals(result, Substring.knuthMorrisPratt(string, substring));
    }

    @Test
    public void test_KnuthMorrisPrat_3() {
        String string = "One morning, when Gregor Samsa woke from troubled dreams, he found himself transformed in his bed into a horrible vermin. He lay on his armour-like back, and if he lifted his head a little he could see his brown belly, slightly domed and divided by arches into stiff sections. The bedding was hardly able to cover it and seemed ready to slide off any moment. His many legs, pitifully thin compared with the size of the rest of him, waved about helplessly as he looked. \"What's happened to me?\" he thought. It wasn't a dream. His room, a proper human room although a little too small, lay peacefully between its four familiar walls. A collection of textile samples lay spread out on the table - Samsa was a travelling salesman - and above it there hung a picture that he had recently cut out of an illustrated magazine and housed in a nice, gilded frame. It showed a lady fitted out with a fur hat and fur boa who sat upright, raising a heavy fur muff that covered the whole of her lower arm towards the viewer. Gregor then turned to look out the window at the dull weather. Drops";
        String substring = "dream";

        List<Integer> result = new ArrayList<>();
        result.add(50);
        result.add(518);
        assertEquals(result, Substring.knuthMorrisPratt(string, substring));
    }

    @Test
    public void test_FSM_1() {
        String string = "Simple string for basic test";
        String substring = "string";

        List<Integer> result = new ArrayList<>();
        result.add(7);
        assertEquals(result, Substring.FSM(string, substring));
    }

    @Test
    public void test_FSM_2() {
        String string = "Another simple string for just proof of concept work with simple words";
        String substring = "simple";

        List<Integer> result = new ArrayList<>();
        result.add(8);
        result.add(58);
        assertEquals(result, Substring.FSM(string, substring));
    }

    @Test
    public void test_FSM_3() {
        String string = "One morning, when Gregor Samsa woke from troubled dreams, he found himself transformed in his bed into a horrible vermin. He lay on his armour-like back, and if he lifted his head a little he could see his brown belly, slightly domed and divided by arches into stiff sections. The bedding was hardly able to cover it and seemed ready to slide off any moment. His many legs, pitifully thin compared with the size of the rest of him, waved about helplessly as he looked. \"What's happened to me?\" he thought. It wasn't a dream. His room, a proper human room although a little too small, lay peacefully between its four familiar walls. A collection of textile samples lay spread out on the table - Samsa was a travelling salesman - and above it there hung a picture that he had recently cut out of an illustrated magazine and housed in a nice, gilded frame. It showed a lady fitted out with a fur hat and fur boa who sat upright, raising a heavy fur muff that covered the whole of her lower arm towards the viewer. Gregor then turned to look out the window at the dull weather. Drops";
        String substring = "dream";

        List<Integer> result = new ArrayList<>();
        result.add(50);
        result.add(518);
        assertEquals(result, Substring.FSM(string, substring));
    }
}
