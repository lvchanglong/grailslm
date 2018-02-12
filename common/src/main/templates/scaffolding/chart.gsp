<!DOCTYPE html>
<html>
	<head>
        <meta name="layout" content="main"/>
	</head>
	<body>
        <g:applyLayout name="\${application.platform.layout}">
            <div class="row">
                <div class="col-md-12">
                    <div id="canvasEx" style="width:100%;height:400px;"></div>
                </div>
                <div class="col-md-12">
                    <div id="instanceList" style="font-size:12px;margin-top:10px;" class="row"></div>
                </div>
            </div>

            <script>
                jQuery(function () {
                    jQuery.getJSON("\${createLink(controller:params.controller, action:'c1')}", function(json){
                        // 基于准备好的dom，初始化echarts实例
                        var myChart = echarts.init(document.getElementById('canvasEx'));

                        // 指定图表的配置项和数据
                        var option = {
                            backgroundColor: '#0f375f',
                            title: {
                                text: '表格',
                                textStyle: {
                                    color: 'white'
                                }
                            },
                            tooltip: {
                                trigger: 'axis',
                                axisPointer: {
                                    type: 'shadow'
                                }
                            },
                            legend: {
                                data: ['line', 'bar'],
                                textStyle: {
                                    color: '#ccc'
                                }
                            },
                            xAxis: {
                                data: json.labels,
                                axisLine: {
                                    lineStyle: {
                                        color: '#ccc'
                                    }
                                }
                            },
                            yAxis: {
                                splitLine: {show: false},
                                axisLine: {
                                    lineStyle: {
                                        color: '#ccc'
                                    }
                                }
                            },
                            series: [{
                                name: 'line',
                                type: 'line',
                                smooth: true,
                                showAllSymbol: true,
                                symbol: 'emptyCircle',
                                symbolSize: 15,
                                data: json.datas
                            }, {
                                name: 'bar',
                                type: 'bar',
                                barWidth: 10,
                                itemStyle: {
                                    normal: {
                                        barBorderRadius: 5,
                                        color: new echarts.graphic.LinearGradient(
                                                0, 0, 0, 1,
                                                [
                                                    {offset: 0, color: '#14c8d4'},
                                                    {offset: 1, color: '#43eec6'}
                                                ]
                                        )
                                    }
                                },
                                data: json.datas
                            }]
                        };

                        // 使用刚指定的配置项和数据显示图表。
                        myChart.setOption(option);

                        myChart.on('click', function (params) {
                            var instList = json.maps[params.name];
                            jQuery("#instanceList").html("");
                            if(instList) {
                                var sortedList = groupBy(instList, function (item) {
                                    return item.id;
                                });//分组

                                sortedList = sortedList.sort(function (a, b) {
                                    return b.length - a.length;
                                });//排序

                                jQuery.each(sortedList, function (i, inst) {
                                    jQuery("#instanceList").append("<div class='col-md-2 chart-box'>" + inst.length + "次 - " + inst[0].id + "</div>");
                                });
                            }
                        });
                    });
                });
            </script>
        </g:applyLayout>
	</body>
</html>