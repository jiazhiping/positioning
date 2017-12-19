package model.gps.persistence;

import Common.CommonFunction;
import Common.Constants;
import model.IMaYueManager;
import model.gps.domain.GPS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.Common.utils.PossUtil;

import java.util.Date;
import java.util.List;

@Repository("gpsManager")
public class GPSManager implements IGPSManager {

    @Autowired
    private IMaYueManager maYueManager;

    @Override
    public String saveGPS(GPS gps) {
//        String ct = Constants.date_ymd.format(new Date());
//        String maxId = maYueManager.getMaxId("t_gps", "gps_id", ct, 6,
//                "where gps_id like '" + ct + "%'");
//        gps.setGpsId(maxId);
        gps.setGpsId(PossUtil.getAesKey());
        gps.setCreateTime(new Date());
        return (String) maYueManager.saveModel(gps);
    }

    @Override
    public GPS getGPS(String gpsId) {
        return maYueManager.getModel(GPS.class, gpsId);
    }

    @Override
    public List <GPS> getGPSName(String locatorName) {
        String whereHQL = "WHERE 1=1 ";
        if (!CommonFunction.VerdictNULL(locatorName)) {
            whereHQL += " AND gps_locator_name='" + locatorName + "'";
        }
        return maYueManager.getModelList(GPS.class, whereHQL + " ORDER BY create_time");
    }

    @Override
    public boolean updateGps(GPS gps) {
        gps.setUpdateTime(new Date());
        return maYueManager.updateModel(gps);
    }

    public IMaYueManager getMaYueManager() { return maYueManager; }

    public void setMaYueManager(IMaYueManager maYueManager) { this.maYueManager = maYueManager; }
}
