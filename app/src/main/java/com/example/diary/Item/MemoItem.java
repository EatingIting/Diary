package com.example.diary.Item;

public class MemoItem {
    private String title;
    private String content;
    private String weather; //★
//    private Uri imageUri;

    public MemoItem() {
        title = null;
        content = null;
        weather = null; //★
        //       imageUri = null;
    }

    public MemoItem(String title, String content, String weather/*, Uri imageUri*/) {
        this.title = title;
        this.content = content;
        this.weather = weather;
        //    this.imageUri = imageUri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
    /*
        public Uri getImageUri() {
            return imageUri;
        }

        public void setImageUri(Uri imageUri) {
            this.imageUri = imageUri;
        }
    */
    @Override
    public String toString() {
        return "MemoItem{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", weather='" + weather +/* '\'' +
                ", imageUri=" + imageUri +*/
                '}';
    }
}