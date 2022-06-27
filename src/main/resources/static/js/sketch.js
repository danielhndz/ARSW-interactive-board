const color = {
  r: Math.floor(Math.random() * 255),
  g: Math.floor(Math.random() * 255),
  b: Math.floor(Math.random() * 255),
};

//Este código asume que las librerías de P5.js ya están cargadas.
//Esta función se ejecuta una sola vez al inicio del script.
function setup() {
  createCanvas(window.innerWidth - 200, window.innerHeight - 100);
  background(255, 255, 255);
}

// Esta función se ejecuta repetidas veces indefinidamente.
function draw() {
  if (mouseIsPressed === true) {
    fill(color.r, color.g, color.b);
    noStroke();
    ellipse(mouseX, mouseY, 3, 3);
    axios.post("/drawPoint", {
      x: mouseX,
      y: mouseY,
      color: color,
    });
  }
  if (mouseIsPressed === false) {
    fill(255, 255, 255);
  }
}

function refresh() {
  clear();
  background(255, 255, 255);
  axios.get("/getPoints").then((points) => {
    if (points.data.length > 0) {
      for (const p of points.data) {
        fill(p.color.r, p.color.g, p.color.b);
        noStroke();
        ellipse(p.x, p.y, 3, 3);
      }
    }
  });
}

function clearBoard() {
  clear();
  background(255, 255, 255);
  axios.get("/clear");
}

setInterval(refresh, 1000);
