package model.gps.business;

import Common.CommonFunction;
import com.google.protobuf.InvalidProtocolBufferException;
import model.binding.business.IBindingService;
import model.binding.domain.BindingView;
import model.deviceUpLine.business.IDeviceUpLineService;
import model.deviceUpLine.domain.DeviceUpLine;
import model.foundation.business.IFoundationService;
import model.foundation.domain.Foundation;
import model.gps.domain.GPS;
import model.gps.persistence.IGPSManager;
import model.historicalRoute.business.IHistoricalRouteService;
import model.historicalRoute.domain.HistoricalRoute;
import model.locator.business.ILocatorService;
import model.locator.domain.Locator;
import model.monitoringRecord.business.IMonitoringRecordService;
import model.monitoringRecord.domain.Monitoring;
import model.terminal.business.ITerminalService;
import model.terminal.domain.Terminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.Common.proto.Protocol;
import web.Common.proto.Protocol.LocationReq.Accuracy;
import web.Common.utils.Gps;
import web.Common.utils.PositionUtil;

import java.util.List;

@Service("gpsService")
@Transactional
public class GPSService implements IGPSService {
    @Autowired
    private IGPSManager gpsManager;
    @Autowired
    private IDeviceUpLineService deviceUpLineService;
    @Autowired
    private IMonitoringRecordService monitoringRecordService;
    @Autowired
    private IHistoricalRouteService historicalRouteService;
    @Autowired
    private ILocatorService locatorService;
    @Autowired
    private ITerminalService terminalService;
    @Autowired
    private IBindingService bindingService;
    @Autowired
    private IFoundationService foundationService;

    @Override
    public void getGPS(String snId, String reqDev, Protocol.Scoped scoped) {
        String gpsId;//GpsId
        GPS gps = new GPS(); //GPS表
        HistoricalRoute historicalRoute;//历史定位信息表
        Monitoring monitoring;//告警表
        DeviceUpLine deviceUpLine;//已上线设备表
        Locator locator;//定位器表
        Terminal terminal;//终端表
        BindingView bindingView;//绑定视图
        Foundation foundation;//系统默认值表

        String locatorPositionLng = "";// 预设位置:经度
        String locatorPositionLat = "";// 预设位置:纬度
        String locatorFrequency = "";//上报频率
        String deviceDistance = "0";//偏移距离
        String bindingId = "";// 绑定Id
        String deviceId = "";//设备Id
        int deviceAlarm = 0;//设备告警状态 无告警：0 有告警：1

        Protocol.LocationReq locationReq;
        try {
            locationReq = Protocol.LocationReq.parseFrom(scoped.getData());
            if (!CommonFunction.VerdictNULL(locationReq)) {
                System.out.println("定位器序列号:" + snId);
                Accuracy accuracy = locationReq.getAcc();
                Protocol.LocationReq.GPS reqGps = locationReq.getGps();
                Gps newGps = null;
                switch (accuracy.getNumber()) {
				case Accuracy.kHightAccuracy_VALUE:
					Gps gcj02 = PositionUtil.gps84_To_Gcj02(reqGps.getLatitude(),reqGps.getLongtitude());
					newGps = PositionUtil.gps84_To_Gcj02(gcj02.getWgLat(),gcj02.getWgLon());
					break;
				case Accuracy.kLowAccuracy_VALUE:
					newGps = PositionUtil.gcj02_To_Bd09(reqGps.getLatitude(),reqGps.getLongtitude());
					break;
				default:
					break;
				}
                if (locationReq.hasGps()) {
                    //todo
                    gps.setLocatorName(String.valueOf(snId));//定位器序列号
                    gps.setTerminalName(String.valueOf(reqDev));//终端序列号
                    if(newGps != null){
                    	gps.setLongitude(String.valueOf(newGps.getWgLon()));//经度
                    	gps.setLatitude(String.valueOf(newGps.getWgLat()));//纬度
                    }
                    gps.setAltitude(String.valueOf(reqGps.getAltitude()));//海拔
                    gps.setBattery(String.valueOf(reqGps.getBattery()));//电池
                    gps.setCourse(String.valueOf(reqGps.getCourse()));//移动距离
                    gps.setUtc(CommonFunction.getDateByTime(Integer.valueOf(reqGps.getUtc()).longValue()));//时间
                    gps.setVelocity(String.valueOf(reqGps.getVelocity()));//移动速度
                    //保存GPS信息
                    gpsId = gpsManager.saveGPS(gps);
                    //查询已保存GPS信息
                    gps = gpsManager.getGPS(gpsId);
                    locator = locatorService.getLocatorLocatorName(null, gps.getLocatorName());
                    if (!CommonFunction.VerdictNULL(locator)) {
                        locatorPositionLng = locator.getLocatorPositionLng();
                        locatorPositionLat = locator.getLocatorPositionLat();
                        deviceDistance = CommonFunction.getDistance(locatorPositionLng, locatorPositionLat, gps.getLongitude(), gps.getLatitude());
                    }
                    bindingView = bindingService.getBindingName(gps.getLocatorName(), gps.getTerminalName());
                    if (!CommonFunction.VerdictNULL(bindingView)) {
                        bindingId = bindingView.getBindingId();
                    }

                    if (!CommonFunction.VerdictNULL(snId)) {//判断定位器是否合法
                        deviceUpLine = deviceUpLineService.getDeviceUpLineName(gps.getLocatorName());//查询该定位器是否存在
                        if (CommonFunction.VerdictNULL(deviceUpLine)) {
                            if (!CommonFunction.VerdictNULL(locator)) {
                                //已知定位器上线
                                monitoring = new Monitoring();
                                monitoring.setGpsId(gps.getGpsId());
                                monitoring.setBindingId(bindingId);
                                monitoring.setMonitoringType("已知定位器上线");
                                monitoring.setMonitoringGrade(0);//告警级别为:提示
                                monitoringRecordService.saveMonitoringRecord(monitoring);
                                deviceAlarm = 1;
                            } else {
                                //未知定位器上线
                                monitoring = new Monitoring();
                                monitoring.setGpsId(gps.getGpsId());
                                monitoring.setBindingId(bindingId);
                                monitoring.setMonitoringType("未知定位器上线");
                                monitoring.setMonitoringGrade(2);//告警级别为:主要
                                monitoringRecordService.saveMonitoringRecord(monitoring);
                                deviceAlarm = 3;
                            }
                            //该定位器未上线
                            deviceUpLine = new DeviceUpLine();
                            deviceUpLine.setLocatorName(gps.getLocatorName());// 定位器序列号
                            deviceUpLine.setTerminalName(gps.getTerminalName());// POS终端序列号
                            deviceUpLine.setDevicePositionLongitude(locatorPositionLng);// 预设位置:经度
                            deviceUpLine.setDevicePositionLatitude(locatorPositionLat);// 预设位置:纬度
                            deviceUpLine.setDeviceCurrentPositionLongitude(gps.getLongitude());// 当前位置:经度
                            deviceUpLine.setDeviceCurrentPositionLatitude(gps.getLatitude());// 当前位置:纬度
                            deviceUpLine.setDeviceDistance(deviceDistance);// 偏移距离
                            deviceUpLine.setDeviceFrequency(locatorFrequency);//上报频率
                            deviceId = deviceUpLineService.saveDeviceUpLine(deviceUpLine);
                        } else {
                            //该定位器已上线
                            deviceUpLine.setDevicePositionLongitude(locatorPositionLng);
                            deviceUpLine.setDevicePositionLatitude(locatorPositionLat);
                            deviceUpLine.setDeviceCurrentPositionLongitude(gps.getLongitude());
                            deviceUpLine.setDeviceCurrentPositionLatitude(gps.getLatitude());
                            deviceUpLine.setDeviceDistance(deviceDistance);
                            deviceUpLine.setDeviceBattery(gps.getBattery());
                            deviceUpLineService.updateDeviceUpLine(deviceUpLine);
                            deviceId = deviceUpLine.getDeviceId();
                            foundation = foundationService.getFoundation();
                            if (CommonFunction.Compares(deviceDistance, foundation.getThreshold())) {
                                //定位器位置异常
                                monitoring = new Monitoring();
                                monitoring.setGpsId(gps.getGpsId());
                                monitoring.setBindingId(bindingId);
                                monitoring.setMonitoringType("定位器位置异常");
                                monitoring.setMonitoringGrade(3);//告警级别为:严重
                                monitoringRecordService.saveMonitoringRecord(monitoring);
                                deviceAlarm = 4;
                            }
                            if (Integer.valueOf(gps.getBattery()) < Integer.valueOf(foundation.getBattery())) {
                                //定位器电量低异常
                                monitoring = new Monitoring();
                                monitoring.setGpsId(gps.getGpsId());
                                monitoring.setBindingId(bindingId);
                                monitoring.setMonitoringType("定位器电量低");
                                monitoring.setMonitoringGrade(3);//告警级别为:严重
                                monitoringRecordService.saveMonitoringRecord(monitoring);
                                deviceAlarm = 4;
                            }
                        }

                        terminal = terminalService.getTerminalTerminalName(null, gps.getTerminalName());//查询该终端是否存在
                        if (CommonFunction.VerdictNULL(terminal)) {
                            //POS终端信息不匹配
                            monitoring = new Monitoring();
                            monitoring.setGpsId(gps.getGpsId());
                            monitoring.setBindingId(bindingId);
                            monitoring.setMonitoringType("POS终端信息不匹配");
                            monitoring.setMonitoringGrade(2);//告警级别为:主要
                            monitoringRecordService.saveMonitoringRecord(monitoring);
                            deviceAlarm = 3;
                        } else if (CommonFunction.VerdictNULL(bindingId)) {
                            //POS终端信息未绑定
                            monitoring = new Monitoring();
                            monitoring.setGpsId(gps.getGpsId());
                            monitoring.setBindingId(bindingId);
                            monitoring.setMonitoringType("POS终端信息未绑定");
                            monitoring.setMonitoringGrade(1);//告警级别为:次要
                            monitoringRecordService.saveMonitoringRecord(monitoring);
                            deviceAlarm = 2;
                        }

                        historicalRoute = new HistoricalRoute();
                        historicalRoute.setGpsId(gps.getGpsId());
                        historicalRoute.setDeviceId(deviceId);
                        historicalRoute.setDistance(deviceDistance);
                        historicalRouteService.saveHistoricalRoute(historicalRoute);
                    } else {
                        //非法POS终端信息
                        monitoring = new Monitoring();
                        monitoring.setGpsId(gps.getGpsId());
                        monitoring.setBindingId("");//绑定Id为空
                        monitoring.setMonitoringType("非法POS终端信息");
                        monitoring.setMonitoringGrade(2);//告警级别为:主要
                        monitoringRecordService.saveMonitoringRecord(monitoring);
                        deviceAlarm = 3;
                    }
                    deviceUpLine = deviceUpLineService.getDeviceUpLine(deviceId);
                    if(deviceUpLine != null){
                    	deviceUpLine.setDeviceAlarm(deviceAlarm);
                    	deviceUpLineService.updateDeviceAlarm(deviceUpLine);
                    }

                } else if (locationReq.hasLbs()) {
                    Protocol.LocationReq.LBS reqLbs = locationReq.getLbs();
                    //todo
                } else {
                    new Throwable(new String("location req invalid"));
                }
            }
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }

    @Override
    public GPS getGPSName(String locatorName) {
        List <GPS> gpss = gpsManager.getGPSName(locatorName);
        if (gpss != null) {
            return gpss.get(0);
        } else {
            return null;
        }

    }

    @Override
    public GPS getGPS(String gpsId) {
        return gpsManager.getGPS(gpsId);
    }

    public IGPSManager getGpsManager() { return gpsManager; }

    public void setGpsManager(IGPSManager gpsManager) { this.gpsManager = gpsManager; }

    public IDeviceUpLineService getDeviceUpLineService() { return deviceUpLineService; }

    public void setDeviceUpLineService(IDeviceUpLineService deviceUpLineService) { this.deviceUpLineService = deviceUpLineService; }

    public IMonitoringRecordService getMonitoringRecordService() { return monitoringRecordService; }

    public void setMonitoringRecordService(IMonitoringRecordService monitoringRecordService) { this.monitoringRecordService = monitoringRecordService; }

    public IHistoricalRouteService getHistoricalRouteService() { return historicalRouteService; }

    public void setHistoricalRouteService(IHistoricalRouteService historicalRouteService) { this.historicalRouteService = historicalRouteService; }

    public ILocatorService getLocatorService() { return locatorService; }

    public void setLocatorService(ILocatorService locatorService) { this.locatorService = locatorService; }

    public ITerminalService getTerminalService() { return terminalService; }

    public void setTerminalService(ITerminalService terminalService) { this.terminalService = terminalService; }

    public IBindingService getBindingService() { return bindingService; }

    public void setBindingService(IBindingService bindingService) { this.bindingService = bindingService; }

    public IFoundationService getFoundationService() { return foundationService; }

    public void setFoundationService(IFoundationService foundationService) { this.foundationService = foundationService; }
}
