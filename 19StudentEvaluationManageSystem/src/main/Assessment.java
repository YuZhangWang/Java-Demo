package main;

public class Assessment {
    private String id;//学号
    private String name;//姓名
    private String unPass;//不及格门数
    private String score;//总成绩
    private String year;//年份
    private String term;//学期

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnPass() {
        return unPass;
    }

    public void setUnPass(String unPass) {
        this.unPass = unPass;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
