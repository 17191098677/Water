package com.smartcityin.waterknow.Entity;

import android.support.annotation.IntDef;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Author : Mr.老王
 * Created on 2018/4/24
 * E-mail : wkz123011@gmail.com
 */
@Entity
public class UserEntity {
    @Id(autoincrement = true)
    private Long id;
    @Property(nameInDb = "username")
    private String username;
    @Property(nameInDb = "region")
    private String region;
    @Property(nameInDb = "switchNumber")
    private String switchNumber;
    @Generated(hash = 128492493)
    public UserEntity(Long id, String username, String region,
            String switchNumber) {
        this.id = id;
        this.username = username;
        this.region = region;
        this.switchNumber = switchNumber;
    }
    @Generated(hash = 1433178141)
    public UserEntity() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getRegion() {
        return this.region;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    public String getSwitchNumber() {
        return this.switchNumber;
    }
    public void setSwitchNumber(String switchNumber) {
        this.switchNumber = switchNumber;
    }
    

}
