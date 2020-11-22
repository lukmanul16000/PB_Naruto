package com.example.pb_naruto.model;

public class AnimeList {
    private String mimage_url;
    private String mtitle;
    private int mepisodes;



    private int mal_id;


//    add more for intent
    private String murl;
    private String mairing;
    private String msynopsis;
    private String mtype;
    private String mscore;
    private String mstart_date;
    private String mend_date;
    private int mmembers;
    private String mrated;

    public AnimeList(){}

    public AnimeList(int mal_id,String image_url, String title, int episodes,String url,String airing,String synopsis,String type,String score,String start_date,String end_date,int members,String rated) {
        mal_id=mal_id;
        mimage_url = image_url;
        mtitle = title;
        mepisodes = episodes;
        murl = url;
        mairing = airing;
        msynopsis = synopsis;
        mtype =type ;
        mscore = score;
        mstart_date = start_date;
        mend_date = end_date;
        mmembers = members;
        mrated = rated;
    }

    public int getMal_id() {
        return mal_id;
    }

    public void setMal_id(int mal_id) {
        this.mal_id = mal_id;
    }
    public String getMimage_url() {
        return mimage_url;
    }

    public void setMimage_url(String mimage_url) {
        this.mimage_url = mimage_url;
    }

    public String getMtitle() {
        return mtitle;
    }

    public void setMtitle(String mtitle) {
        this.mtitle = mtitle;
    }

    public int getMepisodes() {
        return mepisodes;
    }

    public void setMepisodes(int mepisodes) {
        this.mepisodes = mepisodes;
    }


    public String getMurl() {
        return murl;
    }

    public void setMurl(String murl) {
        this.murl = murl;
    }

    public String getMairing() {
        return mairing;
    }

    public void setMairing(String mairing) {
        this.mairing = mairing;
    }

    public String getMsynopsis() {
        return msynopsis;
    }

    public void setMsynopsis(String msynopsis) {
        this.msynopsis = msynopsis;
    }

    public String getMtype() {
        return mtype;
    }

    public void setMtype(String mtype) {
        this.mtype = mtype;
    }

    public String getMscore() {
        return mscore;
    }

    public void setMscore(String mscore) {
        this.mscore = mscore;
    }

    public String getMstart_date() {
        return mstart_date;
    }

    public void setMstart_date(String mstart_date) {
        this.mstart_date = mstart_date;
    }

    public String getMend_date() {
        return mend_date;
    }

    public void setMend_date(String mend_date) {
        this.mend_date = mend_date;
    }

    public int getMmembers() {
        return mmembers;
    }

    public void setMmembers(int mmembers) {
        this.mmembers = mmembers;
    }

    public String getMrated() {
        return mrated;
    }

    public void setMrated(String mrated) {
        this.mrated = mrated;
    }
}
