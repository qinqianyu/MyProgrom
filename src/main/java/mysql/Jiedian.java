package mysql;

public class Jiedian {
    private int gid;
    private String gname;

    public Jiedian() {
    }

    public Jiedian(int gid, String gname) {
        this.gid = gid;
        this.gname = gname;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }
}
