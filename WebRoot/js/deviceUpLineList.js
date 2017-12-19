function queryClick() {
    document.dForm.action = "deviceUpLine_deviceUpLineList.action";
    document.dForm.submit();
}

function monitoringClick(deviceId) {
    document.dForm.action = "monitoring_monitoringList.action?monitoringForm.deviceId=" + deviceId;
    document.dForm.submit();
}

function HistoricalRouteMapClick(locatorName) {
    document.dForm.action = locatorName + "/_toHistoricalRoute.action";
    document.dForm.submit();
}

function tomonitoringRecordList(locatorName) {
    document.dForm.action = "monitoringRecord_monitoringRecordList.action?monitoringRecordQueryForm.locatorName=" + locatorName;
    document.dForm.submit();
}

function toFrequencyClick(deviceId) {
    myzLayerOpen('设备上报频率变更', 470, 300, 'monitoring_updateFrequencyTo.action?monitoringForm.deviceId=' + deviceId, "")
}



