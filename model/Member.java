package model;

public class Member {
    private String fullName;
    private String phone;

    public Member(String fullName, String phone) {
        this.fullName = fullName;
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Member{" +
                "fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
