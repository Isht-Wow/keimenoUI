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
        /** Escape key */
        ESC,
        /** Interrupt signal (Ctrl+C) */
        ETX, // Ctrl+C
        /** Enter key */
        ENTER,
        /** Tab key */
        TAB,
        /** Backspace key */
        BACKSPACE,
        /** Up arrow key */
        ARROW_UP,
        /** Down arrow key */
        ARROW_DOWN,
        /** Left arrow key */
        ARROW_LEFT,
        /** Right arrow key */
        ARROW_RIGHT,
        /** Function key F1 */
        F1,
        /** Function key F2 */
        F2,
        /** Function key F3 */
        F3,
        /** Function key F4 */
        F4,
        /** Function key F5 */
        F5,
        /** Function key F6 */
        F6,
        /** Function key F7 */
        F7,
        /** Function key F8 */
        F8,
        /** Function key F9 */
        F9,
        /** Function key F10 */
        F10,
        /** Function key F11 */
        F11,
        /** Function key F12 */
        F12,

        // Letters
        /** Uppercase letter A */
        A,
        /** Uppercase letter B */
        B,
        /** Uppercase letter C */
        C,
        /** Uppercase letter D */
        D,
        /** Uppercase letter E */
        E,
        /** Uppercase letter F */
        F,
        /** Uppercase letter G */
        G,
        /** Uppercase letter H */
        H,
        /** Uppercase letter I */
        I,
        /** Uppercase letter J */
        J,
        /** Uppercase letter K */
        K,
        /** Uppercase letter L */
        L,
        /** Uppercase letter M */
        M,
        /** Uppercase letter N */
        N,
        /** Uppercase letter O */
        O,
        /** Uppercase letter P */
        P,
        /** Uppercase letter Q */
        Q,
        /** Uppercase letter R */
        R,
        /** Uppercase letter S */
        S,
        /** Uppercase letter T */
        T,
        /** Uppercase letter U */
        U,
        /** Uppercase letter V */
        V,
        /** Uppercase letter W */
        W,
        /** Uppercase letter X */
        X,
        /** Uppercase letter Y */
        Y,
        /** Uppercase letter Z */
        Z,

        /** Lowercase letter a */
        a,
        /** Lowercase letter b */
        b,
        /** Lowercase letter c */
        c,
        /** Lowercase letter d */
        d,
        /** Lowercase letter e */
        e,
        /** Lowercase letter f */
        f,
        /** Lowercase letter g */
        g,
        /** Lowercase letter h */
        h,
        /** Lowercase letter i */
        i,
        /** Lowercase letter j */
        j,
        /** Lowercase letter k */
        k,
        /** Lowercase letter l */
        l,
        /** Lowercase letter m */
        m,
        /** Lowercase letter n */
        n,
        /** Lowercase letter o */
        o,
        /** Lowercase letter p */
        p,
        /** Lowercase letter q */
        q,
        /** Lowercase letter r */
        r,
        /** Lowercase letter s */
        s,
        /** Lowercase letter t */
        t,
        /** Lowercase letter u */
        u,
        /** Lowercase letter v */
        v,
        /** Lowercase letter w */
        w,
        /** Lowercase letter x */
        x,
        /** Lowercase letter y */
        y,
        /** Lowercase letter z */
        z,

        // Numbers
        /** Digit 0 */
        DIGIT_0,
        /** Digit 1 */
        DIGIT_1,
        /** Digit 2 */
        DIGIT_2,
        /** Digit 3 */
        DIGIT_3,
        /** Digit 4 */
        DIGIT_4,
        /** Digit 5 */
        DIGIT_5,
        /** Digit 6 */
        DIGIT_6,
        /** Digit 7 */
        DIGIT_7,
        /** Digit 8 */
        DIGIT_8,
        /** Digit 9 */
        DIGIT_9,

        // Common symbols
        /** Space character */
        SPACE,
        /** Exclamation mark '!' */
        EXCLAMATION_MARK,
        /** Double quote '&quot;' */
        DOUBLE_QUOTE,
        /** Hash '#' */
        HASH,
        /** Dollar sign '$' */
        DOLLAR,
        /** Percent sign '%' */
        PERCENT,
        /** Ampersand '&amp;' */
        AMPERSAND,
        /** Single quote/apostrophe '\'' */
        SINGLE_QUOTE,
        /** Left parenthesis '(' */
        LEFT_PAREN,
        /** Right parenthesis ')' */
        RIGHT_PAREN,
        /** Asterisk '*' */
        ASTERISK,
        /** Plus sign '+' */
        PLUS,
        /** Comma ',' */
        COMMA,
        /** Minus sign '-' */
        MINUS,
        /** Dot/period '.' */
        DOT,
        /** Slash '/' */
        SLASH,
        /** Colon ':' */
        COLON,
        /** Semicolon ';' */
        SEMICOLON,
        /** Less than sign '&lt;' */
        LESS_THAN,
        /** Equals sign '=' */
        EQUALS,
        /** Greater than sign '&gt;' */
        GREATER_THAN,
        /** Question mark '?' */
        QUESTION_MARK,
        /** At sign '@' */
        AT,
        /** Left bracket '[' */
        LEFT_BRACKET,
        /** Backslash '\' */
        BACKSLASH,
        /** Right bracket ']' */
        RIGHT_BRACKET,
        /** Caret '^' */
        CARET,
        /** Underscore '_' */
        UNDERSCORE,
        /** Backtick '`' */
        BACKTICK,
        /** Left brace '{' */
        LEFT_BRACE,
        /** Pipe '&#124;' */
        PIPE,
        /** Right brace '}' */
        RIGHT_BRACE,
        /** Tilde '~' */
        TILDE,

        /** Represents any other key not explicitly listed */
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
         *
         * @param c the character to convert to a {@code Key}
         * @return the corresponding {@code Key} if found; {@code null} otherwise
         */
        public static Key fromChar(char c) {
            return charToKeyMap.get(c);
        }
    }