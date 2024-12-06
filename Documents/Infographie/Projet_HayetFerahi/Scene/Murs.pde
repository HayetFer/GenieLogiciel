void drawRoom() {
  translate(width / 2, height / 2, -200); // Centrer la pièce dans l'affichage
  translate(x, y, z); // Positionner la pièce
  
  // Dessin des murs
  noStroke();
  fill(150, 200, 255); // Couleur des murs
  
  // Mur arrière
  pushMatrix();
  translate(0, 0, -roomDepth / 2);
  beginShape(QUADS);
  vertex(-roomWidth / 2, -roomHeight / 2, 0);
  vertex(roomWidth / 2, -roomHeight / 2, 0);
  vertex(roomWidth / 2, roomHeight / 2, 0);
  vertex(-roomWidth / 2, roomHeight / 2, 0);
  endShape();
  popMatrix();
  
  // Mur avant
  pushMatrix();
  translate(0, 0, roomDepth / 2);
  beginShape(QUADS);
  vertex(-roomWidth / 2, -roomHeight / 2, 0);
  vertex(roomWidth / 2, -roomHeight / 2, 0);
  vertex(roomWidth / 2, roomHeight / 2, 0);
  vertex(-roomWidth / 2, roomHeight / 2, 0);
  endShape();
  popMatrix();
  
  // Mur gauche
  pushMatrix();
  translate(-roomWidth / 2, 0, 0);
  rotateY(HALF_PI);
  beginShape(QUADS);
  vertex(-roomDepth / 2, -roomHeight / 2, 0);
  vertex(roomDepth / 2, -roomHeight / 2, 0);
  vertex(roomDepth / 2, roomHeight / 2, 0);
  vertex(-roomDepth / 2, roomHeight / 2, 0);
  endShape();
  popMatrix();
  
  // Mur droit
  pushMatrix();
  translate(roomWidth / 2, 0, 0);
  rotateY(HALF_PI);
  beginShape(QUADS);
  vertex(-roomDepth / 2, -roomHeight / 2, 0);
  vertex(roomDepth / 2, -roomHeight / 2, 0);
  vertex(roomDepth / 2, roomHeight / 2, 0);
  vertex(-roomDepth / 2, roomHeight / 2, 0);
  endShape();
  popMatrix();
  
  // Sol
  pushMatrix();
  translate(0, roomHeight / 2, 0);
  rotateX(HALF_PI);
  fill(180, 220, 180); // Couleur du sol
  beginShape(QUADS);
  vertex(-roomWidth / 2, -roomDepth / 2, 0);
  vertex(roomWidth / 2, -roomDepth / 2, 0);
  vertex(roomWidth / 2, roomDepth / 2, 0);
  vertex(-roomWidth / 2, roomDepth / 2, 0);
  endShape();
  popMatrix();
  
  // Plafond
  pushMatrix();
  translate(0, -roomHeight / 2, 0);
  rotateX(HALF_PI);
  fill(220, 200, 220); // Couleur du plafond
  beginShape(QUADS);
  vertex(-roomWidth / 2, -roomDepth / 2, 0);
  vertex(roomWidth / 2, -roomDepth / 2, 0);
  vertex(roomWidth / 2, roomDepth / 2, 0);
  vertex(-roomWidth / 2, roomDepth / 2, 0);
  endShape();
  popMatrix();
}
