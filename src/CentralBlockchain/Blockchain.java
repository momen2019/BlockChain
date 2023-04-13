/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CentralBlockchain;

/**
 *
 * @author Momen Mansour
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Blockchain {

    private List<Block> blocks;
    private int difficulty;

    public Blockchain(int difficulty) {
        blocks = new ArrayList<>();
        this.difficulty = difficulty;
        Block genesisBlock = new Block(0, new Date(), "Genesis Block", "0");
        genesisBlock.mineBlock(difficulty);
        blocks.add(genesisBlock);
    }

    public void addBlock(Block newBlock) {
        newBlock.mineBlock(difficulty);
        blocks.add(newBlock);
    }

    public Block getLatestBlock() {
        return blocks.get(blocks.size() - 1);
    }


    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain(4);

        for (int i = 1; i <= 5; i++) {
            String data = "Block " + i + " data";
            Block newBlock = new Block(i, new Date(), data, blockchain.getLatestBlock().getHash());
            blockchain.addBlock(newBlock);
            System.out.println("Block " + i + " added to the blockchain.");
            System.out.println("Hash: " + newBlock.getHash());
            System.out.println("Previous Hash: " + newBlock.getPreviousHash());
            System.out.println("Data: " + newBlock.getData());
            System.out.println();
        }
    }
}