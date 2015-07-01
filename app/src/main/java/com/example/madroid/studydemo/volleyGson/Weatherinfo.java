package com.example.madroid.studydemo.volleyGson;

/**
 * @author: madroid
 * @date: 2015-07-01 11:16
 */
public class Weatherinfo {
    private static final String TAG = "Weatherinfo";
    /**
     * weatherinfo : {"isRadar":"1","SD":"22%","temp":"9","city":"北京","Radar":"JC_RADAR_AZ9010_JB","WSE":"2","qy":"1015","njd":"暂无实况","cityid":"101010100","time":"10:35","WS":"2级","WD":"西南风"}
     */
    private WeatherinfoEntity weatherinfo;

    public void setWeatherinfo(WeatherinfoEntity weatherinfo) {
        this.weatherinfo = weatherinfo;
    }

    public WeatherinfoEntity getWeatherinfo() {
        return weatherinfo;
    }

    public class WeatherinfoEntity {
        /**
         * isRadar : 1
         * SD : 22%
         * temp : 9
         * city : 北京
         * Radar : JC_RADAR_AZ9010_JB
         * WSE : 2
         * qy : 1015
         * njd : 暂无实况
         * cityid : 101010100
         * time : 10:35
         * WS : 2级
         * WD : 西南风
         */
        public String isRadar;
        public String SD;
        public String temp;
        public String city;
        public String Radar;
        public String WSE;
        public String qy;
        public String njd;
        public String cityid;
        public String time;
        public String WS;
        public String WD;

    }

    @Override
    public String toString() {
        return "weatherinfo : "  + "\n" +
                "             isRadar : " + weatherinfo.isRadar + "\n" +
                "                  SD : " + weatherinfo.SD + "\n" +
                "                temp : " + weatherinfo.temp + "\n" +
                "               Radar : " + weatherinfo.Radar + "\n" +
                "                 WSE : " + weatherinfo.WSE + "\n" +
                "                  qy : " + weatherinfo.qy + "\n" +
                "                 njd : " + weatherinfo.njd + "\n" +
                "              cityid : " + weatherinfo.cityid + "\n" +
                "                time : " + weatherinfo.time + "\n" +
                "                  WS : " + weatherinfo.WS + "\n" +
                "                  WD : " + weatherinfo.WD + "\n" ;
    }
}
