package vvfgaa.password;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Key {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int group;
    public String Title;
    public String User;
    public String Password;
    public String Passwoed2;
    public String Website;
    public String Email;
    public String Phone;
    public String Note;

}
