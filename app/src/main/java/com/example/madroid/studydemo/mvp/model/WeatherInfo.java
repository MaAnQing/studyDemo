package com.example.madroid.studydemo.mvp.model;

/**
 * 天气实体类
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: com.example.madroid.studydemo.mvp.model.WeatherInfo.java
 * @author: madroid
 * @date: 2015-06-16 10:41
 */
public class WeatherInfo {
    private static final String TAG = "WeatherInfo";
    /**
     * weatherinfo : {"isRadar":"1","SD":"26%","temp":"10","city":"北京","Radar":"JC_RADAR_AZ9010_JB","WSE":"2","qy":"1012","njd":"暂无实况","cityid":"101010100","time":"10:25","WS":"2级","WD":"东南风"}
     */
    private WeatherInfoEntity weatherinfo;

    public void setWeatherInfo(WeatherInfoEntity weatherinfo) {
        this.weatherinfo = weatherinfo;
    }

    public WeatherInfoEntity getWeatherInfo() {
        return weatherinfo;
    }


    public class WeatherInfoEntity {
        /**
         * isRadar : 1
         * SD : 26%
         * temp : 10
         * city : 北京
         * Radar : JC_RADAR_AZ9010_JB
         * WSE : 2
         * qy : 1012
         * njd : 暂无实况
         * cityid : 101010100
         * time : 10:25
         * WS : 2级
         * WD : 东南风
         */
        private String isRadar;
        private String SD;
        private String temp;
        private String city;
        private String Radar;
        private String WSE;
        private String qy;
        private String njd;
        private String cityid;
        private String time;
        private String WS;
        private String WD;

        public void setIsRadar(String isRadar) {
            this.isRadar = isRadar;
        }

        public void setSD(String SD) {
            this.SD = SD;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setRadar(String Radar) {
            this.Radar = Radar;
        }

        public void setWSE(String WSE) {
            this.WSE = WSE;
        }

        public void setQy(String qy) {
            this.qy = qy;
        }

        public void setNjd(String njd) {
            this.njd = njd;
        }

        public void setCityid(String cityid) {
            this.cityid = cityid;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public void setWS(String WS) {
            this.WS = WS;
        }

        public void setWD(String WD) {
            this.WD = WD;
        }

        public String getIsRadar() {
            return isRadar;
        }

        public String getSD() {
            return SD;
        }

        public String getTemp() {
            return temp;
        }

        public String getCity() {
            return city;
        }

        public String getRadar() {
            return Radar;
        }

        public String getWSE() {
            return WSE;
        }

        public String getQy() {
            return qy;
        }

        public String getNjd() {
            return njd;
        }

        public String getCityid() {
            return cityid;
        }

        public String getTime() {
            return time;
        }

        public String getWS() {
            return WS;
        }

        public String getWD() {
            return WD;
        }
    }
}
