public enum ESuit {
    SUIT_DIAMONDS,
    SUIT_CLUBS,
    SUIT_HEARTS,
    SUIT_SPADES;

    public static final char[] cardSuitToChar = {
            'O',
            'P',
            'C',
            'E'
    };

    public char toChar() {
        return cardSuitToChar[ordinal()];
    }
}
