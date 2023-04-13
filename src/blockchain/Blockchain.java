package blockchain;

// Blockchain Class
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 *
 * @author Momen Mansour
 */

public class Blockchain {

    private Block[] blocks;

    public Blockchain(int size) {
        blocks = new Block[size];
    }

    public void setBlock(int index, Block block) {
        blocks[index] = block;
    }

    public Block getBlock(int index) {
        return blocks[index];
    }

    public void blocksExplorer() {
        for (Block block : blocks) {
            System.out.println(block.getIndex() + ": " + block.getHash());
        }
    }

    public void mineBlock(int index, String data) {
        Block previousBlock = getBlock(index - 1);
        String previousHash = previousBlock.getHash();
        Date timestamp = new Date();
        String hash = calculateHash(index, timestamp, data, previousHash);
        Block block = new Block(index, timestamp, data, previousHash, hash);
        setBlock(index, block);
    }

    String calculateHash(int index, Date timestamp, String data, String previousHash) {
        String input = index + timestamp.toString() + data + previousHash;
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
}
