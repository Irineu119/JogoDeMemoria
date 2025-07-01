public enum ECardNumber {
    CARDNUMBER_ACE,
    CARDNUMBER_TWO,
    CARDNUMBER_THREE,
    CARDNUMBER_FOUR,
    CARDNUMBER_FIVE,
    CARDNUMBER_SIX,
    CARDNUMBER_SEVEN,
    CARDNUMBER_EIGHT,
    CARDNUMBER_NINE,
    CARDNUMBER_TEN,
    CARDNUMBER_JACK,
    CARDNUMBER_QUEEN,
    CARDNUMBER_KING;

    public static final char[] cardNumberToChar = {
            'A',
            '2',
            '3',
            '4',
            '5',
            '6',
            '7',
            '8',
            '9',
            '1',
            'J',
            'Q',
            'K'
    };

    public char toChar() {
        return cardNumberToChar[ordinal()];
    }
}
