package blockchain;

import java.util.Date;

/**
 *
 * @author Momen Mansour
 */
public class Test {

    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain(3);

        // Genesis block
        Block genesis = new Block(0, new Date(), "Genesis Block", "0", "");
        genesis.setHash(blockchain.calculateHash(genesis.getIndex(), genesis.getTimestamp(), genesis.getData(), genesis.getPreviousHash()));
        blockchain.setBlock(0, genesis);

        // Add some blocks
        blockchain.mineBlock(1, "Transaction 1");
        blockchain.mineBlock(2, "Transaction 2");

        // Print the blocks
        blockchain.blocksExplorer();
    }

}
