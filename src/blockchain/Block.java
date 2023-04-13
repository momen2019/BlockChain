package blockchain;

/**
 *
 * @author Momen Mansour
 */

// Block Class
import java.util.Date;

public class Block {

    private int index;
    private Date timestamp;
    private String data;
    private String previousHash;
    private String hash;

    public Block(int index, Date timestamp, String data, String previousHash, String hash) {
        this.index = index;
        this.timestamp = timestamp;
        this.data = data;
        this.previousHash = previousHash;
        this.hash = hash;
    }

    public int getIndex() {
        return index;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getData() {
        return data;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
