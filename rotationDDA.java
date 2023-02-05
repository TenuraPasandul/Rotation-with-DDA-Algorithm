import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class rotationDDA extends JFrame implements MouseListener
{
	int x1,y1,x2,y2;
	
	Graphics g;
	double[][] coordinates;
	
	rotationDDA(){
		super("rotateLine");
		setTitle("DDA");
		setSize(800,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		addMouseListener(this);
		setVisible(true);
		g=getGraphics();
		g.setColor(Color.red);
		coordinates=new double[2][2];
		}
	public void DDA(double x1,double y1,double x2,double y2){
		
		double dx,dy;
		double steps,xinc,yinc,x,y;
		dx=x2-x1;
		dy=y2-y1;
		
		if(Math.abs(dx)>Math.abs(dy)){
			steps=Math.abs(dx);
		}
		else{
			steps=Math.abs(dy);
		}
		xinc=dx/steps;
		yinc=dy/steps;
		
		x=x1;
		y=y1;
		
		for(int i=1;i<=steps;i++){
			x=x+xinc;
			y=y+yinc;
			g.drawLine((int)Math.round(x),(int)Math.round(y),(int)Math.round(x),(int)Math.round(y));
		}
	}
	public static void main(String a[]){
		rotationDDA b=new rotationDDA();
		b.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public double[][] translatePoints(double [][]points,double dx,double dy){
		for(int j=0;j<points[0].length;j++){
			points[0][j]+=dx;
			points[1][j]+=dy;
		}
		return points;
	}
	
	public void mouseClicked(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mousePressed(MouseEvent e){
		coordinates[0][0]=e.getX();
		coordinates[1][0]=e.getY();
	}
	public void mouseReleased(MouseEvent e){
		coordinates[0][1]=e.getX();
		coordinates[1][1]=e.getY();
		g.setColor(Color.red);
		DDA(coordinates[0][0],coordinates[1][0],coordinates[0][1],coordinates[1][1]);
		rotate(30);
	}
	public void mouseDragged(MouseEvent e){}
	
	public void rotate(int angle){
		double teta=Math.toRadians(angle);
		g.setColor(Color.blue);
		double dx=coordinates[0][0];
		double dy=coordinates[1][0];
		coordinates=translatePoints(coordinates,-dx,-dy);
		coordinates=multipleMatrix(rotateMatrix(teta),coordinates);
		coordinates=translatePoints(coordinates,dx,dy);
		DDA(coordinates[0][0],coordinates[1][0],coordinates[0][1],coordinates[1][1]);

	}
	public double [][]rotateMatrix(double teta){
		double r[][]=new double[2][2];
		r[0][0]=Math.cos(teta);
		r[0][1]=-1*Math.sin(teta);
		r[1][0]=Math.sin(teta);
		r[1][1]=Math.cos(teta);
		return r;
	}
	public double[][] multipleMatrix(double x[][],double y[][]){
				double ans[][]=new double[x.length][y[0].length];
				for(int i=0;i<x.length;i++){
					for(int j=0;j<y[0].length;j++){
						ans[i][j]=0;
						for(int k=0;k<x[0].length;k++){
							ans[i][j]+=x[i][k]*y[k][j];
						}
					}
				}
				return ans;			
	}
}