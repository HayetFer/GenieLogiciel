float x = 100;
float y = 100;
float z = 0;
float roomWidth = 900;
float roomHeight = 400;
float roomDepth = 700;


void setup() {
 size(800, 600, P3D);
  smooth(8);
}

void draw() {
  background(0);
   updateCamera();
   drawRoom();
  float[] xValuesTable1 = new float[4];
  for (int i = 0; i < 4; i++) {
    xValuesTable1[i] = x - 40 + tableWidth * i;
  }
  float[] xValuesTable2 = new float[6];
  for (int i = 0; i < 6; i++) {
    xValuesTable2[i] = x - 200 + tableWidth * i;
  }
  float[] xValuesTable3= new float[5];
  for (int i = 0; i < 5; i++) {
    xValuesTable3[i] = x - 130 + tableWidth * i;
  }
  createTables(xValuesTable1, z-roomDepth/2.7);
  createTables(xValuesTable2, z-roomDepth/2.7+tableWidth+80);
  createTables(xValuesTable2, z-roomDepth/2.7+tableWidth*3+80);
  createTables(xValuesTable3, z-roomDepth/2.7+tableWidth*5+80);
  for (int i = 0; i < 4; i++) {
    createChair(xValuesTable1[i]-tableWidth*4.5,z-roomDepth/2.7+tableDepth);
    createComputer(xValuesTable2[i]-tableWidth*2.5,z-roomDepth/2.7+tableDepth+tableWidth*5+70);
  }
  for (int i = 0; i < 6; i++) {
    createChair(xValuesTable2[i]-tableWidth*2.5,z-roomDepth/2.7+tableDepth+tableWidth+70);
    createChair(xValuesTable2[i]-tableWidth*2.5,z-roomDepth/2.7+tableDepth+tableWidth*3+70);
  }
   for (int i = 0; i < 5; i++) {
    createChair(xValuesTable2[i]-tableWidth*2.5,z-roomDepth/2.7+tableDepth+tableWidth*5+70);
  }  
}
