public class Encrypt {
    public static void main(String[] args) {
        String cyhperdText = encryptString("A. PROGRAM THAT \"ENCRYPTS\" YOUR MESSAGE!", 2, 3);
        System.out.println(cyhperdText);
        System.out.println(ungroupify("UNGR OUPY OURM ESSA GExx"));
        System.out.println(decryptString(cyhperdText ,2));
    }
    // Remove all white spaces and punctuations from the text
    public static String normalizeText(String text) {
        if (text.equals("")) {
            throw new IllegalArgumentException("Please enter some text");
        } else {
            return text.replaceAll("[?.\\s\"()!]", "").toUpperCase();
        }
    }
    //Encrypt the text by shifting the letters of the alphabet by the given key
    public static String caesarify(String text, int key) {
        String newText = "";
        String alpha = shiftAlphabet(0);
        String newAlpha = shiftAlphabet(key);

        for (int i = 0; i < text.length(); i++) {
            String currentLetter = String.valueOf(text.charAt(i));
            int indexOfLetter = alpha.indexOf(currentLetter);
            String letterReplacement = String.valueOf(newAlpha.charAt(indexOfLetter));

            newText += letterReplacement;

        }
        return newText;
    }
    //A method to shift the alphabet, this code was given by the course and is not written by me
    public static String shiftAlphabet(int shift) {
        int start = 0;
        if (shift < 0) {
            start = (int) 'Z' + shift + 1;
        } else {
            start = 'A' + shift;
        }
        String result = "";
        char currChar = (char) start;
        for (; currChar <= 'Z'; ++currChar) {
            result = result + currChar;
        }
        if (result.length() < 26) {
            for (currChar = 'A'; result.length() < 26; ++currChar) {
                result = result + currChar;
            }
        }
        return result;
    }
    //Grouping the given text according to the given group size while putting a white space in between every group
    public static String groupify(String text, int groupSize) {
        String newText = "";
        int textLength = text.length();
        int amountXNeeded = groupSize - (textLength % groupSize);

        for (int i = 0; i < textLength; i += groupSize) {
            int range = groupSize + i;
            if (range > textLength) {
                range = textLength;
            }
            newText += text.substring(i, range);
            if (range != textLength) {
                newText += " ";
            }
        }
        if( amountXNeeded != groupSize){
            for (int i = 0; i < amountXNeeded; i++) {
                newText += "x";
            }
        }

        return newText;
    }
    //Encrypting the text by making use of all the methods written
    public static String encryptString(String text, int key, int groupSize) {
        return groupify(caesarify(normalizeText(text), key), groupSize);
    }
    //Ungrouping the given text
    public static String ungroupify(String text){
        return text.replaceAll("[\\sx]", "");
}
    //Decrypting the string back to the original message with no spaces between the words. (the key here must always be the same as the key given in the encryptString method)
    public static String decryptString(String text, int key) {
        String decryptedText = "";
        String alpha = shiftAlphabet(0);
        String newAlpha = shiftAlphabet(key);
        text = ungroupify(text);
        for (int i = 0; i < text.length(); i++) {
            String currentLetter = String.valueOf(text.charAt(i));
            int indexOfLetter = newAlpha.indexOf(currentLetter);
            String letterReplacement = String.valueOf(alpha.charAt(indexOfLetter));
            decryptedText += letterReplacement;

        }
        return decryptedText;
    }
}
