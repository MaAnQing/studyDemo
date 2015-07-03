package com.example.madroid.studydemo.volleyGson;

import java.util.List;

/**
 * @author: madroid
 * @date: 2015-07-01 16:41
 */
public class EcgDataInfo {
    private static final String TAG = "EcgDataInfo";
    /**
     * ECGItem : [{"ECGData":{"index":123,"value":[56142,81723,17263,187263,82163,876231,16291,87126,18723]},"time":12345678},{"ECGData":{"index":124,"value":[56142,81723,17263,187263,82163,876231,16291,87126,18723]},"time":123456546}]
     */
    private List<ECGItemEntity> ECGItem;

    public void setECGItem(List<ECGItemEntity> ECGItem) {
        this.ECGItem = ECGItem;
    }

    public List<ECGItemEntity> getECGItem() {
        return ECGItem;
    }


    public class ECGItemEntity {
        /**
         * ECGData : {"index":123,"value":[56142,81723,17263,187263,82163,876231,16291,87126,18723]}
         * time : 12345678
         */
        public ECGDataEntity ECGData;
        public int time;


        public class ECGDataEntity {
            /**
             * index : 123
             * value : [56142,81723,17263,187263,82163,876231,16291,87126,18723]
             */
            public int index;
            public int[] value;

        }
    }
}
