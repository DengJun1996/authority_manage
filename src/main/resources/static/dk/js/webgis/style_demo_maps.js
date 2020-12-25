var host=WebGISDemoDataURL;
console.log("hots",host);

var darkmapstyle={
  "version": 8,
  "name": "world",
  "metadata": {"maputnik:renderer": "mbgljs"},
  "sources": {
    "world": {
      "tiles": [
        host+"/demotiles/csg/{z}/{x}/{y}.mvt"
      ],
      "type": "vector"
    },
    "csgbound": {
      "type": "geojson",
      "data": host+"/demotiles/csg.json"
    },
    "fs_tiles": {
      "tiles": [
        host+"/demotiles/foshan/{z}/{x}/{y}.mvt"
      ],
      "type": "vector",
      "bounds": [
        97.52872078822246,
        18.157639005000004,
        117.19213091999995,
        29.222985015000017
      ]
    }
  },
  "sprite":  host+"/assets/sprites/mergesprite",
  "glyphs":  host+"/assets/{fontstack}/{range}.pbf",
  "layers": [
    {
      "id": "background",
      "type": "background",
      "layout": {"visibility": "visible"},
      "paint": {"background-color": "rgba(10, 20, 28, 1)"}
    },
    {
      "id": "world@csg",
      "type": "fill",
      "metadata": {"description": "世界地图填充面"},
      "source": "world",
      "source-layer": "world@csg",
      "minzoom": 0,
      "maxzoom": 19.01,
      "layout": {"visibility": "visible"},
      "paint": {"fill-color": "rgba(26, 35, 44, 1)", "fill-antialias": true}
    },
    {
      "id": "nine_line@csg",
      "type": "line",
      "metadata": {"description": "九段线"},
      "source": "world",
      "source-layer": "nine_line@csg",
      "minzoom": 0,
      "maxzoom": 9.01,
      "layout": {"visibility": "visible"},
      "paint": {"line-width": 1, "line-color": "rgba(96, 96, 103, 1)"}
    },
    {
      "id": "china_province@csg",
      "type": "fill",
      "metadata": {"description": "中国省份"},
      "source": "world",
      "source-layer": "china_province@csg",
      "minzoom": 3,
      "maxzoom": 9.01,
      "layout": {"visibility": "visible"},
      "paint": {"fill-color": "rgba(26, 35, 44, 1)", "fill-antialias": true}
    },
    {
      "id": "china_province@csg_outline",
      "type": "line",
      "metadata": {"description": "中国省份轮廓"},
      "source": "world",
      "source-layer": "china_province@csg",
      "minzoom": 3,
      "maxzoom": 9.01,
      "layout": {"visibility": "visible"},
      "paint": {"line-width": 1, "line-color": "rgba(10, 20, 28, 1)"}
    },
    {
      "id": "csg_province@csg",
      "type": "fill",
      "metadata": {"description": "南网五省"},
      "source": "world",
      "source-layer": "csg_province@csg",
      "minzoom": 3,
      "maxzoom": 9.01,
      "layout": {"visibility": "visible"},
      "paint": {"fill-color": "rgba(26, 35, 44, 1)", "fill-antialias": true}
    },
    {
      "id": "csg_city@csg",
      "type": "fill",
      "metadata": {"description": "南网地市"},
      "source": "world",
      "source-layer": "csg_city@csg",
      "minzoom": 5,
      "maxzoom": 9.01,
      "layout": {"visibility": "visible"},
      "paint": {"fill-color": "rgba(26, 35, 44, 1)", "fill-antialias": true}
    },
    {
      "id": "csg_city@csg_outline",
      "type": "line",
      "metadata": {"description": "南网地市轮廓"},
      "source": "world",
      "source-layer": "csg_city@csg",
      "minzoom": 5,
      "maxzoom": 9.01,
      "layout": {"visibility": "visible"},
      "paint": {
        "line-width": 0.38,
        "line-color": "rgba(96, 96, 103, 1)",
        "line-opacity": 0.3
      }
    },
    {
      "id": "csg_background",
      "type": "fill",
      "source": "csgbound",
      "minzoom": 8,
      "maxzoom": 19.01,
      "layout": {"visibility": "visible"},
      "paint": {"fill-color": "rgba(26, 35, 44, 1)", "fill-antialias": true}
    },
    {
      "id": "world@csg_outline",
      "type": "line",
      "metadata": {"description": "世界地图轮廓线"},
      "source": "world",
      "source-layer": "world@csg",
      "minzoom": 0,
      "maxzoom": 9.01,
      "layout": {"visibility": "visible"},
      "paint": {"line-width": 1, "line-color": "rgba(10, 20, 28, 1)"}
    },
    {
      "id": "iland@fs",
      "type": "fill",
      "metadata": {"description": "佛山岛屿"},
      "source": "fs_tiles",
      "source-layer": "iland@fs",
      "minzoom": 8,
      "maxzoom": 21,
      "layout": {"visibility": "visible"},
      "paint": {"fill-color": "rgba(26, 35, 44, 1)", "fill-antialias": true}
    },
    {
      "id": "greenland@fs",
      "type": "fill",
      "metadata": {"description": "佛山绿地"},
      "source": "fs_tiles",
      "source-layer": "greenland@fs",
      "minzoom": 8,
      "maxzoom": 21,
      "filter": [">=", "DISPCLASS", "4"],
      "layout": {"visibility": "visible"},
      "paint": {"fill-color": "rgba(34, 45, 55, 1)", "fill-antialias": true}
    },
    {
      "id": "greenland2",
      "type": "fill",
      "metadata": {"description": "佛山绿地"},
      "source": "fs_tiles",
      "source-layer": "greenland@fs",
      "minzoom": 12,
      "maxzoom": 21,
      "filter": ["<", "DISPCLASS", "4"],
      "layout": {"visibility": "visible"},
      "paint": {"fill-color": "rgba(34, 45, 55, 1)", "fill-antialias": true}
    },
    {
      "id": "function_User@fs",
      "type": "fill",
      "metadata": {"description": "功能区"},
      "source": "fs_tiles",
      "source-layer": "function_User@fs",
      "minzoom": 13,
      "maxzoom": 21,
      "layout": {"visibility": "visible"},
      "paint": {"fill-color": "rgba(32, 43, 52, 1)", "fill-antialias": true}
    },
    {
      "id": "water@fs",
      "type": "fill",
      "metadata": {"description": "水系"},
      "source": "fs_tiles",
      "source-layer": "water@fs",
      "minzoom": 13,
      "maxzoom": 21,
      "filter": ["<", "DISPCLASS", "4"],
      "layout": {"visibility": "visible"},
      "paint": {"fill-color": "rgba(10, 20, 28, 1)", "fill-antialias": true}
    },
    {
      "id": "water2",
      "type": "fill",
      "metadata": {"description": "水系"},
      "source": "fs_tiles",
      "source-layer": "water@fs",
      "minzoom": 8,
      "maxzoom": 21,
      "filter": [">=", "DISPCLASS", "4"],
      "layout": {"visibility": "visible"},
      "paint": {"fill-color": "rgba(10, 20, 28, 1)", "fill-antialias": true}
    },
    {
      "id": "build@fs",
      "type": "fill-extrusion",
      "metadata": {"description": "建筑物"},
      "source": "fs_tiles",
      "source-layer": "build@fs",
      "minzoom": 14,
      "maxzoom": 21,
      "layout": {"visibility": "visible"},
      "paint": {
        "fill-extrusion-color": "rgba(39, 49, 58, 1)",
        "fill-extrusion-height": ["*", ["to-number", ["get", "floor"]], 3],
        "fill-extrusion-base": 2,
        "fill-extrusion-opacity": 0.25
      }
    },
    {
      "id": "csg_outline@csg",
      "type": "line",
      "metadata": {"layer:caption": "csg_outline@csg"},
      "source": "world",
      "source-layer": "csg_province@csg",
      "minzoom": 3,
      "maxzoom": 19.01,
      "layout": {"visibility": "visible"},
      "paint": {
        "line-width": 1,
        "line-color": "rgba(96, 96, 103, 1)",
        "line-opacity": 0.8
      }
    },
    {
      "id": "sidewalk@fs-border",
      "type": "line",
      "metadata": {"description": "行人道"},
      "source": "fs_tiles",
      "source-layer": "sidewalk@fs",
      "minzoom": 15,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "line-cap": "round",
        "line-join": "round"
      },
      "paint": {
        "line-translate-anchor": "viewport",
        "line-width": {
          "stops": [
            [9, 0],
            [10, 0],
            [11, 0],
            [12, 0],
            [13, 0],
            [14, 0.6],
            [15, 0.8],
            [16, 0.8],
            [17, 1],
            [18, 1],
            [19, 1],
            [20, 1.2],
            [21, 1.2]
          ],
          "base": 1.2
        },
        "line-gap-width": {
          "stops": [
            [9, 0],
            [10, 0],
            [11, 0],
            [12, 0],
            [13, 0],
            [14, 1.2],
            [15, 2.4],
            [16, 3.2],
            [17, 6],
            [18, 8],
            [19, 10],
            [20, 10],
            [21, 10]
          ],
          "base": 1.2
        },
        "line-color": "rgba(31, 42, 53, 1)"
      }
    },
    {
      "id": "sidewalk@fs",
      "type": "line",
      "metadata": {"description": "行人道"},
      "source": "fs_tiles",
      "source-layer": "sidewalk@fs",
      "minzoom": 15,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "line-cap": "round",
        "line-join": "round"
      },
      "paint": {
        "line-translate-anchor": "viewport",
        "line-width": {
          "stops": [
            [9, 0],
            [10, 0],
            [11, 0],
            [12, 0],
            [13, 1],
            [14, 1.2],
            [15, 2.4],
            [16, 3.2],
            [17, 6],
            [18, 8],
            [19, 10],
            [20, 10],
            [21, 10]
          ],
          "base": 1.2
        },
        "line-color": "rgba(6, 13, 22, 1)"
      }
    },
    {
      "id": "nineway@fs-border",
      "type": "line",
      "metadata": {"description": "九级公路"},
      "source": "fs_tiles",
      "source-layer": "nineway@fs",
      "minzoom": 15,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "line-cap": "round",
        "line-join": "round"
      },
      "paint": {
        "line-translate-anchor": "viewport",
        "line-width": {
          "stops": [
            [9, 0],
            [10, 0],
            [11, 0],
            [12, 0],
            [13, 0],
            [14, 0.4],
            [15, 0.6],
            [16, 0.8],
            [17, 1],
            [18, 1],
            [19, 1]
          ],
          "base": 1.2
        },
        "line-gap-width": {
          "stops": [
            [9, 0.8],
            [10, 1],
            [11, 1],
            [12, 1],
            [13, 1],
            [14, 2],
            [15, 4],
            [16, 6],
            [17, 10],
            [18, 10],
            [19, 10],
            [20, 10],
            [21, 12]
          ],
          "base": 1.2
        },
        "line-color": "rgba(31, 42, 53, 1)"
      }
    },
    {
      "id": "nineway@fs",
      "type": "line",
      "metadata": {"description": "九级公路"},
      "source": "fs_tiles",
      "source-layer": "nineway@fs",
      "minzoom": 15,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "line-cap": "round",
        "line-join": "round"
      },
      "paint": {
        "line-translate-anchor": "viewport",
        "line-width": {
          "stops": [
            [9, 0.8],
            [10, 1],
            [11, 1],
            [12, 1],
            [13, 1],
            [14, 2],
            [15, 4],
            [16, 6],
            [17, 10],
            [18, 10],
            [19, 10],
            [20, 10],
            [21, 12]
          ],
          "base": 1.2
        },
        "line-color": "rgba(6, 13, 22, 1)"
      }
    },
    {
      "id": "otherway@fs-border",
      "type": "line",
      "metadata": {"description": "其他道路"},
      "source": "fs_tiles",
      "source-layer": "otherway@fs",
      "minzoom": 15,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "line-cap": "butt",
        "line-join": "round"
      },
      "paint": {
        "line-translate-anchor": "viewport",
        "line-width": {
          "stops": [
            [9, 0],
            [10, 0],
            [11, 0],
            [12, 0],
            [13, 0],
            [14, 0.6],
            [15, 0.8],
            [16, 0.8],
            [17, 1],
            [18, 1],
            [19, 1],
            [20, 1.2],
            [21, 1.2]
          ],
          "base": 1.2
        },
        "line-gap-width": {
          "stops": [
            [9, 0.8],
            [10, 1],
            [11, 1],
            [12, 1],
            [13, 1.2],
            [14, 1.4],
            [15, 3],
            [16, 6.4],
            [17, 10],
            [18, 10],
            [19, 10],
            [20, 11],
            [21, 11]
          ],
          "base": 1.2
        },
        "line-color": "rgba(31, 42, 53, 1)"
      }
    },
    {
      "id": "otherway@fs",
      "type": "line",
      "metadata": {"description": "其他道路"},
      "source": "fs_tiles",
      "source-layer": "otherway@fs",
      "minzoom": 15,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "line-cap": "round",
        "line-join": "round"
      },
      "paint": {
        "line-translate-anchor": "viewport",
        "line-width": {
          "stops": [
            [9, 0.8],
            [10, 1],
            [11, 1],
            [12, 1],
            [13, 1.2],
            [14, 1.4],
            [15, 3],
            [16, 6.4],
            [17, 10],
            [18, 10],
            [19, 10],
            [20, 10],
            [21, 10]
          ],
          "base": 1.2
        },
        "line-color": "rgba(6, 13, 22, 1)"
      }
    },
    {
      "id": "countryway@fs-border",
      "type": "line",
      "metadata": {"description": "乡道"},
      "source": "fs_tiles",
      "source-layer": "countryway@fs",
      "minzoom": 11,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "line-cap": "butt",
        "line-join": "round"
      },
      "paint": {
        "line-translate-anchor": "viewport",
        "line-width": {
          "stops": [
            [5, 0],
            [6, 0],
            [7, 0],
            [8, 0],
            [9, 0],
            [10, 0.3],
            [11, 0.5],
            [12, 0.5],
            [13, 0.6],
            [14, 0.6],
            [15, 0.8],
            [16, 0.8],
            [17, 1],
            [18, 1],
            [19, 1],
            [20, 1.2],
            [21, 1.2]
          ],
          "base": 1.2
        },
        "line-gap-width": {
          "stops": [
            [5, 0.7],
            [6, 0.7],
            [7, 0.7],
            [8, 0.8],
            [9, 0.8],
            [10, 1.2],
            [11, 1.6],
            [12, 1.6],
            [13, 2],
            [14, 2.8],
            [15, 7.2],
            [16, 10],
            [17, 16],
            [18, 16],
            [19, 16],
            [20, 18],
            [21, 18]
          ],
          "base": 1.2
        },
        "line-color": {
          "stops": [[11, "rgba(31, 42, 53, 1)"], [19, "rgba(31, 42, 53, 1)"]],
          "base": 1
        }
      }
    },
    {
      "id": "countryway@fs",
      "type": "line",
      "metadata": {"description": "乡道"},
      "source": "fs_tiles",
      "source-layer": "countryway@fs",
      "minzoom": 11,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "line-cap": "round",
        "line-join": "round"
      },
      "paint": {
        "line-translate-anchor": "viewport",
        "line-width": {
          "stops": [
            [5, 0.7],
            [6, 0.7],
            [7, 0.7],
            [8, 0.8],
            [9, 0.8],
            [10, 1.2],
            [11, 1.6],
            [12, 1.8],
            [13, 2],
            [14, 2.8],
            [15, 7.2],
            [16, 10],
            [17, 16],
            [18, 16],
            [19, 16],
            [20, 18],
            [21, 18]
          ],
          "base": 1.2
        },
        "line-color": "rgba(6, 13, 22, 1)"
      }
    },
    {
      "id": "countyway@fs-border",
      "type": "line",
      "metadata": {"description": "县道"},
      "source": "fs_tiles",
      "source-layer": "countyway@fs",
      "minzoom": 10,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "line-cap": "butt",
        "line-join": "round"
      },
      "paint": {
        "line-translate-anchor": "viewport",
        "line-width": {
          "stops": [
            [5, 0],
            [6, 0],
            [7, 0],
            [8, 0],
            [9, 0],
            [10, 0.4],
            [11, 0.5],
            [12, 0.5],
            [13, 0.6],
            [14, 0.6],
            [15, 0.8],
            [16, 0.8],
            [17, 1],
            [18, 1],
            [19, 1],
            [20, 1.2],
            [21, 1.2]
          ],
          "base": 1.2
        },
        "line-gap-width": {
          "stops": [
            [5, 0.7],
            [6, 0.7],
            [7, 0.7],
            [8, 0.8],
            [9, 0.8],
            [10, 1.2],
            [11, 2],
            [12, 3],
            [13, 4.2],
            [14, 4.8],
            [15, 7.2],
            [16, 10],
            [17, 24],
            [18, 24],
            [19, 24],
            [20, 24],
            [21, 24]
          ],
          "base": 1.2
        },
        "line-color": {
          "stops": [[10, "rgba(31, 42, 53, 1)"], [19, "rgba(31, 42, 53, 1)"]],
          "base": 1
        }
      }
    },
    {
      "id": "countyway@fs",
      "type": "line",
      "metadata": {"description": "县道"},
      "source": "fs_tiles",
      "source-layer": "countyway@fs",
      "minzoom": 10,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "line-cap": "butt",
        "line-join": "round"
      },
      "paint": {
        "line-translate-anchor": "viewport",
        "line-width": {
          "stops": [
            [5, 0.7],
            [6, 0.7],
            [7, 0.7],
            [8, 0.8],
            [9, 0.8],
            [10, 1.2],
            [11, 2],
            [12, 3],
            [13, 4.2],
            [14, 4.8],
            [15, 7.2],
            [16, 10],
            [17, 24],
            [18, 24],
            [19, 24],
            [20, 24],
            [21, 24]
          ],
          "base": 1.2
        },
        "line-color": "rgba(6, 13, 22, 1)"
      }
    },
    {
      "id": "fastroad@fs-border",
      "type": "line",
      "metadata": {"description": "城市快速通道"},
      "source": "fs_tiles",
      "source-layer": "fastroad@fs",
      "minzoom": 9,
      "maxzoom": 21,
      "layout": {"visibility": "visible", "line-cap": "butt"},
      "paint": {
        "line-translate-anchor": "viewport",
        "line-width": {
          "stops": [
            [5, 0],
            [6, 0],
            [7, 0.6],
            [8, 0.6],
            [9, 0.6],
            [10, 0.6],
            [11, 0.8],
            [12, 0.8],
            [13, 1],
            [14, 1],
            [15, 1.2],
            [16, 1.2],
            [17, 1.4],
            [18, 1.4],
            [19, 1.4]
          ],
          "base": 1.2
        },
        "line-gap-width": {
          "stops": [
            [5, 0.6],
            [6, 0.8],
            [7, 1.4],
            [8, 1.8],
            [9, 2],
            [10, 2.4],
            [11, 2.8],
            [12, 3.2],
            [13, 5.4],
            [14, 6.8],
            [15, 8],
            [16, 14],
            [17, 20],
            [18, 20],
            [19, 20]
          ],
          "base": 1.2
        },
        "line-color": {
          "stops": [[5, "rgba(31, 42, 53, 1)"], [19, "rgba(31, 42, 53, 1)"]],
          "base": 1
        }
      }
    },
    {
      "id": "fastroad@fs",
      "type": "line",
      "metadata": {"description": "城市快速通道"},
      "source": "fs_tiles",
      "source-layer": "fastroad@fs",
      "minzoom": 9,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "line-cap": "round",
        "line-join": "round"
      },
      "paint": {
        "line-translate-anchor": "viewport",
        "line-width": {
          "stops": [
            [5, 0.7],
            [6, 0.8],
            [7, 1.4],
            [8, 1.8],
            [9, 2],
            [10, 2.4],
            [11, 2.8],
            [12, 3.2],
            [13, 5.4],
            [14, 6.8],
            [15, 8],
            [16, 14],
            [17, 20],
            [18, 20],
            [19, 20]
          ],
          "base": 1.2
        },
        "line-color": "rgba(6, 13, 22, 1)"
      }
    },
    {
      "id": "highway@fs-border",
      "type": "line",
      "metadata": {"description": "高速公路"},
      "source": "fs_tiles",
      "source-layer": "highway@fs",
      "minzoom": 8,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "line-cap": "butt",
        "line-join": "round"
      },
      "paint": {
        "line-translate-anchor": "viewport",
        "line-width": {
          "stops": [
            [5, 0],
            [6, 0],
            [7, 0.6],
            [8, 0.6],
            [9, 0.6],
            [10, 0.6],
            [11, 0.8],
            [12, 0.8],
            [13, 1],
            [14, 1],
            [15, 1.2],
            [16, 1.2],
            [17, 1.4],
            [18, 1.4],
            [19, 1.4],
            [20, 1.6],
            [21, 1.6]
          ],
          "base": 1.2
        },
        "line-gap-width": {
          "stops": [
            [5, 0.6],
            [6, 0.8],
            [7, 1.4],
            [8, 1.8],
            [9, 2],
            [10, 2.4],
            [11, 2.8],
            [12, 3.2],
            [13, 5.4],
            [14, 6.8],
            [15, 8],
            [16, 14],
            [17, 24],
            [18, 24],
            [19, 24],
            [20, 24],
            [21, 24]
          ],
          "base": 1.2
        },
        "line-color": {
          "stops": [[5, "rgba(31, 42, 53, 1)"], [21, "rgba(31, 42, 53, 1)"]],
          "base": 1
        }
      }
    },
    {
      "id": "highway@fs",
      "type": "line",
      "metadata": {"description": "高速公路"},
      "source": "fs_tiles",
      "source-layer": "highway@fs",
      "minzoom": 8,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "line-cap": "butt",
        "line-join": "round"
      },
      "paint": {
        "line-translate-anchor": "viewport",
        "line-width": {
          "stops": [
            [5, 0.7],
            [6, 0.8],
            [7, 1.4],
            [8, 1.8],
            [9, 2],
            [10, 2.4],
            [11, 2.8],
            [12, 3.2],
            [13, 5.4],
            [14, 6.8],
            [15, 8],
            [16, 14],
            [17, 24],
            [18, 24],
            [19, 24],
            [20, 24],
            [21, 24]
          ],
          "base": 1.2
        },
        "line-color": "rgba(6, 13, 22, 1)"
      }
    },
    {
      "id": "railway@fs",
      "type": "line",
      "metadata": {"description": "铁路"},
      "source": "fs_tiles",
      "source-layer": "railway@fs",
      "minzoom": 9,
      "maxzoom": 21,
      "layout": {
        "visibility": "none",
        "line-cap": "square",
        "line-join": "miter"
      },
      "paint": {
        "line-width": {"stops": [[9, 2], [21, 9]]},
        "line-color": "rgba(221, 221, 221, 1)",
        "line-blur": 0,
        "line-opacity": 0.1
      }
    },
    {
      "id": "railway@fs-copy",
      "type": "line",
      "metadata": {"description": "铁路"},
      "source": "fs_tiles",
      "source-layer": "railway@fs",
      "minzoom": 9,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "line-cap": "square",
        "line-join": "miter"
      },
      "paint": {
        "line-width": {"stops": [[9, 2], [21, 8]]},
        "line-color": "rgba(6, 13, 22, 1)",
        "line-blur": 0,
        "line-dasharray": [3, 3]
      }
    },
    {
      "id": "provincialhighway@fs-border",
      "type": "line",
      "metadata": {"description": "省道"},
      "source": "fs_tiles",
      "source-layer": "provincialhighway@fs",
      "minzoom": 8,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "line-cap": "butt",
        "line-join": "round"
      },
      "paint": {
        "line-translate-anchor": "viewport",
        "line-width": {
          "stops": [
            [5, 0],
            [6, 0],
            [7, 0.4],
            [8, 0.4],
            [9, 0.4],
            [10, 0.4],
            [11, 0.6],
            [12, 0.6],
            [13, 0.8],
            [14, 0.8],
            [15, 1],
            [16, 1],
            [17, 1.2],
            [18, 1.2],
            [19, 1.2],
            [20, 1.4],
            [21, 1.4]
          ],
          "base": 1.2
        },
        "line-gap-width": {
          "stops": [
            [5, 0.6],
            [6, 0.8],
            [7, 1],
            [8, 1.2],
            [9, 1.8],
            [10, 2],
            [11, 2.4],
            [12, 2.8],
            [13, 3.6],
            [14, 5.4],
            [15, 7.2],
            [16, 10.8],
            [17, 20],
            [18, 20],
            [19, 20],
            [20, 20],
            [21, 20]
          ],
          "base": 1.2
        },
        "line-color": {
          "stops": [[8, "rgba(31, 42, 53, 1)"], [19, "rgba(31, 42, 53, 1)"]],
          "base": 1
        }
      }
    },
    {
      "id": "provincialhighway@fs",
      "type": "line",
      "metadata": {"description": "省道"},
      "source": "fs_tiles",
      "source-layer": "provincialhighway@fs",
      "minzoom": 8,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "line-cap": "butt",
        "line-join": "round"
      },
      "paint": {
        "line-translate-anchor": "viewport",
        "line-width": {
          "stops": [
            [5, 0.6],
            [6, 0.8],
            [7, 1],
            [8, 1.2],
            [9, 1.8],
            [10, 2],
            [11, 2.4],
            [12, 2.8],
            [13, 3.6],
            [14, 5.4],
            [15, 7.2],
            [16, 10.8],
            [17, 20],
            [18, 20],
            [19, 20],
            [20, 20],
            [21, 20]
          ],
          "base": 1.2
        },
        "line-color": "rgba(6, 13, 22, 1)"
      }
    },
    {
      "id": "nationalhighway@fs-border",
      "type": "line",
      "metadata": {"description": "国道"},
      "source": "fs_tiles",
      "source-layer": "nationalhighway@fs",
      "minzoom": 8,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "line-cap": "butt",
        "line-join": "round"
      },
      "paint": {
        "line-translate-anchor": "viewport",
        "line-width": {
          "stops": [
            [5, 0],
            [6, 0],
            [7, 0.4],
            [8, 0.4],
            [9, 0.4],
            [10, 0.4],
            [11, 0.6],
            [12, 0.6],
            [13, 0.8],
            [14, 0.8],
            [15, 1],
            [16, 1],
            [17, 1.2],
            [18, 1.2],
            [19, 1.2],
            [20, 1.4],
            [21, 1.4]
          ],
          "base": 1.2
        },
        "line-gap-width": {
          "stops": [
            [5, 0.6],
            [6, 0.8],
            [7, 1],
            [8, 1.2],
            [9, 1.8],
            [10, 2],
            [11, 2.4],
            [12, 2.8],
            [13, 3.6],
            [14, 5.4],
            [15, 7.2],
            [16, 10.8],
            [17, 20],
            [18, 20],
            [19, 20],
            [20, 20],
            [21, 20]
          ],
          "base": 1.2
        },
        "line-color": {
          "stops": [[6, "rgba(31, 42, 53, 1)"], [19, "rgba(31, 42, 53, 1)"]],
          "base": 1
        }
      }
    },
    {
      "id": "nationalhighway@fs",
      "type": "line",
      "metadata": {"description": "国道"},
      "source": "fs_tiles",
      "source-layer": "nationalhighway@fs",
      "minzoom": 8,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "line-cap": "round",
        "line-join": "round"
      },
      "paint": {
        "line-translate-anchor": "viewport",
        "line-width": {
          "stops": [
            [5, 0.6],
            [6, 0.8],
            [7, 1],
            [8, 1.2],
            [9, 1.8],
            [10, 2],
            [11, 2.4],
            [12, 2.8],
            [13, 3.6],
            [14, 5.4],
            [15, 7.2],
            [16, 10.8],
            [17, 20],
            [18, 20],
            [19, 20],
            [20, 20],
            [21, 20]
          ],
          "base": 1.2
        },
        "line-color": "rgba(6, 13, 22, 1)"
      }
    },
    {
      "id": "metro@fs-border",
      "type": "line",
      "metadata": {"description": "地铁"},
      "source": "fs_tiles",
      "source-layer": "metro@fs",
      "minzoom": 11,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "line-cap": "square",
        "line-join": "miter"
      },
      "paint": {
        "line-translate-anchor": "viewport",
        "line-width": {"stops": [[10, 3], [19, 7]], "base": 1.2},
        "line-gap-width": 0,
        "line-opacity": 1,
        "line-color": "rgba(31, 42, 53, 1)"
      }
    },
    {
      "id": "metro@fs",
      "type": "line",
      "metadata": {"description": "地铁"},
      "source": "fs_tiles",
      "source-layer": "metro@fs",
      "minzoom": 11,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "line-cap": "round",
        "line-join": "round"
      },
      "paint": {
        "line-translate-anchor": "viewport",
        "line-width": {"stops": [[10, 1.8], [19, 3.6]], "base": 1.2},
        "line-opacity": 0.15,
        "line-color": "rgba(9, 15, 25, 1)"
      }
    },
    {
      "id": "nationalhighway@fs-copy-copy",
      "type": "symbol",
      "metadata": {"description": "国道"},
      "source": "fs_tiles",
      "source-layer": "nationalhighway@fs",
      "minzoom": 8,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "symbol-placement": "line",
        "text-field": "{NAME}",
        "text-font": ["Microsoft YaHei UI Regular"],
        "text-size": {
          "stops": [
            [11, 12],
            [12, 12],
            [13, 12],
            [14, 13],
            [15, 13],
            [16, 14],
            [17, 14],
            [18, 14],
            [19, 14],
            [20, 16],
            [21, 16]
          ],
          "base": 1
        },
        "text-padding": 2
      },
      "paint": {
        "text-halo-color": "rgba(126, 134, 152, 1)",
        "text-translate-anchor": "viewport",
        "icon-color": "#ff0000",
        "text-color": "rgba(126, 134, 152, 1)",
        "text-halo-width": 0
      }
    },
    {
      "id": "csg_province_label@csg",
      "type": "symbol",
      "metadata": {"description": "南网五省标注"},
      "source": "world",
      "source-layer": "csg_province_label@csg",
      "minzoom": 4,
      "maxzoom": 6.01,
      "layout": {
        "visibility": "visible",
        "text-field": "{NAME}",
        "text-font": ["Microsoft YaHei UI Regular"],
        "text-max-width": 10,
        "text-anchor": "top",
        "text-size": 12
      },
      "paint": {
        "text-color": "rgba(126, 134, 152, 1)",
        "text-halo-color": "rgba(126, 134, 152, 1)",
        "text-halo-width": 0,
        "text-halo-blur": 0
      }
    },
    {
      "id": "countryway@fs-copy-copy",
      "type": "symbol",
      "metadata": {"description": "乡道"},
      "source": "fs_tiles",
      "source-layer": "countryway@fs",
      "minzoom": 14,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "symbol-placement": "line",
        "text-field": "{NAME}",
        "text-font": ["Microsoft YaHei UI Regular"],
        "text-size": {
          "stops": [
            [13, 11],
            [14, 11],
            [15, 12],
            [16, 13],
            [17, 13],
            [18, 13],
            [19, 13]
          ],
          "base": 1
        },
        "text-padding": 2,
        "text-pitch-alignment": "viewport",
        "text-rotation-alignment": "map"
      },
      "paint": {
        "text-halo-color": "rgba(126, 134, 152, 1)",
        "text-translate-anchor": "viewport",
        "icon-color": "#ff0000",
        "text-color": "rgba(126, 134, 152, 1)",
        "text-halo-width": 0
      }
    },
    {
      "id": "fastroad@fs-copy-copy",
      "type": "symbol",
      "metadata": {"description": "城市快速通道"},
      "source": "fs_tiles",
      "source-layer": "fastroad@fs",
      "minzoom": 9,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "symbol-placement": "line",
        "text-font": ["Microsoft YaHei UI Regular"],
        "text-field": "{NAME}",
        "text-size": {
          "stops": [
            [11, 12],
            [12, 13],
            [13, 13],
            [14, 14],
            [15, 14],
            [16, 15],
            [17, 15],
            [18, 15],
            [19, 15],
            [20, 16],
            [21, 16]
          ],
          "base": 1
        },
        "text-padding": 2
      },
      "paint": {
        "text-halo-color": "rgba(126, 134, 152, 1)",
        "text-translate-anchor": "viewport",
        "icon-color": "#ff0000",
        "text-color": "rgba(126, 134, 152, 1)",
        "text-halo-width": 0
      }
    },
    {
      "id": "provincialhighway@fs-copy-copy",
      "type": "symbol",
      "metadata": {"description": "省道"},
      "source": "fs_tiles",
      "source-layer": "provincialhighway@fs",
      "minzoom": 10,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "symbol-placement": "line",
        "text-field": "{NAME}",
        "text-font": ["Microsoft YaHei UI Regular"],
        "text-size": {
          "stops": [
            [12, 12],
            [13, 12],
            [14, 13],
            [15, 13],
            [16, 14],
            [17, 14],
            [18, 14],
            [19, 14],
            [20, 14],
            [21, 14]
          ],
          "base": 1
        },
        "text-padding": 2
      },
      "paint": {
        "text-halo-color": "rgba(126, 134, 152, 1)",
        "text-translate-anchor": "viewport",
        "icon-color": "#ff0000",
        "text-color": "rgba(126, 134, 152, 1)",
        "text-halo-width": 0
      }
    },
    {
      "id": "railway@fs-copy-copy",
      "type": "symbol",
      "metadata": {"description": "铁路"},
      "source": "fs_tiles",
      "source-layer": "railway@fs",
      "minzoom": 9,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "symbol-placement": "line",
        "text-field": "{NAME}",
        "text-font": ["Microsoft YaHei UI Regular"],
        "text-size": 12
      },
      "paint": {
        "text-color": "rgba(126, 134, 152, 1)",
        "text-halo-color": "rgba(126, 134, 152, 1)",
        "text-halo-width": 0,
        "text-halo-blur": 0
      }
    },
    {
      "id": "otherway@fs-copy",
      "type": "symbol",
      "metadata": {"description": "其他道路"},
      "source": "fs_tiles",
      "source-layer": "otherway@fs",
      "minzoom": 15,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "text-field": "{NAME}",
        "text-font": ["Microsoft YaHei UI Regular"],
        "text-size": {
          "stops": [
            [15, 10],
            [16, 11],
            [17, 11],
            [18, 12],
            [19, 12],
            [20, 14],
            [21, 14]
          ],
          "base": 1
        }
      },
      "paint": {
        "text-halo-color": "rgba(126, 134, 152, 1)",
        "text-translate-anchor": "viewport",
        "icon-color": "#ff0000",
        "text-color": "rgba(126, 134, 152, 1)",
        "text-halo-width": 0
      }
    },
    {
      "id": "countyway@fs-copy",
      "type": "symbol",
      "metadata": {"description": "县道"},
      "source": "fs_tiles",
      "source-layer": "countyway@fs",
      "minzoom": 13,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "text-field": "{NAME}",
        "text-font": ["Microsoft YaHei UI Regular"],
        "text-size": {
          "stops": [
            [12, 10],
            [13, 11],
            [14, 11],
            [15, 12],
            [16, 13],
            [17, 13],
            [18, 13],
            [19, 13]
          ],
          "base": 1
        },
        "text-padding": 2,
        "symbol-placement": "line",
        "text-pitch-alignment": "viewport"
      },
      "paint": {
        "text-halo-color": "rgba(126, 134, 152, 1)",
        "text-translate-anchor": "viewport",
        "icon-color": "#ff0000",
        "text-color": "rgba(126, 134, 152, 1)",
        "text-halo-width": 0
      }
    },
    {
      "id": "highway@fs-copy-copy",
      "type": "symbol",
      "metadata": {"description": "高速公路"},
      "source": "fs_tiles",
      "source-layer": "highway@fs",
      "minzoom": 10,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "text-field": "{NAME}",
        "text-font": ["Microsoft YaHei UI Regular"],
        "text-size": {
          "stops": [
            [11, 12],
            [12, 13],
            [13, 13],
            [14, 14],
            [15, 14],
            [16, 15],
            [17, 15],
            [18, 15],
            [19, 15],
            [20, 15],
            [21, 15]
          ],
          "base": 1
        },
        "text-padding": 2,
        "symbol-placement": "line",
        "text-pitch-alignment": "viewport",
        "text-rotation-alignment": "map"
      },
      "paint": {
        "text-halo-color": "rgba(126, 134, 152, 1)",
        "text-translate-anchor": "viewport",
        "icon-color": "#ff0000",
        "text-color": "rgba(126, 134, 152, 1)",
        "text-halo-width": 0
      }
    },
    {
      "id": "csg_city_label@csg",
      "type": "symbol",
      "metadata": {"description": "南网地市标注"},
      "source": "world",
      "source-layer": "csg_city_label@csg",
      "minzoom": 6,
      "maxzoom": 19.01,
      "layout": {
        "visibility": "visible",
        "text-field": "{NAME}",
        "text-font": ["Microsoft YaHei UI Regular"],
        "text-max-width": 10,
        "text-anchor": "top",
        "text-size": 12
      },
      "paint": {
        "text-color": "rgba(126, 134, 152, 1)",
        "text-halo-color": "rgba(126, 134, 152, 1)",
        "text-halo-width": 0,
        "text-halo-blur": 0
      }
    },
    {
      "id": "china_province_label2@csg",
      "type": "symbol",
      "metadata": {"description": "中国省份标注"},
      "source": "world",
      "source-layer": "china_province_label@csg",
      "minzoom": 4,
      "maxzoom": 9.01,
      "filter": ["==", "yxcode", "20"],
      "layout": {
        "visibility": "visible",
        "text-field": "{NAME}",
        "text-font": ["Microsoft YaHei UI Regular"],
        "text-max-width": 10,
        "text-anchor": "top",
        "text-size": 12
      },
      "paint": {
        "text-color": "rgba(126, 134, 152, 1)",
        "text-halo-color": "rgba(126, 134, 152, 1)",
        "text-halo-width": 0,
        "text-halo-blur": 0
      }
    },
    {
      "id": "worldlabel@csg",
      "type": "symbol",
      "metadata": {"description": "国家标注"},
      "source": "world",
      "source-layer": "china_province_label@csg",
      "minzoom": 3,
      "maxzoom": 9.01,
      "filter": ["!in", "code", 1, 3, 2],
      "layout": {
        "visibility": "visible",
        "text-field": "{NAME}",
        "text-font": ["Microsoft YaHei UI Regular"],
        "text-max-width": 10,
        "text-anchor": "top",
        "text-size": 12
      },
      "paint": {
        "text-color": "rgba(126, 134, 152, 1)",
        "text-halo-color": "rgba(126, 134, 152, 1)",
        "text-halo-width": 0,
        "text-halo-blur": 0
      }
    },
    {
      "id": "railwaystation@fs",
      "type": "symbol",
      "metadata": {"description": "火车站"},
      "source": "fs_tiles",
      "source-layer": "railwaystation@fs",
      "minzoom": 13,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "text-field": "{NAME}",
        "text-font": ["Microsoft YaHei UI Regular"],
        "text-max-width": 10,
        "text-anchor": "left",
        "text-size": 11,
        "icon-size": 0.89,
        "icon-image": "poi_code_230100_16",
        "icon-offset": [-10, 0]
      },
      "paint": {
        "text-halo-color": "rgba(126, 134, 152, 1)",
        "text-translate-anchor": "viewport",
        "icon-color": "#ff0000",
        "icon-translate-anchor": "viewport",
        "text-color": "rgba(126, 134, 152, 1)",
        "text-halo-width": 0,
        "text-translate": [0, -2]
      }
    },
    {
      "id": "finance@fs",
      "type": "symbol",
      "metadata": {"description": "金融机构"},
      "source": "fs_tiles",
      "source-layer": "finance@fs",
      "minzoom": 16,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "text-field": "{NAME}",
        "text-font": ["Microsoft YaHei UI Regular"],
        "text-max-width": 10,
        "text-anchor": "left",
        "text-size": 11,
        "icon-size": 0.89,
        "icon-image": "poi_code_150102_16",
        "icon-offset": [-10, 0]
      },
      "paint": {
        "text-halo-color": "rgba(126, 134, 152, 1)",
        "text-translate-anchor": "viewport",
        "icon-color": "#ff0000",
        "icon-translate-anchor": "viewport",
        "text-color": "rgba(126, 134, 152, 1)",
        "text-halo-width": 0,
        "text-translate": [0, -2]
      }
    },
    {
      "id": "village@fs",
      "type": "symbol",
      "metadata": {"description": "村名"},
      "source": "fs_tiles",
      "source-layer": "village@fs",
      "minzoom": 15,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "text-field": "{NAME}",
        "text-font": ["Microsoft YaHei UI Regular"],
        "text-max-width": 10,
        "text-anchor": "top",
        "text-size": {
          "stops": [
            [11, 9],
            [12, 10],
            [13, 10],
            [14, 11],
            [15, 11],
            [16, 11],
            [17, 11],
            [18, 11],
            [19, 11]
          ],
          "base": 1
        },
        "text-padding": 10
      },
      "paint": {
        "text-halo-color": "rgba(126, 134, 152, 1)",
        "icon-color": "#ff0000",
        "text-color": "rgba(126, 134, 152, 1)",
        "text-halo-width": 0
      }
    },
    {
      "id": "tour@fs",
      "type": "symbol",
      "metadata": {"description": "景点"},
      "source": "fs_tiles",
      "source-layer": "tour@fs",
      "minzoom": 13,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "text-field": "{NAME}",
        "text-font": ["Microsoft YaHei UI Regular"],
        "text-max-width": 10,
        "text-anchor": "left",
        "text-size": 11,
        "icon-size": 0.89,
        "icon-image": "poi_code_180304_16",
        "icon-offset": [-12, 0]
      },
      "paint": {
        "text-halo-color": "rgba(126, 134, 152, 1)",
        "text-translate-anchor": "viewport",
        "icon-color": "#ff0000",
        "icon-translate-anchor": "viewport",
        "text-color": "rgba(126, 134, 152, 1)",
        "text-halo-width": 0,
        "text-translate": [0, 0]
      }
    },
    {
      "id": "education@fs",
      "type": "symbol",
      "metadata": {"description": "教育机构"},
      "source": "fs_tiles",
      "source-layer": "education@fs",
      "minzoom": 16,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "text-field": "{NAME}",
        "text-font": ["Microsoft YaHei UI Regular"],
        "text-offset": [0.8, 0],
        "text-max-width": 8,
        "text-anchor": "left",
        "text-rotation-alignment": "viewport",
        "text-size": 11,
        "icon-size": 0.89,
        "icon-image": "poi_code_160102_16"
      },
      "paint": {
        "text-halo-color": "rgba(126, 134, 152, 1)",
        "text-translate-anchor": "viewport",
        "icon-color": "#ff0000",
        "icon-translate-anchor": "viewport",
        "text-color": "rgba(126, 134, 152, 1)",
        "text-halo-width": 0,
        "text-translate": [0, -2]
      }
    },
    {
      "id": "hotal@fs",
      "type": "symbol",
      "metadata": {"description": "住宿"},
      "source": "fs_tiles",
      "source-layer": "hotal@fs",
      "minzoom": 17,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "text-field": "{NAME}",
        "text-font": ["Microsoft YaHei UI Regular"],
        "text-max-width": 10,
        "text-anchor": "left",
        "text-size": 11,
        "icon-size": 0.89,
        "icon-image": "poi_code_120102_16",
        "icon-offset": [-12, 0]
      },
      "paint": {
        "text-halo-color": "rgba(126, 134, 152, 1)",
        "text-translate-anchor": "viewport",
        "icon-color": "#ff0000",
        "icon-translate-anchor": "viewport",
        "text-color": "rgba(126, 134, 152, 1)",
        "text-halo-width": 0,
        "text-translate": [0, -2]
      }
    },
    {
      "id": "medical@fs",
      "type": "symbol",
      "metadata": {"description": "医疗"},
      "source": "fs_tiles",
      "source-layer": "medical@fs",
      "minzoom": 15,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "text-field": "{NAME}",
        "text-font": ["Microsoft YaHei UI Regular"],
        "text-max-width": 10,
        "text-anchor": "left",
        "text-size": 11,
        "icon-size": 0.89,
        "icon-image": "poi_code_170100_16",
        "icon-padding": 0,
        "icon-offset": [-10, 0]
      },
      "paint": {
        "text-halo-color": "rgba(126, 134, 152, 1)",
        "text-translate-anchor": "viewport",
        "icon-color": "#ff0000",
        "icon-translate-anchor": "viewport",
        "text-color": "rgba(126, 134, 152, 1)",
        "text-halo-width": 0,
        "text-translate": [0, -2]
      }
    },
    {
      "id": "country@fs",
      "type": "symbol",
      "metadata": {"description": "乡"},
      "source": "fs_tiles",
      "source-layer": "country@fs",
      "minzoom": 11,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "text-field": "{NAME}",
        "text-font": ["Microsoft YaHei UI Regular"],
        "text-max-width": 10,
        "text-anchor": "top",
        "text-size": {
          "stops": [[9, 10], [10, 10], [11, 10], [12, 11], [13, 12]],
          "base": 1
        },
        "text-padding": 10
      },
      "paint": {
        "text-halo-color": "rgba(126, 134, 152, 1)",
        "text-translate-anchor": "viewport",
        "icon-color": "#ff0000",
        "text-color": "rgba(126, 134, 152, 1)",
        "text-halo-width": 0,
        "text-opacity": 1
      }
    },
    {
      "id": "county@fs",
      "type": "symbol",
      "metadata": {"description": "县标注"},
      "source": "fs_tiles",
      "source-layer": "county@fs",
      "minzoom": 8,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "text-field": "{NAME}",
        "text-font": ["Microsoft YaHei UI Regular"],
        "text-max-width": 10,
        "text-anchor": "top",
        "text-size": {
          "stops": [[9, 13], [10, 14], [11, 14], [12, 15]],
          "base": 1
        }
      },
      "paint": {
        "text-halo-color": "rgba(126, 134, 152, 1)",
        "text-translate-anchor": "viewport",
        "icon-color": "#ff0000",
        "text-color": "rgba(126, 134, 152, 1)",
        "text-halo-width": 0,
        "text-translate": [0, 0]
      }
    },
    {
      "id": "park2@fs",
      "type": "symbol",
      "metadata": {"description": "公园"},
      "source": "fs_tiles",
      "source-layer": "park@fs",
      "minzoom": 15,
      "maxzoom": 21,
      "filter": ["==", "DLEVEL", "0"],
      "layout": {
        "visibility": "visible",
        "text-field": "{NAME}",
        "text-font": ["Microsoft YaHei UI Regular"],
        "text-max-width": 10,
        "text-anchor": "top",
        "text-size": 11
      },
      "paint": {
        "text-halo-color": "rgba(126, 134, 152, 1)",
        "icon-color": "#ff0000",
        "text-color": "rgba(126, 134, 152, 1)",
        "text-halo-width": 0
      }
    },
    {
      "id": "park@fs",
      "type": "symbol",
      "metadata": {"description": "公园"},
      "source": "fs_tiles",
      "source-layer": "park@fs",
      "minzoom": 15,
      "maxzoom": 21,
      "filter": [">", "DLEVEL", "0"],
      "layout": {
        "visibility": "visible",
        "text-field": "{NAME}",
        "text-font": ["Microsoft YaHei UI Regular"],
        "text-max-width": 10,
        "text-anchor": "top",
        "text-size": 11
      },
      "paint": {
        "text-halo-color": "rgba(126, 134, 152, 1)",
        "icon-color": "#ff0000",
        "text-color": "rgba(126, 134, 152, 1)",
        "text-halo-width": 0
      }
    },
    {
      "id": "government@fs",
      "type": "symbol",
      "metadata": {"description": "政府机构"},
      "source": "fs_tiles",
      "source-layer": "government@fs",
      "minzoom": 16,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "text-field": "{NAME}",
        "text-font": ["Microsoft YaHei UI Regular"],
        "text-max-width": 10,
        "text-anchor": "left",
        "text-size": 11,
        "icon-size": 0.89,
        "icon-image": "poi_code_190100_16",
        "icon-offset": [-10, 0]
      },
      "paint": {
        "text-halo-color": "rgba(126, 134, 152, 1)",
        "text-translate-anchor": "viewport",
        "icon-color": "#ff0000",
        "icon-translate-anchor": "viewport",
        "text-color": "rgba(126, 134, 152, 1)",
        "text-halo-width": 0,
        "text-translate": [0, -2]
      }
    }
  ],
  "id": "5usgfbh2k"
}

var basemapstyle ={
  "version": 8,
  "name": "world",
  "metadata": {},
  "sources": {
    "world": {
      "tiles": [
        host+"/demotiles/csg/{z}/{x}/{y}.mvt"
      ],
      "type": "vector"
    },
    "csgbound": {
      "type": "geojson",
      "data": host+"/demotiles/csg.json"
    },
    "fs_tiles": {
      "tiles": [
        host+"/demotiles/foshan/{z}/{x}/{y}.mvt"
      ],
      "type": "vector",
      "bounds": [
        97.52872078822246,
        18.157639005000004,
        117.19213091999995,
        29.222985015000017
      ]
    }
  },
  "sprite":  host+"/assets/sprites/mergesprite",
  "glyphs":  host+"/assets/{fontstack}/{range}.pbf",
  "layers": [
    {
      "id": "background",
      "type": "background",
      "paint": {
        "background-color": "rgba(190, 217, 255, 1)"
      }
    },
    {
      "id": "world@csg",
      "type": "fill",
      "metadata": {
        "description": "世界地图填充面"
      },
      "source": "world",
      "source-layer": "world@csg",
      "minzoom": 0,
      "maxzoom": 19.01,
      "layout": {
        "visibility": "visible"
      },
      "paint": {
        "fill-color": "rgba(247, 248, 249, 1)",
        "fill-antialias": true
      }
    },
    {
      "id": "nine_line@csg",
      "type": "line",
      "metadata": {
        "description": "九段线"
      },
      "source": "world",
      "source-layer": "nine_line@csg",
      "minzoom": 0,
      "maxzoom": 9.01,
      "layout": {
        "visibility": "visible"
      },
      "paint": {
        "line-width": 1,
        "line-color": "rgba(255, 255, 255, 1)"
      }
    },
    {
      "id": "china_province@csg",
      "type": "fill",
      "metadata": {
        "description": "中国省份"
      },
      "source": "world",
      "source-layer": "china_province@csg",
      "minzoom": 3,
      "maxzoom": 9.01,
      "layout": {
        "visibility": "visible"
      },
      "paint": {
        "fill-color": "rgba(247, 248, 249, 1)",
        "fill-antialias": true
      }
    },
    {
      "id": "china_province@csg_outline",
      "type": "line",
      "metadata": {
        "description": "中国省份轮廓"
      },
      "source": "world",
      "source-layer": "china_province@csg",
      "minzoom": 3,
      "maxzoom": 9.01,
      "layout": {
        "visibility": "visible"
      },
      "paint": {
        "line-width": 0.4,
        "line-color": "rgba(153, 153, 153, 1)"
      }
    },
    {
      "id": "csg_province@csg",
      "type": "fill",
      "metadata": {
        "description": "南网五省"
      },
      "source": "world",
      "source-layer": "csg_province@csg",
      "minzoom": 3,
      "maxzoom": 9.01,
      "layout": {
        "visibility": "visible"
      },
      "paint": {
        "fill-color": "rgba(245, 245, 245, 1)",
        "fill-antialias": true
      }
    },
    {
      "id": "csg_city@csg",
      "type": "fill",
      "metadata": {
        "description": "南网地市"
      },
      "source": "world",
      "source-layer": "csg_city@csg",
      "minzoom": 5,
      "maxzoom": 9.01,
      "layout": {
        "visibility": "none"
      },
      "paint": {
        "fill-color": "rgba(245, 245, 245, 1)",
        "fill-antialias": true
      }
    },
    {
      "id": "csg_city@csg_outline",
      "type": "line",
      "metadata": {
        "description": "南网地市轮廓"
      },
      "source": "world",
      "source-layer": "csg_city@csg",
      "minzoom": 5,
      "maxzoom": 9.01,
      "layout": {
        "visibility": "visible"
      },
      "paint": {
        "line-width": 0.38,
        "line-color": "rgba(153, 153, 153, 1)"
      }
    },
    {
      "id": "csg_province_label@csg",
      "type": "symbol",
      "metadata": {
        "description": "南网五省标注"
      },
      "source": "world",
      "source-layer": "csg_province_label@csg",
      "minzoom": 4,
      "maxzoom": 6.01,
      "layout": {
        "visibility": "visible",
        "text-field": "{NAME}",
        "text-font": [
          "Microsoft YaHei UI Regular"
        ],
        "text-max-width": 10,
        "text-anchor": "top",
        "text-size": 12
      },
      "paint": {
        "text-color": "rgba(51, 51, 51, 1)",
        "text-halo-color": "rgba(255, 255, 255, 1)",
        "text-halo-width": 2,
        "text-halo-blur": 2
      }
    },
    {
      "id": "csg_background",
      "type": "fill",
      "source": "csgbound",
      "minzoom": 8,
      "maxzoom": 19.01,
      "layout": {
        "visibility": "visible"
      },
      "paint": {
        "fill-color": "rgba(245, 245, 245, 1)",
        "fill-antialias": true
      }
    },
    {
      "id": "world@csg_outline",
      "type": "line",
      "metadata": {
        "description": "世界地图轮廓线"
      },
      "source": "world",
      "source-layer": "world@csg",
      "minzoom": 0,
      "maxzoom": 9.01,
      "layout": {
        "visibility": "visible"
      },
      "paint": {
        "line-width": 0.38,
        "line-color": "rgba(209, 209, 209, 1)"
      }
    },
    {
      "id": "iland@fs",
      "type": "fill",
      "metadata": {
        "description": "佛山岛屿"
      },
      "source": "fs_tiles",
      "source-layer": "iland@fs",
      "minzoom": 8,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible"
      },
      "paint": {
        "fill-color": "rgba(245, 245, 245, 1)",
        "fill-antialias": true
      }
    },
    {
      "id": "greenland@fs",
      "type": "fill",
      "metadata": {
        "description": "佛山绿地"
      },
      "source": "fs_tiles",
      "source-layer": "greenland@fs",
      "minzoom": 8,
      "maxzoom": 21,
      "filter": [
        ">=",
        "DISPCLASS",
        "4"
      ],
      "paint": {
        "fill-color": "rgba(220, 255, 214, 1)",
        "fill-antialias": true
      }
    },
    {
      "id": "greenland2",
      "type": "fill",
      "metadata": {
        "description": "佛山绿地"
      },
      "source": "fs_tiles",
      "source-layer": "greenland@fs",
      "minzoom": 12,
      "maxzoom": 21,
      "filter": [
        "<",
        "DISPCLASS",
        "4"
      ],
      "layout": {
        "visibility": "visible"
      },
      "paint": {
        "fill-color": "rgba(220, 255, 214, 1)",
        "fill-antialias": true
      }
    },
    {
      "id": "function_User@fs",
      "type": "fill",
      "metadata": {
        "description": "功能区"
      },
      "source": "fs_tiles",
      "source-layer": "function_User@fs",
      "minzoom": 13,
      "maxzoom": 21,
      "paint": {
        "fill-color": "rgba(220, 255, 214, 1)",
        "fill-antialias": true
      }
    },
    {
      "id": "water@fs",
      "type": "fill",
      "metadata": {
        "description": "水系"
      },
      "source": "fs_tiles",
      "source-layer": "water@fs",
      "minzoom": 13,
      "maxzoom": 21,
      "filter": [
        "<",
        "DISPCLASS",
        "4"
      ],
      "layout": {
        "visibility": "visible"
      },
      "paint": {
        "fill-color": "rgba(190, 217, 255, 1)",
        "fill-antialias": true
      }
    },
    {
      "id": "water2",
      "type": "fill",
      "metadata": {
        "description": "水系"
      },
      "source": "fs_tiles",
      "source-layer": "water@fs",
      "minzoom": 8,
      "maxzoom": 21,
      "filter": [
        ">=",
        "DISPCLASS",
        "4"
      ],
      "layout": {
        "visibility": "visible"
      },
      "paint": {
        "fill-color": "rgba(190, 217, 255, 1)",
        "fill-antialias": true
      }
    },
    {
      "id": "build@fs",
      "type": "fill-extrusion",
      "metadata": {
        "description": "建筑物"
      },
      "source": "fs_tiles",
      "source-layer": "build@fs",
      "minzoom": 14,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible"
      },
        'paint':
            {
                'fill-extrusion-color': '#c8c8c8',
                'fill-extrusion-height': ["*", ["to-number",["get", "floor"]], 3],
                'fill-extrusion-base': 2,
                'fill-extrusion-opacity': .6
            }
    },
    {
      "id": "csg_outline@csg",
      "type": "line",
      "metadata": {
        "layer:caption": "csg_outline@csg"
      },
      "source": "world",
      "source-layer": "csg_province@csg",
      "minzoom": 3,
      "maxzoom": 19.01,
      "layout": {
        "visibility": "visible"
      },
      "paint": {
        "line-width": 1,
        "line-color": "rgba(255, 158, 5, 1)"
      }
    },
    {
      "id": "sidewalk@fs-border",
      "type": "line",
      "metadata": {
        "description": "行人道"
      },
      "source": "fs_tiles",
      "source-layer": "sidewalk@fs",
      "minzoom": 15,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "line-cap": "round",
        "line-join": "round"
      },
      "paint": {
        "line-translate-anchor": "viewport",
        "line-width": {
          "stops": [
            [
              9,
              0
            ],
            [
              10,
              0
            ],
            [
              11,
              0
            ],
            [
              12,
              0
            ],
            [
              13,
              0
            ],
            [
              14,
              0.6
            ],
            [
              15,
              0.8
            ],
            [
              16,
              0.8
            ],
            [
              17,
              1
            ],
            [
              18,
              1
            ],
            [
              19,
              1
            ],
            [
              20,
              1.2
            ],
            [
              21,
              1.2
            ]
          ],
          "base": 1.2
        },
        "line-gap-width": {
          "stops": [
            [
              9,
              0
            ],
            [
              10,
              0
            ],
            [
              11,
              0
            ],
            [
              12,
              0
            ],
            [
              13,
              0
            ],
            [
              14,
              1.2
            ],
            [
              15,
              2.4
            ],
            [
              16,
              3.2
            ],
            [
              17,
              6
            ],
            [
              18,
              8
            ],
            [
              19,
              10
            ],
            [
              20,
              10
            ],
            [
              21,
              10
            ]
          ],
          "base": 1.2
        },
        "line-color": "#d0d4d8"
      }
    },
    {
      "id": "sidewalk@fs",
      "type": "line",
      "metadata": {
        "description": "行人道"
      },
      "source": "fs_tiles",
      "source-layer": "sidewalk@fs",
      "minzoom": 15,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "line-cap": "round",
        "line-join": "round"
      },
      "paint": {
        "line-translate-anchor": "viewport",
        "line-width": {
          "stops": [
            [
              9,
              0
            ],
            [
              10,
              0
            ],
            [
              11,
              0
            ],
            [
              12,
              0
            ],
            [
              13,
              1
            ],
            [
              14,
              1.2
            ],
            [
              15,
              2.4
            ],
            [
              16,
              3.2
            ],
            [
              17,
              6
            ],
            [
              18,
              8
            ],
            [
              19,
              10
            ],
            [
              20,
              10
            ],
            [
              21,
              10
            ]
          ],
          "base": 1.2
        },
        "line-color": "#ffffff"
      }
    },
    {
      "id": "nineway@fs-border",
      "type": "line",
      "metadata": {
        "description": "九级公路"
      },
      "source": "fs_tiles",
      "source-layer": "nineway@fs",
      "minzoom": 15,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "line-cap": "round",
        "line-join": "round"
      },
      "paint": {
        "line-translate-anchor": "viewport",
        "line-width": {
          "stops": [
            [
              9,
              0
            ],
            [
              10,
              0
            ],
            [
              11,
              0
            ],
            [
              12,
              0
            ],
            [
              13,
              0
            ],
            [
              14,
              0.4
            ],
            [
              15,
              0.6
            ],
            [
              16,
              0.8
            ],
            [
              17,
              1
            ],
            [
              18,
              1
            ],
            [
              19,
              1
            ]
          ],
          "base": 1.2
        },
        "line-gap-width": {
          "stops": [
            [
              9,
              0.8
            ],
            [
              10,
              1
            ],
            [
              11,
              1
            ],
            [
              12,
              1
            ],
            [
              13,
              1
            ],
            [
              14,
              2
            ],
            [
              15,
              4
            ],
            [
              16,
              6
            ],
            [
              17,
              10
            ],
            [
              18,
              10
            ],
            [
              19,
              10
            ],
            [
              20,
              10
            ],
            [
              21,
              12
            ]
          ],
          "base": 1.2
        },
        "line-color": "#d0d4d8"
      }
    },
    {
      "id": "nineway@fs",
      "type": "line",
      "metadata": {
        "description": "九级公路"
      },
      "source": "fs_tiles",
      "source-layer": "nineway@fs",
      "minzoom": 15,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "line-cap": "round",
        "line-join": "round"
      },
      "paint": {
        "line-translate-anchor": "viewport",
        "line-width": {
          "stops": [
            [
              9,
              0.8
            ],
            [
              10,
              1
            ],
            [
              11,
              1
            ],
            [
              12,
              1
            ],
            [
              13,
              1
            ],
            [
              14,
              2
            ],
            [
              15,
              4
            ],
            [
              16,
              6
            ],
            [
              17,
              10
            ],
            [
              18,
              10
            ],
            [
              19,
              10
            ],
            [
              20,
              10
            ],
            [
              21,
              12
            ]
          ],
          "base": 1.2
        },
        "line-color": "#ffffff"
      }
    },
    {
      "id": "otherway@fs-border",
      "type": "line",
      "metadata": {
        "description": "其他道路"
      },
      "source": "fs_tiles",
      "source-layer": "otherway@fs",
      "minzoom": 15,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "line-cap": "butt",
        "line-join": "round"
      },
      "paint": {
        "line-translate-anchor": "viewport",
        "line-width": {
          "stops": [
            [
              9,
              0
            ],
            [
              10,
              0
            ],
            [
              11,
              0
            ],
            [
              12,
              0
            ],
            [
              13,
              0
            ],
            [
              14,
              0.6
            ],
            [
              15,
              0.8
            ],
            [
              16,
              0.8
            ],
            [
              17,
              1
            ],
            [
              18,
              1
            ],
            [
              19,
              1
            ],
            [
              20,
              1.2
            ],
            [
              21,
              1.2
            ]
          ],
          "base": 1.2
        },
        "line-gap-width": {
          "stops": [
            [
              9,
              0.8
            ],
            [
              10,
              1
            ],
            [
              11,
              1
            ],
            [
              12,
              1
            ],
            [
              13,
              1.2
            ],
            [
              14,
              1.4
            ],
            [
              15,
              3
            ],
            [
              16,
              6.4
            ],
            [
              17,
              10
            ],
            [
              18,
              10
            ],
            [
              19,
              10
            ],
            [
              20,
              11
            ],
            [
              21,
              11
            ]
          ],
          "base": 1.2
        },
        "line-color": "#d0d4d8"
      }
    },
    {
      "id": "otherway@fs",
      "type": "line",
      "metadata": {
        "description": "其他道路"
      },
      "source": "fs_tiles",
      "source-layer": "otherway@fs",
      "minzoom": 15,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "line-cap": "round",
        "line-join": "round"
      },
      "paint": {
        "line-translate-anchor": "viewport",
        "line-width": {
          "stops": [
            [
              9,
              0.8
            ],
            [
              10,
              1
            ],
            [
              11,
              1
            ],
            [
              12,
              1
            ],
            [
              13,
              1.2
            ],
            [
              14,
              1.4
            ],
            [
              15,
              3
            ],
            [
              16,
              6.4
            ],
            [
              17,
              10
            ],
            [
              18,
              10
            ],
            [
              19,
              10
            ],
            [
              20,
              10
            ],
            [
              21,
              10
            ]
          ],
          "base": 1.2
        },
        "line-color": "#ffffff"
      }
    },
    {
      "id": "otherway@fs-copy",
      "type": "symbol",
      "metadata": {
        "description": "其他道路"
      },
      "source": "fs_tiles",
      "source-layer": "otherway@fs",
      "minzoom": 15,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "text-field": "{NAME}",
        "text-font": [
          "Microsoft YaHei UI Regular"
        ],
        "text-size": {
          "stops": [
            [
              15,
              10
            ],
            [
              16,
              11
            ],
            [
              17,
              11
            ],
            [
              18,
              12
            ],
            [
              19,
              12
            ],
            [
              20,
              14
            ],
            [
              21,
              14
            ]
          ],
          "base": 1
        }
      },
      "paint": {
        "text-halo-color": "#ffffff",
        "text-translate-anchor": "viewport",
        "icon-color": "#ff0000",
        "text-color": "#acb6c5",
        "text-halo-width": 1
      }
    },
    {
      "id": "countryway@fs-border",
      "type": "line",
      "metadata": {
        "description": "乡道"
      },
      "source": "fs_tiles",
      "source-layer": "countryway@fs",
      "minzoom": 11,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "line-cap": "butt",
        "line-join": "round"
      },
      "paint": {
        "line-translate-anchor": "viewport",
        "line-width": {
          "stops": [
            [
              5,
              0
            ],
            [
              6,
              0
            ],
            [
              7,
              0
            ],
            [
              8,
              0
            ],
            [
              9,
              0
            ],
            [
              10,
              0.3
            ],
            [
              11,
              0.5
            ],
            [
              12,
              0.5
            ],
            [
              13,
              0.6
            ],
            [
              14,
              0.6
            ],
            [
              15,
              0.8
            ],
            [
              16,
              0.8
            ],
            [
              17,
              1
            ],
            [
              18,
              1
            ],
            [
              19,
              1
            ],
            [
              20,
              1.2
            ],
            [
              21,
              1.2
            ]
          ],
          "base": 1.2
        },
        "line-gap-width": {
          "stops": [
            [
              5,
              0.7
            ],
            [
              6,
              0.7
            ],
            [
              7,
              0.7
            ],
            [
              8,
              0.8
            ],
            [
              9,
              0.8
            ],
            [
              10,
              1.2
            ],
            [
              11,
              1.6
            ],
            [
              12,
              1.6
            ],
            [
              13,
              2
            ],
            [
              14,
              2.8
            ],
            [
              15,
              7.2
            ],
            [
              16,
              10
            ],
            [
              17,
              16
            ],
            [
              18,
              16
            ],
            [
              19,
              16
            ],
            [
              20,
              18
            ],
            [
              21,
              18
            ]
          ],
          "base": 1.2
        },
        "line-color": {
          "stops": [
            [
              11,
              "#d0d4d8"
            ],
            [
              19,
              "#d0d4d8"
            ]
          ],
          "base": 1
        }
      }
    },
    {
      "id": "countryway@fs",
      "type": "line",
      "metadata": {
        "description": "乡道"
      },
      "source": "fs_tiles",
      "source-layer": "countryway@fs",
      "minzoom": 11,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "line-cap": "round",
        "line-join": "round"
      },
      "paint": {
        "line-translate-anchor": "viewport",
        "line-width": {
          "stops": [
            [
              5,
              0.7
            ],
            [
              6,
              0.7
            ],
            [
              7,
              0.7
            ],
            [
              8,
              0.8
            ],
            [
              9,
              0.8
            ],
            [
              10,
              1.2
            ],
            [
              11,
              1.6
            ],
            [
              12,
              1.8
            ],
            [
              13,
              2
            ],
            [
              14,
              2.8
            ],
            [
              15,
              7.2
            ],
            [
              16,
              10
            ],
            [
              17,
              16
            ],
            [
              18,
              16
            ],
            [
              19,
              16
            ],
            [
              20,
              18
            ],
            [
              21,
              18
            ]
          ],
          "base": 1.2
        },
        "line-color": "#ffffff"
      }
    },
    {
      "id": "countryway@fs-copy-copy",
      "type": "symbol",
      "metadata": {
        "description": "乡道"
      },
      "source": "fs_tiles",
      "source-layer": "countryway@fs",
      "minzoom": 14,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "symbol-placement": "line",
        "text-field": "{NAME}",
        "text-font": [
          "Microsoft YaHei UI Regular"
        ],
        "text-size": {
          "stops": [
            [
              13,
              11
            ],
            [
              14,
              11
            ],
            [
              15,
              12
            ],
            [
              16,
              13
            ],
            [
              17,
              13
            ],
            [
              18,
              13
            ],
            [
              19,
              13
            ]
          ],
          "base": 1
        },
        "text-padding": 2,
        "text-pitch-alignment": "viewport",
        "text-rotation-alignment": "map"
      },
      "paint": {
        "text-halo-color": "#ffffff",
        "text-translate-anchor": "viewport",
        "icon-color": "#ff0000",
        "text-color": "#8594ab",
        "text-halo-width": 1
      }
    },
    {
      "id": "countyway@fs-border",
      "type": "line",
      "metadata": {
        "description": "县道"
      },
      "source": "fs_tiles",
      "source-layer": "countyway@fs",
      "minzoom": 10,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "line-cap": "butt",
        "line-join": "round"
      },
      "paint": {
        "line-translate-anchor": "viewport",
        "line-width": {
          "stops": [
            [
              5,
              0
            ],
            [
              6,
              0
            ],
            [
              7,
              0
            ],
            [
              8,
              0
            ],
            [
              9,
              0
            ],
            [
              10,
              0.4
            ],
            [
              11,
              0.5
            ],
            [
              12,
              0.5
            ],
            [
              13,
              0.6
            ],
            [
              14,
              0.6
            ],
            [
              15,
              0.8
            ],
            [
              16,
              0.8
            ],
            [
              17,
              1
            ],
            [
              18,
              1
            ],
            [
              19,
              1
            ],
            [
              20,
              1.2
            ],
            [
              21,
              1.2
            ]
          ],
          "base": 1.2
        },
        "line-gap-width": {
          "stops": [
            [
              5,
              0.7
            ],
            [
              6,
              0.7
            ],
            [
              7,
              0.7
            ],
            [
              8,
              0.8
            ],
            [
              9,
              0.8
            ],
            [
              10,
              1.2
            ],
            [
              11,
              2
            ],
            [
              12,
              3
            ],
            [
              13,
              4.2
            ],
            [
              14,
              4.8
            ],
            [
              15,
              7.2
            ],
            [
              16,
              10
            ],
            [
              17,
              24
            ],
            [
              18,
              24
            ],
            [
              19,
              24
            ],
            [
              20,
              24
            ],
            [
              21,
              24
            ]
          ],
          "base": 1.2
        },
        "line-color": {
          "stops": [
            [
              10,
              "#d8dcdf"
            ],
            [
              19,
              "#d0d4d8"
            ]
          ],
          "base": 1
        }
      }
    },
    {
      "id": "countyway@fs",
      "type": "line",
      "metadata": {
        "description": "县道"
      },
      "source": "fs_tiles",
      "source-layer": "countyway@fs",
      "minzoom": 10,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "line-cap": "butt",
        "line-join": "round"
      },
      "paint": {
        "line-translate-anchor": "viewport",
        "line-width": {
          "stops": [
            [
              5,
              0.7
            ],
            [
              6,
              0.7
            ],
            [
              7,
              0.7
            ],
            [
              8,
              0.8
            ],
            [
              9,
              0.8
            ],
            [
              10,
              1.2
            ],
            [
              11,
              2
            ],
            [
              12,
              3
            ],
            [
              13,
              4.2
            ],
            [
              14,
              4.8
            ],
            [
              15,
              7.2
            ],
            [
              16,
              10
            ],
            [
              17,
              24
            ],
            [
              18,
              24
            ],
            [
              19,
              24
            ],
            [
              20,
              24
            ],
            [
              21,
              24
            ]
          ],
          "base": 1.2
        },
        "line-color": "rgba(255, 255, 255, 1)"
      }
    },
    {
      "id": "countyway@fs-copy-copy",
      "type": "symbol",
      "metadata": {
        "description": "县道"
      },
      "source": "fs_tiles",
      "source-layer": "countyway@fs",
      "minzoom": 13,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "text-field": "{NAME}",
        "text-font": [
          "Microsoft YaHei UI Regular"
        ],
        "text-size": {
          "stops": [
            [
              12,
              10
            ],
            [
              13,
              11
            ],
            [
              14,
              11
            ],
            [
              15,
              12
            ],
            [
              16,
              13
            ],
            [
              17,
              13
            ],
            [
              18,
              13
            ],
            [
              19,
              13
            ]
          ],
          "base": 1
        },
        "text-padding": 2,
        "symbol-placement": "line",
        "text-pitch-alignment": "viewport"
      },
      "paint": {
        "text-halo-color": "#ffffff",
        "text-translate-anchor": "viewport",
        "icon-color": "#ff0000",
        "text-color": "#8594ab",
        "text-halo-width": 1
      }
    },
    {
      "id": "metro@fs-border",
      "type": "line",
      "metadata": {
        "description": "地铁"
      },
      "source": "fs_tiles",
      "source-layer": "metro@fs",
      "minzoom": 11,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "line-cap": "square",
        "line-join": "miter"
      },
      "paint": {
        "line-translate-anchor": "viewport",
        "line-width": {
          "stops": [
            [
              10,
              2.8
            ],
            [
              19,
              6
            ]
          ],
          "base": 1.2
        },
        "line-gap-width": 0,
        "line-opacity": 1,
        "line-color": "#ffffff"
      }
    },
    {
      "id": "metro@fs",
      "type": "line",
      "metadata": {
        "description": "地铁"
      },
      "source": "fs_tiles",
      "source-layer": "metro@fs",
      "minzoom": 11,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "line-cap": "round",
        "line-join": "round"
      },
      "paint": {
        "line-translate-anchor": "viewport",
        "line-width": {
          "stops": [
            [
              10,
              1.8
            ],
            [
              19,
              3.6
            ]
          ],
          "base": 1.2
        },
        "line-opacity": 0.15,
        "line-color": "rgba(67, 0, 160, 1)"
      }
    },
    {
      "id": "fastroad@fs-copy",
      "type": "line",
      "metadata": {
        "description": "城市快速通道"
      },
      "source": "fs_tiles",
      "source-layer": "fastroad@fs",
      "minzoom": 9,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "line-cap": "butt"
      },
      "paint": {
        "line-translate-anchor": "viewport",
        "line-width": {
          "stops": [
            [
              5,
              0
            ],
            [
              6,
              0
            ],
            [
              7,
              0.6
            ],
            [
              8,
              0.6
            ],
            [
              9,
              0.6
            ],
            [
              10,
              0.6
            ],
            [
              11,
              0.8
            ],
            [
              12,
              0.8
            ],
            [
              13,
              1
            ],
            [
              14,
              1
            ],
            [
              15,
              1.2
            ],
            [
              16,
              1.2
            ],
            [
              17,
              1.4
            ],
            [
              18,
              1.4
            ],
            [
              19,
              1.4
            ]
          ],
          "base": 1.2
        },
        "line-gap-width": {
          "stops": [
            [
              5,
              0.6
            ],
            [
              6,
              0.8
            ],
            [
              7,
              1.4
            ],
            [
              8,
              1.8
            ],
            [
              9,
              2
            ],
            [
              10,
              2.4
            ],
            [
              11,
              2.8
            ],
            [
              12,
              3.2
            ],
            [
              13,
              5.4
            ],
            [
              14,
              6.8
            ],
            [
              15,
              8
            ],
            [
              16,
              14
            ],
            [
              17,
              20
            ],
            [
              18,
              20
            ],
            [
              19,
              20
            ]
          ],
          "base": 1.2
        },
        "line-color": {
          "stops": [
            [
              5,
              "#e2e4e6"
            ],
            [
              19,
              "#d0d4d8"
            ]
          ],
          "base": 1
        }
      }
    },
    {
      "id": "fastroad@fs",
      "type": "line",
      "metadata": {
        "description": "城市快速通道"
      },
      "source": "fs_tiles",
      "source-layer": "fastroad@fs",
      "minzoom": 9,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "line-cap": "round",
        "line-join": "round"
      },
      "paint": {
        "line-translate-anchor": "viewport",
        "line-width": {
          "stops": [
            [
              5,
              0.7
            ],
            [
              6,
              0.8
            ],
            [
              7,
              1.4
            ],
            [
              8,
              1.8
            ],
            [
              9,
              2
            ],
            [
              10,
              2.4
            ],
            [
              11,
              2.8
            ],
            [
              12,
              3.2
            ],
            [
              13,
              5.4
            ],
            [
              14,
              6.8
            ],
            [
              15,
              8
            ],
            [
              16,
              14
            ],
            [
              17,
              20
            ],
            [
              18,
              20
            ],
            [
              19,
              20
            ]
          ],
          "base": 1.2
        },
        "line-color": "#ffffff"
      }
    },
    {
      "id": "fastroad@fs-copy-copy",
      "type": "symbol",
      "metadata": {
        "description": "城市快速通道"
      },
      "source": "fs_tiles",
      "source-layer": "fastroad@fs",
      "minzoom": 9,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "symbol-placement": "line",
        "text-font": [
          "Microsoft YaHei UI Regular"
        ],
        "text-field": "{NAME}",
        "text-size": {
          "stops": [
            [
              11,
              12
            ],
            [
              12,
              13
            ],
            [
              13,
              13
            ],
            [
              14,
              14
            ],
            [
              15,
              14
            ],
            [
              16,
              15
            ],
            [
              17,
              15
            ],
            [
              18,
              15
            ],
            [
              19,
              15
            ],
            [
              20,
              16
            ],
            [
              21,
              16
            ]
          ],
          "base": 1
        },
        "text-padding": 2
      },
      "paint": {
        "text-halo-color": "#ffffff",
        "text-translate-anchor": "viewport",
        "icon-color": "#ff0000",
        "text-color": "#32496c",
        "text-halo-width": 1
      }
    },
    {
      "id": "highway@fs-border",
      "type": "line",
      "metadata": {
        "description": "高速公路"
      },
      "source": "fs_tiles",
      "source-layer": "highway@fs",
      "minzoom": 8,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "line-cap": "butt",
        "line-join": "round"
      },
      "paint": {
        "line-translate-anchor": "viewport",
        "line-width": {
          "stops": [
            [
              5,
              0
            ],
            [
              6,
              0
            ],
            [
              7,
              0.6
            ],
            [
              8,
              0.6
            ],
            [
              9,
              0.6
            ],
            [
              10,
              0.6
            ],
            [
              11,
              0.8
            ],
            [
              12,
              0.8
            ],
            [
              13,
              1
            ],
            [
              14,
              1
            ],
            [
              15,
              1.2
            ],
            [
              16,
              1.2
            ],
            [
              17,
              1.4
            ],
            [
              18,
              1.4
            ],
            [
              19,
              1.4
            ],
            [
              20,
              1.6
            ],
            [
              21,
              1.6
            ]
          ],
          "base": 1.2
        },
        "line-gap-width": {
          "stops": [
            [
              5,
              0.6
            ],
            [
              6,
              0.8
            ],
            [
              7,
              1.4
            ],
            [
              8,
              1.8
            ],
            [
              9,
              2
            ],
            [
              10,
              2.4
            ],
            [
              11,
              2.8
            ],
            [
              12,
              3.2
            ],
            [
              13,
              5.4
            ],
            [
              14,
              6.8
            ],
            [
              15,
              8
            ],
            [
              16,
              14
            ],
            [
              17,
              24
            ],
            [
              18,
              24
            ],
            [
              19,
              24
            ],
            [
              20,
              24
            ],
            [
              21,
              24
            ]
          ],
          "base": 1.2
        },
        "line-color": {
          "stops": [
            [
              5,
              "rgba(226, 228, 230, 1)"
            ],
            [
              21,
              "rgba(208, 212, 216, 1)"
            ]
          ],
          "base": 1
        }
      }
    },
    {
      "id": "highway@fs",
      "type": "line",
      "metadata": {
        "description": "高速公路"
      },
      "source": "fs_tiles",
      "source-layer": "highway@fs",
      "minzoom": 8,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "line-cap": "butt",
        "line-join": "round"
      },
      "paint": {
        "line-translate-anchor": "viewport",
        "line-width": {
          "stops": [
            [
              5,
              0.7
            ],
            [
              6,
              0.8
            ],
            [
              7,
              1.4
            ],
            [
              8,
              1.8
            ],
            [
              9,
              2
            ],
            [
              10,
              2.4
            ],
            [
              11,
              2.8
            ],
            [
              12,
              3.2
            ],
            [
              13,
              5.4
            ],
            [
              14,
              6.8
            ],
            [
              15,
              8
            ],
            [
              16,
              14
            ],
            [
              17,
              24
            ],
            [
              18,
              24
            ],
            [
              19,
              24
            ],
            [
              20,
              24
            ],
            [
              21,
              24
            ]
          ],
          "base": 1.2
        },
        "line-color": "rgba(255, 255, 255, 1)"
      }
    },
    {
      "id": "highway@fs-copy-copy",
      "type": "symbol",
      "metadata": {
        "description": "高速公路"
      },
      "source": "fs_tiles",
      "source-layer": "highway@fs",
      "minzoom": 10,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "text-field": "{NAME}",
        "text-font": [
          "Microsoft YaHei UI Regular"
        ],
        "text-size": {
          "stops": [
            [
              11,
              12
            ],
            [
              12,
              13
            ],
            [
              13,
              13
            ],
            [
              14,
              14
            ],
            [
              15,
              14
            ],
            [
              16,
              15
            ],
            [
              17,
              15
            ],
            [
              18,
              15
            ],
            [
              19,
              15
            ],
            [
              20,
              15
            ],
            [
              21,
              15
            ]
          ],
          "base": 1
        },
        "text-padding": 2,
        "symbol-placement": "line",
        "text-pitch-alignment": "viewport",
        "text-rotation-alignment": "map"
      },
      "paint": {
        "text-halo-color": "#fffffe",
        "text-translate-anchor": "viewport",
        "icon-color": "#ff0000",
        "text-color": "#32496c",
        "text-halo-width": 1
      }
    },
    {
      "id": "railway@fs",
      "type": "line",
      "metadata": {
        "description": "铁路"
      },
      "source": "fs_tiles",
      "source-layer": "railway@fs",
      "minzoom": 9,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "line-cap": "square",
        "line-join": "miter"
      },
      "paint": {
        "line-width": {
          "stops": [
            [
              9,
              2
            ],
            [
              21,
              9
            ]
          ]
        },
        "line-color": "rgba(221, 221, 221, 1)",
        "line-blur": 0
      }
    },
    {
      "id": "railway@fs-copy",
      "type": "line",
      "metadata": {
        "description": "铁路"
      },
      "source": "fs_tiles",
      "source-layer": "railway@fs",
      "minzoom": 9,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "line-cap": "square",
        "line-join": "miter"
      },
      "paint": {
        "line-width": {
          "stops": [
            [
              9,
              2
            ],
            [
              21,
              8
            ]
          ]
        },
        "line-color": "rgba(255, 255, 255, 1)",
        "line-blur": 0,
        "line-dasharray": [
          3,
          3
        ]
      }
    },
    {
      "id": "railway@fs-copy-copy",
      "type": "symbol",
      "metadata": {
        "description": "铁路"
      },
      "source": "fs_tiles",
      "source-layer": "railway@fs",
      "minzoom": 9,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "symbol-placement": "line",
        "text-field": "{NAME}",
        "text-font": [
          "Microsoft YaHei UI Regular"
        ],
        "text-size": 12
      },
      "paint": {
        "text-color": "rgba(102, 102, 102, 1)",
        "text-halo-color": "rgba(255, 255, 255, 1)",
        "text-halo-width": 5,
        "text-halo-blur": 5
      }
    },
    {
      "id": "provincialhighway@fs-border",
      "type": "line",
      "metadata": {
        "description": "省道"
      },
      "source": "fs_tiles",
      "source-layer": "provincialhighway@fs",
      "minzoom": 8,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "line-cap": "butt",
        "line-join": "round"
      },
      "paint": {
        "line-translate-anchor": "viewport",
        "line-width": {
          "stops": [
            [
              5,
              0
            ],
            [
              6,
              0
            ],
            [
              7,
              0.4
            ],
            [
              8,
              0.4
            ],
            [
              9,
              0.4
            ],
            [
              10,
              0.4
            ],
            [
              11,
              0.6
            ],
            [
              12,
              0.6
            ],
            [
              13,
              0.8
            ],
            [
              14,
              0.8
            ],
            [
              15,
              1
            ],
            [
              16,
              1
            ],
            [
              17,
              1.2
            ],
            [
              18,
              1.2
            ],
            [
              19,
              1.2
            ],
            [
              20,
              1.4
            ],
            [
              21,
              1.4
            ]
          ],
          "base": 1.2
        },
        "line-gap-width": {
          "stops": [
            [
              5,
              0.6
            ],
            [
              6,
              0.8
            ],
            [
              7,
              1
            ],
            [
              8,
              1.2
            ],
            [
              9,
              1.8
            ],
            [
              10,
              2
            ],
            [
              11,
              2.4
            ],
            [
              12,
              2.8
            ],
            [
              13,
              3.6
            ],
            [
              14,
              5.4
            ],
            [
              15,
              7.2
            ],
            [
              16,
              10.8
            ],
            [
              17,
              20
            ],
            [
              18,
              20
            ],
            [
              19,
              20
            ],
            [
              20,
              20
            ],
            [
              21,
              20
            ]
          ],
          "base": 1.2
        },
        "line-color": {
          "stops": [
            [
              8,
              "#d2d6d9"
            ],
            [
              19,
              "#d0d4d8"
            ]
          ],
          "base": 1
        }
      }
    },
    {
      "id": "provincialhighway@fs",
      "type": "line",
      "metadata": {
        "description": "省道"
      },
      "source": "fs_tiles",
      "source-layer": "provincialhighway@fs",
      "minzoom": 8,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "line-cap": "butt",
        "line-join": "round"
      },
      "paint": {
        "line-translate-anchor": "viewport",
        "line-width": {
          "stops": [
            [
              5,
              0.6
            ],
            [
              6,
              0.8
            ],
            [
              7,
              1
            ],
            [
              8,
              1.2
            ],
            [
              9,
              1.8
            ],
            [
              10,
              2
            ],
            [
              11,
              2.4
            ],
            [
              12,
              2.8
            ],
            [
              13,
              3.6
            ],
            [
              14,
              5.4
            ],
            [
              15,
              7.2
            ],
            [
              16,
              10.8
            ],
            [
              17,
              20
            ],
            [
              18,
              20
            ],
            [
              19,
              20
            ],
            [
              20,
              20
            ],
            [
              21,
              20
            ]
          ],
          "base": 1.2
        },
        "line-color": "rgba(255, 255, 255, 1)"
      }
    },
    {
      "id": "provincialhighway@fs-copy-copy",
      "type": "symbol",
      "metadata": {
        "description": "省道"
      },
      "source": "fs_tiles",
      "source-layer": "provincialhighway@fs",
      "minzoom": 10,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "symbol-placement": "line",
        "text-field": "{NAME}",
        "text-font": [
          "Microsoft YaHei UI Regular"
        ],
        "text-size": {
          "stops": [
            [
              12,
              12
            ],
            [
              13,
              12
            ],
            [
              14,
              13
            ],
            [
              15,
              13
            ],
            [
              16,
              14
            ],
            [
              17,
              14
            ],
            [
              18,
              14
            ],
            [
              19,
              14
            ],
            [
              20,
              14
            ],
            [
              21,
              14
            ]
          ],
          "base": 1
        },
        "text-padding": 2
      },
      "paint": {
        "text-halo-color": "#ffffff",
        "text-translate-anchor": "viewport",
        "icon-color": "#ff0000",
        "text-color": "#63768f",
        "text-halo-width": 1
      }
    },
    {
      "id": "nationalhighway@fs-border",
      "type": "line",
      "metadata": {
        "description": "国道"
      },
      "source": "fs_tiles",
      "source-layer": "nationalhighway@fs",
      "minzoom": 8,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "line-cap": "butt",
        "line-join": "round"
      },
      "paint": {
        "line-translate-anchor": "viewport",
        "line-width": {
          "stops": [
            [
              5,
              0
            ],
            [
              6,
              0
            ],
            [
              7,
              0.4
            ],
            [
              8,
              0.4
            ],
            [
              9,
              0.4
            ],
            [
              10,
              0.4
            ],
            [
              11,
              0.6
            ],
            [
              12,
              0.6
            ],
            [
              13,
              0.8
            ],
            [
              14,
              0.8
            ],
            [
              15,
              1
            ],
            [
              16,
              1
            ],
            [
              17,
              1.2
            ],
            [
              18,
              1.2
            ],
            [
              19,
              1.2
            ],
            [
              20,
              1.4
            ],
            [
              21,
              1.4
            ]
          ],
          "base": 1.2
        },
        "line-gap-width": {
          "stops": [
            [
              5,
              0.6
            ],
            [
              6,
              0.8
            ],
            [
              7,
              1
            ],
            [
              8,
              1.2
            ],
            [
              9,
              1.8
            ],
            [
              10,
              2
            ],
            [
              11,
              2.4
            ],
            [
              12,
              2.8
            ],
            [
              13,
              3.6
            ],
            [
              14,
              5.4
            ],
            [
              15,
              7.2
            ],
            [
              16,
              10.8
            ],
            [
              17,
              20
            ],
            [
              18,
              20
            ],
            [
              19,
              20
            ],
            [
              20,
              20
            ],
            [
              21,
              20
            ]
          ],
          "base": 1.2
        },
        "line-color": {
          "stops": [
            [
              6,
              "#dee1e3"
            ],
            [
              19,
              "#d0d4d8"
            ]
          ],
          "base": 1
        }
      }
    },
    {
      "id": "nationalhighway@fs",
      "type": "line",
      "metadata": {
        "description": "国道"
      },
      "source": "fs_tiles",
      "source-layer": "nationalhighway@fs",
      "minzoom": 8,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "line-cap": "round",
        "line-join": "round"
      },
      "paint": {
        "line-translate-anchor": "viewport",
        "line-width": {
          "stops": [
            [
              5,
              0.6
            ],
            [
              6,
              0.8
            ],
            [
              7,
              1
            ],
            [
              8,
              1.2
            ],
            [
              9,
              1.8
            ],
            [
              10,
              2
            ],
            [
              11,
              2.4
            ],
            [
              12,
              2.8
            ],
            [
              13,
              3.6
            ],
            [
              14,
              5.4
            ],
            [
              15,
              7.2
            ],
            [
              16,
              10.8
            ],
            [
              17,
              20
            ],
            [
              18,
              20
            ],
            [
              19,
              20
            ],
            [
              20,
              20
            ],
            [
              21,
              20
            ]
          ],
          "base": 1.2
        },
        "line-color": "#ffffff"
      }
    },
    {
      "id": "nationalhighway@fs-copy-copy",
      "type": "symbol",
      "metadata": {
        "description": "国道"
      },
      "source": "fs_tiles",
      "source-layer": "nationalhighway@fs",
      "minzoom": 8,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "symbol-placement": "line",
        "text-field": "{NAME}",
        "text-font": [
          "Microsoft YaHei UI Regular"
        ],
        "text-size": {
          "stops": [
            [
              11,
              12
            ],
            [
              12,
              12
            ],
            [
              13,
              12
            ],
            [
              14,
              13
            ],
            [
              15,
              13
            ],
            [
              16,
              14
            ],
            [
              17,
              14
            ],
            [
              18,
              14
            ],
            [
              19,
              14
            ],
            [
              20,
              16
            ],
            [
              21,
              16
            ]
          ],
          "base": 1
        },
        "text-padding": 2
      },
      "paint": {
        "text-halo-color": "#ffffff",
        "text-translate-anchor": "viewport",
        "icon-color": "#ff0000",
        "text-color": "#63768f",
        "text-halo-width": 1
      }
    },
    {
      "id": "csg_city_label@csg",
      "type": "symbol",
      "metadata": {
        "description": "南网地市标注"
      },
      "source": "world",
      "source-layer": "csg_city_label@csg",
      "minzoom": 6,
      "maxzoom": 19.01,
      "layout": {
        "visibility": "visible",
        "text-field": "{NAME}",
        "text-font": [
          "Microsoft YaHei UI Regular"
        ],
        "text-max-width": 10,
        "text-anchor": "top",
        "text-size": 12
      },
      "paint": {
        "text-color": "rgba(51, 51, 51, 1)",
        "text-halo-color": "rgba(255, 255, 255, 1)",
        "text-halo-width": 2,
        "text-halo-blur": 2
      }
    },
    {
      "id": "china_province_label2@csg",
      "type": "symbol",
      "metadata": {
        "description": "中国省份标注"
      },
      "source": "world",
      "source-layer": "china_province_label@csg",
      "minzoom": 4,
      "maxzoom": 9.01,
      "filter": [
        "==",
        "yxcode",
        "20"
      ],
      "layout": {
        "visibility": "visible",
        "text-field": "{NAME}",
        "text-font": [
          "Microsoft YaHei UI Regular"
        ],
        "text-max-width": 10,
        "text-anchor": "top",
        "text-size": 12
      },
      "paint": {
        "text-color": "rgba(102, 102, 102, 1)",
        "text-halo-color": "rgba(255, 255, 255, 1)",
        "text-halo-width": 2,
        "text-halo-blur": 2
      }
    },
    {
      "id": "worldlabel@csg",
      "type": "symbol",
      "metadata": {
        "description": "国家标注"
      },
      "source": "world",
      "source-layer": "china_province_label@csg",
      "minzoom": 3,
      "maxzoom": 9.01,
      "filter": [
        "!in",
        "code",
        1,
        3,
        2
      ],
      "layout": {
        "visibility": "visible",
        "text-field": "{NAME}",
        "text-font": [
          "Microsoft YaHei UI Regular"
        ],
        "text-max-width": 10,
        "text-anchor": "top",
        "text-size": 12
      },
      "paint": {
        "text-color": "rgba(102, 102, 102, 1)",
        "text-halo-color": "rgba(255, 255, 255, 1)",
        "text-halo-width": 2,
        "text-halo-blur": 2
      }
    },
    {
      "id": "railwaystation@fs",
      "type": "symbol",
      "metadata": {
        "description": "火车站"
      },
      "source": "fs_tiles",
      "source-layer": "railwaystation@fs",
      "minzoom": 13,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "text-field": "{NAME}",
        "text-font": [
          "Microsoft YaHei UI Regular"
        ],
        "text-max-width": 10,
        "text-anchor": "left",
        "text-size": 11,
        "icon-size": 0.89,
        "icon-image": "poi_code_230100_16",
        "icon-offset": [
          -10,
          0
        ]
      },
      "paint": {
        "text-halo-color": "#ffffff",
        "text-translate-anchor": "viewport",
        "icon-color": "#ff0000",
        "icon-translate-anchor": "viewport",
        "text-color": "#658993",
        "text-halo-width": 1,
        "text-translate": [
          0,
          -2
        ]
      }
    },
    {
      "id": "finance@fs",
      "type": "symbol",
      "metadata": {
        "description": "金融机构"
      },
      "source": "fs_tiles",
      "source-layer": "finance@fs",
      "minzoom": 16,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "text-field": "{NAME}",
        "text-font": [
          "Microsoft YaHei UI Regular"
        ],
        "text-max-width": 10,
        "text-anchor": "left",
        "text-size": 11,
        "icon-size": 0.89,
        "icon-image": "poi_code_150102_16",
        "icon-offset": [
          -10,
          0
        ]
      },
      "paint": {
        "text-halo-color": "#ffffff",
        "text-translate-anchor": "viewport",
        "icon-color": "#ff0000",
        "icon-translate-anchor": "viewport",
        "text-color": "#658993",
        "text-halo-width": 1,
        "text-translate": [
          0,
          -2
        ]
      }
    },
    {
      "id": "village@fs",
      "type": "symbol",
      "metadata": {
        "description": "村名"
      },
      "source": "fs_tiles",
      "source-layer": "village@fs",
      "minzoom": 15,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "text-field": "{NAME}",
        "text-font": [
          "Microsoft YaHei UI Regular"
        ],
        "text-max-width": 10,
        "text-anchor": "top",
        "text-size": {
          "stops": [
            [
              11,
              9
            ],
            [
              12,
              10
            ],
            [
              13,
              10
            ],
            [
              14,
              11
            ],
            [
              15,
              11
            ],
            [
              16,
              11
            ],
            [
              17,
              11
            ],
            [
              18,
              11
            ],
            [
              19,
              11
            ]
          ],
          "base": 1
        },
        "text-padding": 10
      },
      "paint": {
        "text-halo-color": "#ffffff",
        "icon-color": "#ff0000",
        "text-color": "#a1b394",
        "text-halo-width": 1
      }
    },
    {
      "id": "tour@fs",
      "type": "symbol",
      "metadata": {
        "description": "景点"
      },
      "source": "fs_tiles",
      "source-layer": "tour@fs",
      "minzoom": 13,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "text-field": "{NAME}",
        "text-font": [
          "Microsoft YaHei UI Regular"
        ],
        "text-max-width": 10,
        "text-anchor": "left",
        "text-size": 11,
        "icon-size": 0.89,
        "icon-image": "poi_code_180304_16",
        "icon-offset": [
          -12,
          0
        ]
      },
      "paint": {
        "text-halo-color": "#ffffff",
        "text-translate-anchor": "viewport",
        "icon-color": "#ff0000",
        "icon-translate-anchor": "viewport",
        "text-color": "#658993",
        "text-halo-width": 1,
        "text-translate": [
          0,
          0
        ]
      }
    },
    {
      "id": "education@fs",
      "type": "symbol",
      "metadata": {
        "description": "教育机构"
      },
      "source": "fs_tiles",
      "source-layer": "education@fs",
      "minzoom": 16,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "text-field": "{NAME}",
        "text-font": [
          "Microsoft YaHei UI Regular"
        ],
        "text-offset": [
          0.8,
          0
        ],
        "text-max-width": 8,
        "text-anchor": "left",
        "text-rotation-alignment": "viewport",
        "text-size": 11,
        "icon-size": 0.89,
        "icon-image": "poi_code_160102_16"
      },
      "paint": {
        "text-halo-color": "#ffffff",
        "text-translate-anchor": "viewport",
        "icon-color": "#ff0000",
        "icon-translate-anchor": "viewport",
        "text-color": "#658993",
        "text-halo-width": 1,
        "text-translate": [
          0,
          -2
        ]
      }
    },
    {
      "id": "hotal@fs",
      "type": "symbol",
      "metadata": {
        "description": "住宿"
      },
      "source": "fs_tiles",
      "source-layer": "hotal@fs",
      "minzoom": 17,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "text-field": "{NAME}",
        "text-font": [
          "Microsoft YaHei UI Regular"
        ],
        "text-max-width": 10,
        "text-anchor": "left",
        "text-size": 11,
        "icon-size": 0.89,
        "icon-image": "poi_code_120102_16",
        "icon-offset": [
          -12,
          0
        ]
      },
      "paint": {
        "text-halo-color": "#ffffff",
        "text-translate-anchor": "viewport",
        "icon-color": "#ff0000",
        "icon-translate-anchor": "viewport",
        "text-color": "#658993",
        "text-halo-width": 1,
        "text-translate": [
          0,
          -2
        ]
      }
    },
    {
      "id": "medical@fs",
      "type": "symbol",
      "metadata": {
        "description": "医疗"
      },
      "source": "fs_tiles",
      "source-layer": "medical@fs",
      "minzoom": 15,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "text-field": "{NAME}",
        "text-font": [
          "Microsoft YaHei UI Regular"
        ],
        "text-max-width": 10,
        "text-anchor": "left",
        "text-size": 11,
        "icon-size": 0.89,
        "icon-image": "poi_code_170100_16",
        "icon-padding": 0,
        "icon-offset": [
          -10,
          0
        ]
      },
      "paint": {
        "text-halo-color": "#ffffff",
        "text-translate-anchor": "viewport",
        "icon-color": "#ff0000",
        "icon-translate-anchor": "viewport",
        "text-color": "#658993",
        "text-halo-width": 1,
        "text-translate": [
          0,
          -2
        ]
      }
    },
    {
      "id": "country@fs",
      "type": "symbol",
      "metadata": {
        "description": "乡"
      },
      "source": "fs_tiles",
      "source-layer": "country@fs",
      "minzoom": 11,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "text-field": "{NAME}",
        "text-font": [
          "Microsoft YaHei UI Regular"
        ],
        "text-max-width": 10,
        "text-anchor": "top",
        "text-size": {
          "stops": [
            [
              9,
              10
            ],
            [
              10,
              10
            ],
            [
              11,
              10
            ],
            [
              12,
              11
            ],
            [
              13,
              12
            ]
          ],
          "base": 1
        },
        "text-padding": 10
      },
      "paint": {
        "text-halo-color": "#ffffff",
        "text-translate-anchor": "viewport",
        "icon-color": "#ff0000",
        "text-color": "#4c535e",
        "text-halo-width": 1,
        "text-opacity": 1
      }
    },
    {
      "id": "county@fs",
      "type": "symbol",
      "metadata": {
        "description": "县标注"
      },
      "source": "fs_tiles",
      "source-layer": "county@fs",
      "minzoom": 8,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "text-field": "{NAME}",
        "text-font": [
          "Microsoft YaHei UI Regular"
        ],
        "text-max-width": 10,
        "text-anchor": "top",
        "text-size": {
          "stops": [
            [
              9,
              13
            ],
            [
              10,
              14
            ],
            [
              11,
              14
            ],
            [
              12,
              15
            ]
          ],
          "base": 1
        }
      },
      "paint": {
        "text-halo-color": "#ffffff",
        "text-translate-anchor": "viewport",
        "icon-color": "#ff0000",
        "text-color": "#a0b0bc",
        "text-halo-width": 1,
        "text-translate": [
          0,
          0
        ]
      }
    },
    {
      "id": "park2@fs",
      "type": "symbol",
      "metadata": {
        "description": "公园"
      },
      "source": "fs_tiles",
      "source-layer": "park@fs",
      "minzoom": 15,
      "maxzoom": 21,
      "filter": [
        "==",
        "DLEVEL",
        "0"
      ],
      "layout": {
        "visibility": "visible",
        "text-field": "{NAME}",
        "text-font": [
          "Microsoft YaHei UI Regular"
        ],
        "text-max-width": 10,
        "text-anchor": "top",
        "text-size": 11
      },
      "paint": {
        "text-halo-color": "#ffffff",
        "icon-color": "#ff0000",
        "text-color": "#a1b394",
        "text-halo-width": 1
      }
    },
    {
      "id": "park@fs",
      "type": "symbol",
      "metadata": {
        "description": "公园"
      },
      "source": "fs_tiles",
      "source-layer": "park@fs",
      "minzoom": 15,
      "maxzoom": 21,
      "filter": [
        ">",
        "DLEVEL",
        "0"
      ],
      "layout": {
        "visibility": "visible",
        "text-field": "{NAME}",
        "text-font": [
          "Microsoft YaHei UI Regular"
        ],
        "text-max-width": 10,
        "text-anchor": "top",
        "text-size": 11
      },
      "paint": {
        "text-halo-color": "#ffffff",
        "icon-color": "#ff0000",
        "text-color": "#a1b394",
        "text-halo-width": 1
      }
    },
    {
      "id": "government@fs",
      "type": "symbol",
      "metadata": {
        "description": "政府机构"
      },
      "source": "fs_tiles",
      "source-layer": "government@fs",
      "minzoom": 16,
      "maxzoom": 21,
      "layout": {
        "visibility": "visible",
        "text-field": "{NAME}",
        "text-font": [
          "Microsoft YaHei UI Regular"
        ],
        "text-max-width": 10,
        "text-anchor": "left",
        "text-size": 11,
        "icon-size": 0.89,
        "icon-image": "poi_code_190100_16",
        "icon-offset": [
          -10,
          0
        ]
      },
      "paint": {
        "text-halo-color": "#ffffff",
        "text-translate-anchor": "viewport",
        "icon-color": "#ff0000",
        "icon-translate-anchor": "viewport",
        "text-color": "#658993",
        "text-halo-width": 1,
        "text-translate": [
          0,
          -2
        ]
      }
    }
  ],
  "id": "5usgfbh2k"
}
