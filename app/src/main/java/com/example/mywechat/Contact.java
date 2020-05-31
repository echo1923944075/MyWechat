package com.example.mywechat;
/*
    联系人实体类，包含name和phone两个字段
*/
class Contact {  //自定义的实体类
    private String name;
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contact{" + "name='" + name + '\'' + ", phone='" + phone + '\'' + '}';
    }
}
