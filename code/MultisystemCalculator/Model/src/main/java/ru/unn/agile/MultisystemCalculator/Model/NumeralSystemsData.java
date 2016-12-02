package ru.unn.agile.MultisystemCalculator.Model;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableList;

import java.util.Map;

import static ru.unn.agile.MultisystemCalculator.Model.Format.*;

/**
 * Created by Alexander on 09.11.2016.
 */
public final class NumeralSystemsData {

    public static final Map<Character, String> OCT_TO_BINARY_MAPPING =
            ImmutableMap.<Character, String>builder().put(new Character('0'), "000")
                    .put(new Character('1'), "001")
                    .put(new Character('2'), "010")
                    .put(new Character('3'), "011")
                    .put(new Character('4'), "100")
                    .put(new Character('5'), "101")
                    .put(new Character('6'), "110")
                    .put(new Character('7'), "111")
                    .build();

    public static final Map<Character, String> HEX_TO_BINARY_MAPPING =
            ImmutableMap.<Character, String>builder().put(new Character('0'), "0000")
                    .put(new Character('1'), "0001")
                    .put(new Character('2'), "0010")
                    .put(new Character('3'), "0011")
                    .put(new Character('4'), "0100")
                    .put(new Character('5'), "0101")
                    .put(new Character('6'), "0110")
                    .put(new Character('7'), "0111")
                    .put(new Character('8'), "1000")
                    .put(new Character('9'), "1001")
                    .put(new Character('A'), "1010")
                    .put(new Character('B'), "1011")
                    .put(new Character('C'), "1100")
                    .put(new Character('D'), "1101")
                    .put(new Character('E'), "1110")
                    .put(new Character('F'), "1111")
                    .build();

    public static final ImmutableList<Character> BINARY_CHARACTERS = ImmutableList.of('0', '1');
    public static final ImmutableList<Character> OCTAL_CHARACTERS = ImmutableList.of('0', '1', '2',
            '3', '4',
            '5', '6', '7');
    public static final ImmutableList<Character> HEXADECIMAL_CHARACTERS = ImmutableList.of('0', '1',
            '2', '3',
            '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F');

    public static final Map<Format, String> FORMAT_PREFIXES_MAPPING =
            ImmutableMap.<Format, String>builder().put(BIN, "0b")
                    .put(OCT, "0o")
                    .put(HEX, "0x")
                    .build();

    private NumeralSystemsData() {
    }
}
