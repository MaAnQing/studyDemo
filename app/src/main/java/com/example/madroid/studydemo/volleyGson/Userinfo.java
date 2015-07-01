package com.example.madroid.studydemo.volleyGson;

/**
 * @author: madroid
 * @date: 2015-07-01 09:27
 */
public class Userinfo {
    private static final String TAG = "User";
    /**
     * study : {"weixin":false,"bluetooth":true,"volley":true,"json":true,"gson":true}
     * address : anhui bozhou
     * name : madroid
     * age : 25
     */
    private StudyEntity study;
    private String address;
    private String name;
    private int age;

    public void setStudy(StudyEntity study) {
        this.study = study;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public StudyEntity getStudy() {
        return study;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public class StudyEntity {
        /**
         * weixin : false
         * bluetooth : true
         * volley : true
         * json : true
         * gson : true
         */
        private boolean weixin;
        private boolean bluetooth;
        private boolean volley;
        private boolean json;
        private boolean gson;

        public void setWeixin(boolean weixin) {
            this.weixin = weixin;
        }

        public void setBluetooth(boolean bluetooth) {
            this.bluetooth = bluetooth;
        }

        public void setVolley(boolean volley) {
            this.volley = volley;
        }

        public void setJson(boolean json) {
            this.json = json;
        }

        public void setGson(boolean gson) {
            this.gson = gson;
        }

        public boolean isWeixin() {
            return weixin;
        }

        public boolean isBluetooth() {
            return bluetooth;
        }

        public boolean isVolley() {
            return volley;
        }

        public boolean isJson() {
            return json;
        }

        public boolean isGson() {
            return gson;
        }
    }

    @Override
    public String toString() {
        return "{name:" + name + ",\nage:" + age + ",\naddress:" + address +",\nstudy: {\nweixin:" + study.weixin +
                ",\nvolley:" + study.volley + ",\nbluetooth:" + study.bluetooth + ",\njson:" + study.json +
                ",\ngson:" + study.gson +"}\n}" ;
    }
}
