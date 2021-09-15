<%-- 
    Document   : chartCountPatientByYear
    Created on : Aug 14, 2021, 12:27:20 AM
    Author     : MSIGE66
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script type="text/javascript">
    window.onload = function () {

        var dps = [[]];
        var chart = new CanvasJS.Chart("chartContainer", {
            theme: "light2", //"light1", "dark1", "dark2"
            animationEnabled: true,
            axisX: {
                valueFormatString: "#",
                maximum: 12,
                minimum: 1,
                title: "<spring:message code="chart.month" />"
            },
            axisY: {
                title: "<spring:message code="chart.patient" />",
                maximum: null,
                minimum: 0,
            },
            data: [{
                    type: "column",
                    xValueType: "number",
                    xValueFormatString: "#",
                    yValueFormatString: "#,##0 bệnh nhân",
                    dataPoints: dps[0]
                }]
        });

        var xValue;
        var yValue;

    <c:forEach items="${dataPointsList}" var="dataPoints" varStatus="loop">
        <c:forEach items="${dataPoints}" var="dataPoint">
        xValue = parseInt("${dataPoint.x}");
        yValue = parseFloat("${dataPoint.y}");
        label = "${dataPoint.label}";
        indexLabel = "${dataPoint.indexLabel}";
        dps[parseInt("${loop.index}")].push({
            x: xValue,
            y: yValue
        });
        </c:forEach>
    </c:forEach>

        chart.render();

    }
</script>
<div class="container">


    <div class="row my-2">  

        <div class="col-md-11">
            <form action="">
                <div class="row">
                    <div class="col-md-4">
                        <input class="form-control" type="number" min="0" name="year" placeholder="Nhập từ khoá để tìm" />
                    </div>
                    <div class="col-md-1 p-0"> 
                        <input type="submit" value="<spring:message code="label.search" />" class="btn btn-warning"/>
                    </div>
                </div>
            </form>  
        </div>
    </div>
                    <h1 class="text-center"><spring:message code="chart.statisticPatientByYear" /> ${year}</h1>
                    
                    
                    
                    
    <h3><spring:message code="chart.monthOfMinPatient" />: ${statistic.get("minMonth")} - ${statistic.get("min")} <spring:message code="chart.patient" /></h3>      

    <h3><spring:message code="chart.monthOfMaxPatient" />: ${statistic.get("maxMonth")} - ${statistic.get("max")} <spring:message code="chart.patient" /></h3>       

    <h3><spring:message code="chart.totalPatientInYear" />: ${statistic.get("total")} <spring:message code="chart.patient" /></h3>
    <div id="chartContainer" style="height: 370px; width: 100%;"></div>
    <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
</div>
