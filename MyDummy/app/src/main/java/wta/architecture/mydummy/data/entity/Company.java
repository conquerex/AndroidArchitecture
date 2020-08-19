package wta.architecture.mydummy.data.entity;

public class Company {
    private String bs;

    private String catchPhrase;

    private String name;

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ClassPojo [bs = " + bs + ", catchPhrase = " + catchPhrase + ", name = " + name + "]";
    }
}
