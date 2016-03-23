package battleShipPorject;


import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;

public class MouseDragger extends MouseAdapter{
	private Point lastLocation;
	private Component draggedComponent;
	
	public void mousePressed(MouseEvent e){
		draggedComponent = e.getComponent();
		lastLocation = SwingUtilities.convertPoint(draggedComponent, e.getPoint(), draggedComponent.getParent());
		System.out.println(lastLocation.x + " " + lastLocation.y + '\n');
	}//End mousePressed
	
	public void mouseDragged(MouseEvent e){
		 Point location = SwingUtilities.convertPoint(draggedComponent, e.getPoint(), draggedComponent.getParent());
       if (draggedComponent.getParent().getBounds().contains(location)){
           Point newLocation = draggedComponent.getLocation();
           newLocation.translate(location.x - lastLocation.x, location.y - lastLocation.y);
           newLocation.x = Math.max(newLocation.x, 0);
           newLocation.x = Math.min(newLocation.x, draggedComponent.getParent().getWidth() - draggedComponent.getWidth());
           newLocation.y = Math.max(newLocation.y, 0);
           newLocation.y = Math.min(newLocation.y, draggedComponent.getParent().getHeight() - draggedComponent.getHeight());
           draggedComponent.setLocation(newLocation);
           lastLocation = location;
       }
	}//End mouseDragged
	
	public void mouseReleased(MouseEvent e){
		Point p = new Point(draggedComponent.getX(), draggedComponent.getY());
		if(p.x < 410 && p.x >= 0){
			if(p.y < 410 && p.x >= 0){
				draggedComponent.setLocation((int)((p.x/40)*41), (int)((p.y/40)*41));
			}
		}else{
			draggedComponent.setLocation(0,0);
		}
      lastLocation = null;
	    draggedComponent = null;
  }//End mouseReleased

  public void makeDraggable(Component component){
      component.addMouseListener(this);
      component.addMouseMotionListener(this);
  }//End makeDraggable


}//End MouseDragger
