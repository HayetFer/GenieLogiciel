float computerWidth = 50;
float computerHeight = 40;
float computerDepth = 20;
float standWidth = 30;
float standHeight = 10;
float screenHeight = 35;
float screenThickness = 2;

void createComputer(float x, float z) {
  PShape computer = createShape(GROUP);
  
  // Create computer base/tower
  PShape tower = createShape();
  tower.beginShape(QUADS);
  tower.fill(100); // Dark gray color for tower
  
  // Tower faces
  tower.vertex(-computerWidth/2, 0, -computerDepth/2);
  tower.vertex(computerWidth/2, 0, -computerDepth/2);
  tower.vertex(computerWidth/2, computerHeight, -computerDepth/2);
  tower.vertex(-computerWidth/2, computerHeight, -computerDepth/2);
  
  tower.vertex(computerWidth/2, 0, -computerDepth/2);
  tower.vertex(computerWidth/2, 0, computerDepth/2);
  tower.vertex(computerWidth/2, computerHeight, computerDepth/2);
  tower.vertex(computerWidth/2, computerHeight, -computerDepth/2);
  
  tower.vertex(computerWidth/2, 0, computerDepth/2);
  tower.vertex(-computerWidth/2, 0, computerDepth/2);
  tower.vertex(-computerWidth/2, computerHeight, computerDepth/2);
  tower.vertex(computerWidth/2, computerHeight, computerDepth/2);
  
  tower.vertex(-computerWidth/2, 0, computerDepth/2);
  tower.vertex(-computerWidth/2, 0, -computerDepth/2);
  tower.vertex(-computerWidth/2, computerHeight, -computerDepth/2);
  tower.vertex(-computerWidth/2, computerHeight, computerDepth/2);
  
  tower.endShape(CLOSE);
  computer.addChild(tower);
  

  
  // Translate and position computer
  pushMatrix();
  //rotate(-PI);
  rotateY(-HALF_PI);
  translate(x, y - legHeight - tableHeight, z);
  shape(computer);
  popMatrix();
}
