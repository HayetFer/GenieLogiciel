float chairWidth = 40;
float chairDepth = 40;
float chairHeight = 40;
float legWidth = 10;
float legHeight = 60;
float backrestHeight = 80;

void createChair(float x, float z) {
  PShape chair = createShape(GROUP);
  
  // Create the seat
  PShape seat = createShape();
  seat.beginShape(QUADS);
  seat.fill(200); // Optional: add color
  // Seat top
  seat.vertex(-chairWidth/2, 0, -chairDepth/2);
  seat.vertex(chairWidth/2, 0, -chairDepth/2);
  seat.vertex(chairWidth/2, 0, chairDepth/2);
  seat.vertex(-chairWidth/2, 0, chairDepth/2);
  
  // Seat sides
  seat.vertex(-chairWidth/2, 0, -chairDepth/2);
  seat.vertex(-chairWidth/2, -legHeight/4, -chairDepth/2);
  seat.vertex(chairWidth/2, -legHeight/4, -chairDepth/2);
  seat.vertex(chairWidth/2, 0, -chairDepth/2);
  
  seat.vertex(chairWidth/2, 0, -chairDepth/2);
  seat.vertex(chairWidth/2, -legHeight/4, -chairDepth/2);
  seat.vertex(chairWidth/2, -legHeight/4, chairDepth/2);
  seat.vertex(chairWidth/2, 0, chairDepth/2);
  
  seat.vertex(chairWidth/2, 0, chairDepth/2);
  seat.vertex(chairWidth/2, -legHeight/4, chairDepth/2);
  seat.vertex(-chairWidth/2, -legHeight/4, chairDepth/2);
  seat.vertex(-chairWidth/2, 0, chairDepth/2);
  
  seat.vertex(-chairWidth/2, 0, chairDepth/2);
  seat.vertex(-chairWidth/2, -legHeight/4, chairDepth/2);
  seat.vertex(-chairWidth/2, -legHeight/4, -chairDepth/2);
  seat.vertex(-chairWidth/2, 0, -chairDepth/2);
  
  seat.endShape(CLOSE);
  chair.addChild(seat);
  
  // Add legs
  for (int i = 0; i < 4; i++) {
    float offsetX = (i % 2 == 0) ? -chairWidth/2 + legWidth/2 : chairWidth/2 - legWidth/2;
    float offsetZ = (i < 2) ? -chairDepth/2 + legWidth/2 : chairDepth/2 - legWidth/2;
    
    PShape leg = createShape();
    leg.beginShape(QUADS);
    leg.fill(150); // Optional: different color for legs
    
    // Front face
    leg.vertex(offsetX - legWidth/2, 0, offsetZ - legWidth/2);
    leg.vertex(offsetX + legWidth/2, 0, offsetZ - legWidth/2);
    leg.vertex(offsetX + legWidth/2, -legHeight, offsetZ - legWidth/2);
    leg.vertex(offsetX - legWidth/2, -legHeight, offsetZ - legWidth/2);
    
    // Back face
    leg.vertex(offsetX - legWidth/2, 0, offsetZ + legWidth/2);
    leg.vertex(offsetX + legWidth/2, 0, offsetZ + legWidth/2);
    leg.vertex(offsetX + legWidth/2, -legHeight, offsetZ + legWidth/2);
    leg.vertex(offsetX - legWidth/2, -legHeight, offsetZ + legWidth/2);
    
    // Left face
    leg.vertex(offsetX - legWidth/2, 0, offsetZ - legWidth/2);
    leg.vertex(offsetX - legWidth/2, 0, offsetZ + legWidth/2);
    leg.vertex(offsetX - legWidth/2, -legHeight, offsetZ + legWidth/2);
    leg.vertex(offsetX - legWidth/2, -legHeight, offsetZ - legWidth/2);
    
    // Right face
    leg.vertex(offsetX + legWidth/2, 0, offsetZ - legWidth/2);
    leg.vertex(offsetX + legWidth/2, 0, offsetZ + legWidth/2);
    leg.vertex(offsetX + legWidth/2, -legHeight, offsetZ + legWidth/2);
    leg.vertex(offsetX + legWidth/2, -legHeight, offsetZ - legWidth/2);
    
    leg.endShape(CLOSE);
    chair.addChild(leg);
  }
  
  // Create the backrest
  PShape backrest = createShape();
  backrest.beginShape(QUADS);
  backrest.fill(180); // Optional: another color
  
  // Backrest main surface
  backrest.vertex(-chairWidth/2, 0, chairDepth/2);
  backrest.vertex(chairWidth/2, 0, chairDepth/2);
  backrest.vertex(chairWidth/2, backrestHeight, chairDepth/2);
  backrest.vertex(-chairWidth/2, backrestHeight, chairDepth/2);
  
  // Backrest side faces
  backrest.vertex(-chairWidth/2, 0, chairDepth/2);
  backrest.vertex(-chairWidth/2, backrestHeight, chairDepth/2);
  backrest.vertex(-chairWidth/2, backrestHeight, chairDepth/2 - legWidth/2);
  backrest.vertex(-chairWidth/2, 0, chairDepth/2 - legWidth/2);
  
  backrest.vertex(chairWidth/2, 0, chairDepth/2);
  backrest.vertex(chairWidth/2, backrestHeight, chairDepth/2);
  backrest.vertex(chairWidth/2, backrestHeight, chairDepth/2 - legWidth/2);
  backrest.vertex(chairWidth/2, 0, chairDepth/2 - legWidth/2);
  
  backrest.endShape(CLOSE);
  chair.addChild(backrest);
  
  // Translate and rotate the chair
  pushMatrix();
  rotate(-PI);
  rotateY(-HALF_PI);
  translate(x, y-legHeight-chairHeight-backrestHeight-seat.height, z);
  shape(chair);
  popMatrix();
}
