package com.example.quizeapp;

import com.google.firebase.firestore.DocumentId;

public class QuizeListModule  {


    @DocumentId
    String   quiz_id;
  String   name;
  String  image;
   String  level;

    String desc;
      String    visibility;
      private  long  questions;
public QuizeListModule(){

}

    public QuizeListModule(String name, String image, String level, String quiz_id, String desc, String visibility, long questions) {
        this.name = name;
        this.image = image;
        this.level = level;
        this.quiz_id = quiz_id;
        this.desc = desc;
        this.visibility = visibility;
        this.questions = questions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(String quiz_id) {
        this.quiz_id = quiz_id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public long getQuestions() {
        return questions;
    }

    public void setQuestions(long questions) {
        this.questions = questions;
    }
}
