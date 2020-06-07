package Trees;

import java.util.HashMap;

public class Trie {
    boolean isLeaf;
    HashMap<Character, Trie> children;
    public Trie() {
        isLeaf = false;
        children = new HashMap<>();
    }

    /**
     * Adds a word to the trie
     * @param word word to be added
     */
    public void addWord(String word) {
        Trie currentTrie = this;
        HashMap<Character, Trie> children = currentTrie.children;
        for (int i=0; i<word.length(); i++) {
            Character currentChar = word.charAt(i);
            if (!children.containsKey(currentChar)) {
                children.put(currentChar, new Trie());
            }
            currentTrie = children.get(currentChar);
            children = currentTrie.children;
        }
        currentTrie.isLeaf = true;
    }

    /**
     * Determines whether a word is contained in the trie
     * @param word the given word
     * @return a boolean indicating whether the trie contains the word
     */
    public boolean containsWord(String word) {
        Trie currentTrie = this;
        HashMap<Character, Trie> children = currentTrie.children;
        for (int i=0; i<word.length(); i++) {
            Character currentChar = word.charAt(i);
            if (!children.containsKey(currentChar)) {
                return false;
            }
            currentTrie = children.get(currentChar);
            children = currentTrie.children;
        }
        return currentTrie.isLeaf;
    }

    /**
     * Determines whether a prefix is contained in the trie
     * @param prefix the given prefix
     * @return a boolean indicating whether the trie contains the prefix
     */
    public boolean containsPrefix(String prefix) {
        Trie currentTrie = this;
        HashMap<Character, Trie> children = currentTrie.children;
        for (int i=0; i<prefix.length(); i++) {
            Character currentChar = prefix.charAt(i);
            if (!children.containsKey(currentChar)) {
                return false;
            }
            currentTrie = children.get(currentChar);
            children = currentTrie.children;
        }
        return true;
    }

    /**
     * removes a word from the trie
     * @param word word to be removed
     * @return whether the word was removed or not
     */
    public boolean removeWord(String word) {
        if (!containsWord(word)) {
            return false;
        }
        removeWord(word, this, 0);
        return true;
    }

    public Trie removeWord(String word, Trie trie, int index) {
        if (index >= word.length()) {
            trie.isLeaf = false;
            return trie;
        }
        Trie child = removeWord(word, trie.children.get(word.charAt(index)), index+1);
        if (child.children.size() == 0) {
            trie.children.remove(word.charAt(index));
        }
        return trie;
    }

    /**
     * prints all the words in the trie
     */
    public void printAllWords() {
        for (char c: children.keySet()) {
            StringBuilder sb = new StringBuilder(c + "");
            printAllWords(children.get(c), sb);
        }
    }

    public void printAllWords(Trie trie, StringBuilder sb) {
        for (char ch: trie.children.keySet()) {
            sb.append(ch);
            printAllWords(trie.children.get(ch), sb);
            sb.deleteCharAt(sb.length() - 1);
        }

        if (trie.isLeaf) {
            System.out.println(sb.toString());
        }
    }
}
