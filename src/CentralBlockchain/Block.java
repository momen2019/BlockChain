/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CentralBlockchain;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 *
 * @author Momen Mansour
 */
    public class Block {
        private int index;
        private Date timestamp;
        private String data;
        private String previousHash;
        private String hash;
        private int nonce;

        public Block(int index, Date timestamp, String data, String previousHash) {
            this.index = index;
            this.timestamp = timestamp;
            this.data = data;
            this.previousHash = previousHash;
            this.nonce = 0;
            this.hash = calculateHash();
        }

        public void mineBlock(int difficulty) {
            String target = new String(new char[difficulty]).replace('\0', '0');
            while (!hash.substring(0, difficulty).equals(target)) {
                nonce++;
                hash = calculateHash();
            }
            System.out.println("Block mined: " + hash);
        }

        private String calculateHash() {
            String input = index + timestamp.toString() + data + previousHash + nonce;
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));
                StringBuilder hash = new StringBuilder();
                for (byte b : hashBytes) {
                    hash.append(String.format("%02x", b));
                }
                return hash.toString();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("SHA-256 not available");
            }
        }

        public String getHash() {
            return hash;
        }

        public String getPreviousHash() {
            return previousHash;
        }

        public String getData() {
            return data;
        }
    }
