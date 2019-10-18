package mysql;

public class Guanxi {
    private int start;
    private int end;
    private int end2;
    private int end3;
    private int end4;
    private String startname;
    private String endname;
    private String end2name;
    private String end3name;
    private String end4name;

    @Override
    public String toString() {
        return "Guanxi{" +
                "start=" + start +
                ", end=" + end +
                ", end2=" + end2 +
                ", end3=" + end3 +
                ", end4=" + end4 +
                ", startname='" + startname + '\'' +
                ", endname='" + endname + '\'' +
                ", end2name='" + end2name + '\'' +
                ", end3name='" + end3name + '\'' +
                ", end4name='" + end4name + '\'' +
                '}';
    }

    public Guanxi(int start, int end, int end2, int end3, int end4, String startname, String endname, String end2name, String end3name, String end4name) {
        this.start = start;
        this.end = end;
        this.end2 = end2;
        this.end3 = end3;
        this.end4 = end4;
        this.startname = startname;
        this.endname = endname;
        this.end2name = end2name;
        this.end3name = end3name;
        this.end4name = end4name;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getEnd2() {
        return end2;
    }

    public void setEnd2(int end2) {
        this.end2 = end2;
    }

    public int getEnd3() {
        return end3;
    }

    public void setEnd3(int end3) {
        this.end3 = end3;
    }

    public int getEnd4() {
        return end4;
    }

    public void setEnd4(int end4) {
        this.end4 = end4;
    }

    public String getStartname() {
        return startname;
    }

    public void setStartname(String startname) {
        this.startname = startname;
    }

    public String getEndname() {
        return endname;
    }

    public void setEndname(String endname) {
        this.endname = endname;
    }

    public String getEnd2name() {
        return end2name;
    }

    public void setEnd2name(String end2name) {
        this.end2name = end2name;
    }

    public String getEnd3name() {
        return end3name;
    }

    public void setEnd3name(String end3name) {
        this.end3name = end3name;
    }

    public String getEnd4name() {
        return end4name;
    }

    public void setEnd4name(String end4name) {
        this.end4name = end4name;
    }

    public Guanxi() {
    }
}
