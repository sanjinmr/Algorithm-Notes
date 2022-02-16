package com.sanjin.www.algorithm.strings;

/**
 * Trie树的简单实现
 */
public class Trie {

    private TrieNode root = new TrieNode('/');

    public void insert(char[] texts) {
        if (texts == null) {
            return;
        }

        TrieNode p = root;
        for (int i = 0; i < texts.length; i ++) {
            int index = texts[i] - 'a';
            if (p.childs[index] == null) {
                p.childs[index] = new TrieNode(texts[i]);
            }
            p = p.childs[index];
        }
        p.isEndingChar = true;
    }

    public boolean find(char[] texts) {
        if (texts == null) return false;

        TrieNode p = root;
        for (int i = 0; i < texts.length; i ++) {
            int index = texts[i] - 'a';
            if (p.childs[index] == null) {
                return false;
            }
            p = p.childs[index];
        }

        if (p.isEndingChar) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Trie树节点
     */
    public static class TrieNode {
        protected char data;
        protected TrieNode[] childs = new TrieNode[26];
        protected boolean isEndingChar = false;
        public TrieNode(char data) {
            this.data = data;
        }
    }
}
