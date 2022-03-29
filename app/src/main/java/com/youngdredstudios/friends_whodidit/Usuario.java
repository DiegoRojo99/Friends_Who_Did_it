package com.youngdredstudios.friends_whodidit;

import java.util.ArrayList;

public class Usuario {

    public String nombre, email, password;
    public ArrayList<UserAchievement> userAchievements;
    public Usuario(){

    }
    public Usuario(String nombre, String email, String password){
        this.nombre=nombre;
        this.email=email;
        this.password=password;
        this.userAchievements = new ArrayList<>();
    }
}
