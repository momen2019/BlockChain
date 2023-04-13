/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DecentralizedBlockchain;

/**
 *
 * @author Momen Mansour
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


public class Blockchain {
    private List<Block> chain;
    private int difficulty;

    public Blockchain(int difficulty) {
        this.chain = new ArrayList<>();
        this.difficulty = difficulty;
        chain.add(createGenesisBlock());
    }

    private Block createGenesisBlock() {
        return new Block(0, new Date(), "Genesis Block", "0");
    }

    public Block getLatestBlock() {
        return chain.get(chain.size() - 1);
    }

    public Block getBlock(int index) {
        return chain.get(index);
    }

    public void addBlock(Block newBlock) {
        newBlock.setPreviousHash(getLatestBlock().getHash());
        newBlock.mineBlock(difficulty);
        chain.add(newBlock);
    }

    public boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;

        for (int i = 1; i < chain.size(); i++) {
            currentBlock = chain.get(i);
            previousBlock = chain.get(i - 1);

            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                System.out.println("Current hash is not equal to calculated hash");
                return false;
            }

            if (!previousBlock.getHash().equals(currentBlock.getPreviousHash())) {
                System.out.println("Previous hash is not equal to current block's previous hash");
                return false;
            }
        }
        return true;
    }

public static void main(String[] args) {
    Blockchain blockchain = new Blockchain(4);
    Random rand = new Random();

    for (int i = 1; i <= 5; i++) {
        String data = "Block " + i + " data";
        Block newBlock = new Block(i, new Date(), data, blockchain.getLatestBlock().getHash());
        blockchain.addBlock(newBlock);
        System.out.println("Block " + i + " added to the blockchain.");
    }

    System.out.println("Is blockchain valid? " + blockchain.isChainValid());

    System.out.println("Blockchain:");
    for (Block block : blockchain.chain) {
        System.out.println("Index: " + block.getIndex());
        System.out.println("Timestamp: " + block.getTimestamp());
        System.out.println("Data: " + block.getData());
        System.out.println("Previous Hash: " + block.getPreviousHash());
        System.out.println("Hash: " + block.getHash());
        System.out.println();
    }
}
}