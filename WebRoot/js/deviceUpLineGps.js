function queryClick() {
    document.hrForm.action = "historicalRoute_historicalRouteList.action";
    document.hrForm.submit();
}

function HistoricalRouteMapClick(locatorName) {
    document.hrForm.action = locatorName + "/_toHistoricalRoute.action";
    document.hrForm.submit();
}




