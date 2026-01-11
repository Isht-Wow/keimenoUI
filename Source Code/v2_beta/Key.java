package wow.ishit.v2_beta_keimenoUI;

/**
 * Represents keys on a keyboard, including letters (both uppercase and lowercase),
 * digits, common symbols, function keys (F1-F12), arrow keys, and special keys like
 * ESC, ENTER, TAB, and BACKSPACE.
 * <p>
 * This enum provides a mapping from characters to their corresponding {@code Key} enum constants.
 * The mapping is stored in the {@code charToKeyMap} for quick lookup.
 * </p>
 */
public enum Key {
        ESC,
        ETX, // Ctrl+C
        ENTER,
        TAB,
        BACKSPACE,
        ARROW_UP,
        ARROW_DOWN,
        ARROW_LEFT,
        ARROW_RIGHT,
        F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12,

        // Letters
        A, B, C, D, E, F, G, H, I, J, K, L, M,
        N, O, P, Q, R, S, T, U, V, W, X, Y, Z,

        a, b, c, d, e, f, g, h, i, j, k, l, m,
        n, o, p, q, r, s, t, u, v, w, x, y, z,

        // Numbers
        DIGIT_0, DIGIT_1, DIGIT_2, DIGIT_3, DIGIT_4,
        DIGIT_5, DIGIT_6, DIGIT_7, DIGIT_8, DIGIT_9,

        // Common symbols
        SPACE,
        EXCLAMATION_MARK,
        DOUBLE_QUOTE,
        HASH,
        DOLLAR,
        PERCENT,
        AMPERSAND,
        SINGLE_QUOTE,
        LEFT_PAREN,
        RIGHT_PAREN,
        ASTERISK,
        PLUS,
        COMMA,
        MINUS,
        DOT,
        SLASH,
        COLON,
        SEMICOLON,
        LESS_THAN,
        EQUALS,
        GREATER_THAN,
        QUESTION_MARK,
        AT,
        LEFT_BRACKET,
        BACKSLASH,
        RIGHT_BRACKET,
        CARET,
        UNDERSCORE,
        BACKTICK,
        LEFT_BRACE,
        PIPE,
        RIGHT_BRACE,
        TILDE,

        OTHER;

        /**
         * A mapping from {@code char} values to their corresponding {@code Key} enum constants.
         * This map is used to quickly convert a character to a {@code Key}.
         */
        private static final java.util.Map<Character, Key> charToKeyMap = new java.util.HashMap<>();

        static {
            // Letters uppercase
            charToKeyMap.put('A', A);
            charToKeyMap.put('B', B);
            charToKeyMap.put('C', C);
            charToKeyMap.put('D', D);
            charToKeyMap.put('E', E);
            charToKeyMap.put('F', F);
            charToKeyMap.put('G', G);
            charToKeyMap.put('H', H);
            charToKeyMap.put('I', I);
            charToKeyMap.put('J', J);
            charToKeyMap.put('K', K);
            charToKeyMap.put('L', L);
            charToKeyMap.put('M', M);
            charToKeyMap.put('N', N);
            charToKeyMap.put('O', O);
            charToKeyMap.put('P', P);
            charToKeyMap.put('Q', Q);
            charToKeyMap.put('R', R);
            charToKeyMap.put('S', S);
            charToKeyMap.put('T', T);
            charToKeyMap.put('U', U);
            charToKeyMap.put('V', V);
            charToKeyMap.put('W', W);
            charToKeyMap.put('X', X);
            charToKeyMap.put('Y', Y);
            charToKeyMap.put('Z', Z);

            // Letters lowercase
            charToKeyMap.put('a', a);
            charToKeyMap.put('b', b);
            charToKeyMap.put('c', c);
            charToKeyMap.put('d', d);
            charToKeyMap.put('e', e);
            charToKeyMap.put('f', f);
            charToKeyMap.put('g', g);
            charToKeyMap.put('h', h);
            charToKeyMap.put('i', i);
            charToKeyMap.put('j', j);
            charToKeyMap.put('k', k);
            charToKeyMap.put('l', l);
            charToKeyMap.put('m', m);
            charToKeyMap.put('n', n);
            charToKeyMap.put('o', o);
            charToKeyMap.put('p', p);
            charToKeyMap.put('q', q);
            charToKeyMap.put('r', r);
            charToKeyMap.put('s', s);
            charToKeyMap.put('t', t);
            charToKeyMap.put('u', u);
            charToKeyMap.put('v', v);
            charToKeyMap.put('w', w);
            charToKeyMap.put('x', x);
            charToKeyMap.put('y', y);
            charToKeyMap.put('z', z);

            // Digits
            charToKeyMap.put('0', DIGIT_0);
            charToKeyMap.put('1', DIGIT_1);
            charToKeyMap.put('2', DIGIT_2);
            charToKeyMap.put('3', DIGIT_3);
            charToKeyMap.put('4', DIGIT_4);
            charToKeyMap.put('5', DIGIT_5);
            charToKeyMap.put('6', DIGIT_6);
            charToKeyMap.put('7', DIGIT_7);
            charToKeyMap.put('8', DIGIT_8);
            charToKeyMap.put('9', DIGIT_9);

            // Symbols
            charToKeyMap.put(' ', SPACE);
            charToKeyMap.put('!', EXCLAMATION_MARK);
            charToKeyMap.put('"', DOUBLE_QUOTE);
            charToKeyMap.put('#', HASH);
            charToKeyMap.put('$', DOLLAR);
            charToKeyMap.put('%', PERCENT);
            charToKeyMap.put('&', AMPERSAND);
            charToKeyMap.put('\'', SINGLE_QUOTE);
            charToKeyMap.put('(', LEFT_PAREN);
            charToKeyMap.put(')', RIGHT_PAREN);
            charToKeyMap.put('*', ASTERISK);
            charToKeyMap.put('+', PLUS);
            charToKeyMap.put(',', COMMA);
            charToKeyMap.put('-', MINUS);
            charToKeyMap.put('.', DOT);
            charToKeyMap.put('/', SLASH);
            charToKeyMap.put(':', COLON);
            charToKeyMap.put(';', SEMICOLON);
            charToKeyMap.put('<', LESS_THAN);
            charToKeyMap.put('=', EQUALS);
            charToKeyMap.put('>', GREATER_THAN);
            charToKeyMap.put('?', QUESTION_MARK);
            charToKeyMap.put('@', AT);
            charToKeyMap.put('[', LEFT_BRACKET);
            charToKeyMap.put('\\', BACKSLASH);
            charToKeyMap.put(']', RIGHT_BRACKET);
            charToKeyMap.put('^', CARET);
            charToKeyMap.put('_', UNDERSCORE);
            charToKeyMap.put('`', BACKTICK);
            charToKeyMap.put('{', LEFT_BRACE);
            charToKeyMap.put('|', PIPE);
            charToKeyMap.put('}', RIGHT_BRACE);
            charToKeyMap.put('~', TILDE);
        }

        /**
         * Returns the {@code Key} corresponding to the given character.
         * <p>
         * Usage example:
         * <pre>
         * {@code
         * Key key = Key.fromChar('A');
         * if (key == Key.A) {
         *     // Handle key 'A'
         * }
         * }
         * </pre>
         * </p>
         *
         * @param c the character to convert to a {@code Key}
         * @return the corresponding {@code Key} if found; {@code null} otherwise
         */
        public static Key fromChar(char c) {
            return charToKeyMap.get(c);
        }
    }