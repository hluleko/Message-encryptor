
package za.ac.tut.encryption;

public class MessageEncryption {

    public MessageEncryption() {
    
    }
    
     public  String encryp(String text, int shift) {
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                ch = (char) ((ch - base + shift) % 26 + base);
            }
            encrypted.append(ch);
        }
        return encrypted.toString();
    }
}
     

