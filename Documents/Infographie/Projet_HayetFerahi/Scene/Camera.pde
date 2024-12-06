// Camera Movement Logic
float camX = 0, camY = 350, camZ = 200; 
float camAngleY = 0;                    
float moveSpeed = 10;      
float turnSpeed = 0.10; 

void updateCamera() {
  if (keyPressed) {
    if (keyCode == LEFT) {
      camAngleY -= turnSpeed; 
    }
    if (keyCode == RIGHT) {
      camAngleY += turnSpeed; 
    }
  }

  if (keyPressed) {
    if (keyCode == UP) {
      camX += cos(camAngleY) * moveSpeed; 
      camZ += sin(camAngleY) * moveSpeed;
    }
    if (keyCode == DOWN) {
      camX -= cos(camAngleY) * moveSpeed; 
      camZ -= sin(camAngleY) * moveSpeed;
    }
  }

  camera(camX, camY, camZ, 
         camX + cos(camAngleY), camY, camZ + sin(camAngleY), 
         0, 1, 0);
}
