"use strict";
$(document).ready(function () {
  dashboard();

  /*Counter Js Starts*/
  $(".counter").counterUp({
    delay: 10,
    time: 400,
  });
  /*Counter Js Ends*/

  //  Resource bar
  $(".resource-barchart").sparkline(
    [5, 6, 2, 4, 9, 1, 2, 8, 3, 6, 4, 2, 1, 5],
    {
      type: "bar",
      barWidth: "15px",
      height: "80px",
      barColor: "#fff",
      tooltipClassname: "abc",
    }
  );

  function setHeight() {
    var $window = $(window);
    var windowHeight = $(window).height();
    if ($window.width() >= 320) {
      $(".user-list").parent().parent().css("min-height", windowHeight);
      $(".chat-window-inner-content").css("max-height", windowHeight);
      $(".user-list").parent().parent().css("right", -300);
    }
  }
  setHeight();

  $(window).on("load", function () {
    setHeight();
  });
});

$(window).resize(function () {
  dashboard();
  //  Resource bar
  $(".resource-barchart").sparkline(
    [5, 6, 2, 4, 9, 1, 2, 8, 3, 6, 4, 2, 1, 5],
    {
      type: "bar",
      barWidth: "15px",
      height: "80px",
      barColor: "#fff",
      tooltipClassname: "abc",
    }
  );
});

function dashboard() {}

Highcharts.chart("barchart", {
  title: {
    text: "INGÉNIEURIE",
  },
  xAxis: {
    categories: ["FSI"],
  },
  series: [
    {
      type: "column",
      name: "Génie Informatique",
      data: [100],
      color: "#f57c00",
    },
    {
      type: "column",
      name: "Génie Civil",
      data: [120],
      color: "#2BBBAD",
    },
    {
      type: "column",
      name: "Génie des Énergies Renouvelables",
      data: [30],
      color: "#37417e",
    },
    {
      type: "column",
      name: "Génie des Systèmes Embarqués",
      data: [24],
      color: "#69444e",
    },
    {
      type: "column",
      name: "Transformation Digitale",
      data: [60],
      color: "#84144e",
    },
  ],
});

function dashboard() {}

Highcharts.chart("barchart1", {
  title: {
    text: "BUSINESS",
  },
  xAxis: {
    categories: ["FBS"],
  },
  series: [
    {
      type: "column",
      name: "Comptabilé, Contrôle et Audit",
      data: [3],
      color: "#f57c00",
    },
    {
      type: "column",
      name: "Supply Chain Management",
      data: [4],
      color: "#2BBBAD",
    },
    {
      type: "column",
      name: "Marketing & Commmunication",
      data: [3],
      color: "#37417e",
    },
    {
      type: "column",
      name: "Ingénieur des Affaires",
      data: [2],
      color: "#69444e",
    },
    {
      type: "column",
      name: "Finance, Banque et Assurance",
      data: [5],
      color: "#84144e",
    },
    {
      type: "column",
      name: "Commerce, Vente et Distribution",
      data: [2],
      color: "#14844e",
    },
    {
      type: "column",
      name: "Logistique et Transport",
      data: [4],
      color: "#14844e",
    },
  ],
});

Highcharts.chart("barchart2", {
  title: {
    text: "ARCHITECTURE & URBANISME",
  },
  xAxis: {
    categories: ["ESMAB"],
  },
  series: [
    {
      type: "column",
      name: "Urbanisme & Aménagement",
      data: [4],
      color: "#f57c00",
    },
    {
      type: "column",
      name: "Architecte d'interieur",
      data: [6],
      color: "#2BBBAD",
    },
    {
      type: "column",
      name: "Architecte du Paysage",
      data: [5],
      color: "#37417e",
    },
    {
      type: "column",
      name: "Design de Produit",
      data: [9],
      color: "#69444e",
    }
  ],
});

Highcharts.chart("piechart", {
  chart: {
    type: "pie",
    options3d: {
      enabled: true,
      alpha: 45,
      beta: 0,
    },
    //  backgroundColor:'#fff'
  },
  title: {
    text: "Browser market shares at a specific website, 2014",
  },
  tooltip: {
    pointFormat: "{series.name}: <b>{point.percentage:.1f}%</b>",
  },
  plotOptions: {
    pie: {
      allowPointSelect: true,
      cursor: "pointer",
      depth: 35,
      dataLabels: {
        enabled: true,
        format: "{point.name}",
      },
    },
  },
  series: [
    {
      name: "Établissement",
      data: [
        {
          name: "FBS",
          y: 45.0,
          sliced: true,
          color: "#d12f3e",
        },
        {
          name: "FSI",
          y: 37.2,
          color: "#173e8b",
        },
        {
          name: "ESMAB",
          y: 20.7,
          color: "#b61674",
        },
      ],
    },
  ],
});
