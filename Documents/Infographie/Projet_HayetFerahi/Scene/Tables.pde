
float tableWidth = 80;
float tableDepth = 80;
float tableHeight = 20;


void createTable(float x, float z, int index, int totalTables) {
  PShape table;
  table = createShape(GROUP);
PShape top;
  // Create the table top
  top = createShape();
  top.beginShape(QUADS);
  
  // Définir les sommets pour chaque face de la boîte
  // Face avant
  top.vertex(-tableWidth / 2, -tableHeight / 2, -tableDepth / 2);
  top.vertex(tableWidth / 2, -tableHeight / 2, -tableDepth / 2);
  top.vertex(tableWidth / 2, tableHeight / 2, -tableDepth / 2);
  top.vertex(-tableWidth / 2, tableHeight / 2, -tableDepth / 2);
  
  // Face arrière
  top.vertex(-tableWidth / 2, -tableHeight / 2, tableDepth / 2);
  top.vertex(tableWidth / 2, -tableHeight / 2, tableDepth / 2);
  top.vertex(tableWidth / 2, tableHeight / 2, tableDepth / 2);
  top.vertex(-tableWidth / 2, tableHeight / 2, tableDepth / 2);
  
  // Face gauche
  top.vertex(-tableWidth / 2, -tableHeight / 2, -tableDepth / 2);
  top.vertex(-tableWidth / 2, -tableHeight / 2, tableDepth / 2);
  top.vertex(-tableWidth / 2, tableHeight / 2, tableDepth / 2);
  top.vertex(-tableWidth / 2, tableHeight / 2, -tableDepth / 2);
  
  // Face droite
  top.vertex(tableWidth / 2, -tableHeight / 2, -tableDepth / 2);
  top.vertex(tableWidth / 2, -tableHeight / 2, tableDepth / 2);
  top.vertex(tableWidth / 2, tableHeight / 2, tableDepth / 2);
  top.vertex(tableWidth / 2, tableHeight / 2, -tableDepth / 2);
  
  // Face supérieure
  top.vertex(-tableWidth / 2, -tableHeight / 2, tableDepth / 2);
  top.vertex(tableWidth / 2, -tableHeight / 2, tableDepth / 2);
  top.vertex(tableWidth / 2, -tableHeight / 2, -tableDepth / 2);
  top.vertex(-tableWidth / 2, -tableHeight / 2, -tableDepth / 2);
  
  // Face inférieure
  top.vertex(-tableWidth / 2, tableHeight / 2, tableDepth / 2);
  top.vertex(tableWidth / 2, tableHeight / 2, tableDepth / 2);
  top.vertex(tableWidth / 2, tableHeight / 2, -tableDepth / 2);
  top.vertex(-tableWidth / 2, tableHeight / 2, -tableDepth / 2);
  
  top.endShape();
  // Dimensions of legs
    top.translate(x,y,z);
    top.rotateY(HALF_PI);
  shape(top);

  table.translate(x, y + legHeight + tableHeight, z);
  table.rotateY(HALF_PI);
  shape(table);
}

void createTables(float[] xValues, float z) {
  for (int i = 0; i < xValues.length; i++) {
    createTable(xValues[i], z, i, xValues.length);
  }
}
