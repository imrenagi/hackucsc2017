$(function () {
    $.getJSON('https://www.highcharts.com/samples/data/jsonp.php?filename=usdeur.json&callback=?', function (data) {

        Highcharts.chart('container', {
            chart: {
                zoomType: 'x',
                style: {
                   fontFamily: 'Roboto'
                },
                backgroundColor: '#0d1a00'
            },
            title: {
                style: {
                   color: '#d9d9d9',
                   fontWeight: 'bold'
                },
                text: 'Daily Revenue over Time'
            },
            subtitle: {
                style: {
                   color: '#d9d9d9',
                   fontWeight: 'bold'
                },
                text: document.ontouchstart === undefined ?
                        'Click and drag in the plot area to zoom in' : 'Pinch the chart to zoom in'
            },
            xAxis: {
                type: 'datetime'
            },
            yAxis: {
                title: {
                    text: 'Revenue (USD)'
                }
            },
            legend: {
                enabled: false
            },
            plotOptions: {
                area: {
                    fillColor: {
                        linearGradient: {
                            x1: 0,
                            y1: 0,
                            x2: 0,
                            y2: 1
                        },
                        stops: [
                            [0, Highcharts.getOptions().colors[1]],
                            [1, Highcharts.Color(Highcharts.getOptions().colors[1]).setOpacity(0.8).get('rgba')]
                        ]
                    },
                    marker: {
                        radius: 2
                    },
                    lineWidth: 1,
                    states: {
                        hover: {
                            lineWidth: 1
                        }
                    },
                    threshold: null
                }
            },

            series: [{
                type: 'area',
                name: 'Revenue (USD)',
                data: data
            }]
        });
    });
});
