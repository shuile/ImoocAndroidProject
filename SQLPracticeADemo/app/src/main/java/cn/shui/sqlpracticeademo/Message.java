package cn.shui.sqlpracticeademo;

public class Message {

    private int _id;
    private int learner;
    private String name;

    public Message() {
    }

    public Message(int learner, String name) {
        super();
        this.learner = learner;
        this.name = name;
    }

    public Message(int id, int learner, String name) {
        super();
        this._id = id;
        this.learner = learner;
        this.name = name;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public int getLearner() {
        return learner;
    }

    public void setLearner(int learner) {
        this.learner = learner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
