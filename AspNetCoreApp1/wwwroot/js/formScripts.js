var can = document.getElementById('canvas');
var ctx = can.getContext('2d');

function resizeCanvas() {
    can.width = document.querySelector('.form-container').offsetWidth;
    can.height = document.querySelector('.form-container').offsetHeight;
}

window.addEventListener('resize', resizeCanvas);
resizeCanvas();

// Particle setup
var p = [];

function Clear() {
  ctx.fillStyle = "rgba(0,0,0,0.07)";
  ctx.fillRect(0, 0, can.width, can.height);
}

function getRandomColor() {
  // Generate a random hue value
  currentHue = Math.floor(Math.random() * 360);
  return `hsl(${currentHue}, 100%, 50%)`;
}

function Particle(x, y, speed, color) {
  this.x = x;
  this.y = y;
  this.speed = speed;
  this.c = color;

  this.upd = function () {
    ctx.strokeStyle = this.c;
    ctx.lineWidth = 1;
    ctx.lineCap = "round";
    ctx.beginPath();
    ctx.moveTo(this.x, this.y);
    this.x += this.speed.x;
    this.y += this.speed.y;
    ctx.lineTo(this.x, this.y);
    ctx.stroke();

    // Determine the angle and magnitude of the particle's velocity
    this.ang = Math.atan2(this.speed.y, this.speed.x);
    this.mag = Math.sqrt(this.speed.x ** 2 + this.speed.y ** 2);
    var op = [this.ang + Math.PI / 4, this.ang - Math.PI / 4];
    var ch = Math.floor(Math.random() * op.length);

    // Randomly change direction
    if (Math.random() < 0.05) {
      this.speed.x = Math.cos(op[ch]) * this.mag;
      this.speed.y = Math.sin(op[ch]) * this.mag;
    }
  };
}

var speed = 5;
var period = 2000;

function pulse() {
  setTimeout(pulse, period);
  var color = getRandomColor(); // Update the color for all particles in this pulse
  // Adjust the number of particles based on form size
  var numParticles = Math.round(can.width * can.height / 5000);
  for (var i = 0; i < numParticles; i++) {
    var angle = Math.random() * 2 * Math.PI;
    p.push(new Particle(can.width / 2, can.height / 2, {
      x: Math.cos(angle) * speed,
      y: Math.sin(angle) * speed
    }, color));
  }
}

function gameMove() {
  requestAnimationFrame(gameMove);
  Clear();

  for (var i = 0; i < p.length; i++) {
    p[i].upd();
    if (p[i].x < 0 || p[i].x > can.width || p[i].y < 0 || p[i].y > can.height) {
      p.splice(i, 1);
    }
  }
}

pulse(); // Start the particle effect
gameMove(); // Start the animation loop