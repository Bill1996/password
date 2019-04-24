package vvfgaa.password.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import vvfgaa.password.model.Key;

@Entity(tableName = "key")
public class KeyEntity implements Key {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int group;
    private String Title;
    private String User;
    private String Password;
    private String Password2;
    private String Website;
    private String Email;
    private String Phone;
    private String Note;

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    @Override
    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    @Override
    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    @Override
    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Override
    public String getPassword2() {
        return Password2;
    }

    public void setPassword2(String passwoed2) {
        Password2 = passwoed2;
    }

    @Override
    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    @Override
    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    @Override
    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    @Override
    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public KeyEntity() {
    }
}
