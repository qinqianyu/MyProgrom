package redis.Bloom;

import io.rebloom.client.Client;


public class BloomTest2 {
    public static void main(String[] args) {
        Client client = new Client("localhost", 6378);
    }
}
