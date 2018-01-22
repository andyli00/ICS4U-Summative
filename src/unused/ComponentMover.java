/*
 * Thanks to https://www.programcreek.com/java-api-examples/index.php?source_dir=HTML-Encrypter-master/ComponentMover.java
 * for providing reference code!
 */

package unused;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

/**
 * This class allows you to move a Component by using a mouse. The Component
 * moved can be a high level Window (ie. Window, Frame, Dialog) in which case
 * the Window is moved within the desktop. Or the Component can belong to a
 * Container in which case the Component is moved within the Container.
 *
 * When moving a Window, the listener can be added to a child Component of the
 * Window. In this case attempting to move the child will result in the Window
 * moving. For example, you might create a custom "Title Bar" for an undecorated
 * Window and moving of the Window is accomplished by moving the title bar only.
 * Multiple components can be registered as "window movers".
 *
 * Components can be registered when the class is created. Additional components
 * can be added at any time using the registerComponent() method.
 *
 * @author Andy Li
 * @since Nov 13, 2017
 */
public class ComponentMover extends MouseAdapter {
	
	private Class destinationClass;
	private Component destinationComponent;
	private Component destination;
	private Component source;
	
	private Point pressed;
	private Point location;
	
	private Cursor originalCursor;
	private boolean autoscrolls;
	private boolean potentialDrag;
	
	private boolean changeCursor = true;
	private boolean autoLayout = false;
	
	private Insets dragInsets = new Insets(0, 0, 0, 0);
	private Insets edgeInsets = new Insets(0, 0, 0, 0);
	private Dimension snapSize = new Dimension(1, 1);
	
	/**
	 * Constructor for moving individual components. The components
	 * must be registered with the registerComponent() method.
	 */
	public ComponentMover() {
	}
	
	/**
	 * Constructor that specifies a class of Component that will be moved when drag
	 * events are generated on a registered child component. The events will be passed
	 * to the first ancestor of the specified class
	 *
	 * @param destinationClass	the Class of the ancestor component
	 * @param components		one or more components to be registered for the
	 *                          forwarding of drag events to the ancestor
	 */
	public ComponentMover(Class destinationClass, Component... components) {
		this.destinationClass = destinationClass;
		registerComponent(components);
	}
	
	/**
	 * Constructor to specify a parent component that will be moved when
	 * drag events are generated on a registered child component
	 * @param destinationComponent 	the parent component to forward drag events to
	 * @param components			the components to be registered for forwarding drag
	 *                              events to a parent component
	 */
	public ComponentMover(Component destinationComponent, Component... components) {
		this.destinationComponent = destinationComponent;
		registerComponent(components);
	}
	
	/**
	 *
	 * @return the changeCursor value
	 */
	public boolean isChangeCursor() {
		return changeCursor;
	}
	
	/**
	 * Set changeCursor value
	 * @param changeCursor the new changeCursor value
	 */
	public void setChangeCursor(boolean changeCursor) {
		this.changeCursor = changeCursor;
	}
	
	/**
	 *  @return  the auto layout property
	 */
	public boolean isAutoLayout() {
		return autoLayout;
	}
	
	/**
	 *  Set the auto layout
	 *
	 *  @param  autoLayout when true, layout will be invoked on the parent container
	 */
	public void setAutoLayout(boolean autoLayout) {
		this.autoLayout = autoLayout;
	}
	
	public Insets getDragInsets() {
		return dragInsets;
	}
	
	/**
	 * Sets the drag insets. This specifies an area where mouseDragged events
	 * should be ignored (i.e. areas that won't move the window when dragged)
	 *
	 * @param dragInsets new dragInsets value
	 */
	public void setDragInsets(Insets dragInsets) {
		this.dragInsets = dragInsets;
	}
	
	public Insets getEdgeInsets() {
		return edgeInsets;
	}
	
	/**
	 * Sets the edge insets. these specify how close to each edge of the
	 * parent component that the child component can be moved
	 * Positive values = child must be inside the parent
	 * Negative values = child can be moved outside the parent
	 */
	public void setEdgeInsets(Insets edgeInsets) {
		this.edgeInsets = edgeInsets;
	}
	
	/**
	 *  @return the snap size
	 */
	public Dimension getSnapSize() {
		return snapSize;
	}
	
	/**
	 *  Set the snap size. Forces the component to be snapped to
	 *  the closest grid position. Snapping will occur when the mouse is
	 *  dragged half way.
	 *
	 *  @throws IllegalArgumentException if the width or height are below 1
	 */
	public void setSnapSize(Dimension snapSize) {
		if (snapSize.width < 1 ||  snapSize.height < 1)
			throw new IllegalArgumentException("Snap sizes must be greater than 0");
		
		this.snapSize = snapSize;
	}
	
	/**
	 * Adds mouse listeners to the specified components
	 * @param components the component(s) that the listeners are added to
	 */
	private void registerComponent(Component... components) {
		for (Component c: components) {
			c.addMouseListener(this);
		}
	}
	
	/**
	 * Removes mouse listeners from the specified components
	 * @param components the component(s) that the listeners are removed from
	 */
	private void deRegisterComponenet(Component... components) {
		for (Component c: components) {
			c.removeMouseListener(this);
		}
	}
	
	/**
	 * Sets up variables used in moving the element
	 * Creates a rectangle that represents the area that the component
	 * can be moved with (e.g. a navigation bar) and checks if the
	 * MouseEvent originated from that area. if it does, calls setupForDragging()
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		source = e.getComponent();
		int width = source.getSize().width - dragInsets.left - dragInsets.right;
		int height = source.getSize().height - dragInsets.top - dragInsets.bottom;
		
		Rectangle r = new Rectangle(dragInsets.left, dragInsets.top, width, height);
		
		if (r.contains(e.getPoint()))
			setupForDragging(e);
	}
	
	public void setupForDragging(MouseEvent e) {
		source.addMouseMotionListener(this);
		potentialDrag= true;
		
		//determines the component that will be moved
		if (destinationComponent != null)
			destination = destinationComponent;
		else if (destinationClass == null)
			destination = source;
		else //forwards events to destination component
			destination = SwingUtilities.getAncestorOfClass(destinationClass, source);
		
		pressed = e.getLocationOnScreen();
		location = destination.getLocation();
		
		if (changeCursor) {
			originalCursor = source.getCursor();
			source.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
		}
		
		//makes sure autoscrolls is false to make dragging smoother
		if (destination instanceof JComponent) {
			JComponent jc = (JComponent) destination;
			autoscrolls = jc.getAutoscrolls();
			jc.setAutoscrolls(false);
		}
	}
	
	/**
	 * Actually moves the component to its new location.
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		Point dragged = e.getLocationOnScreen();
		int dragX = getDragDistance(dragged.x, pressed.x, snapSize.width);
		int dragY = getDragDistance(dragged.y, pressed.y, snapSize.height);
		
		int locationX = location.x + dragX;
		int locationY = location.y + dragY;
		
		//  Mouse dragged events are not generated for every pixel the mouse
		//  is moved. Adjust the location to make sure we are still on a
		//  snap value.
		
		while (locationX < edgeInsets.left)
			locationX += snapSize.width;
		
		while (locationY < edgeInsets.top)
			locationY += snapSize.height;
		
		Dimension d = getBoundingSize( destination );
		
		while (locationX + destination.getSize().width + edgeInsets.right > d.width)
			locationX -= snapSize.width;
		
		while (locationY + destination.getSize().height + edgeInsets.bottom > d.height)
			locationY -= snapSize.height;
		
		//  Adjustments are finished, move the component
		
		destination.setLocation(locationX, locationY);
	}
	
	/**
	 *  Get the bounds of the parent of the dragged component.
	 */
	private Dimension getBoundingSize(Component source)
	{
		if (source instanceof Window)
		{
			GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
			Rectangle bounds = env.getMaximumWindowBounds();
			return new Dimension(bounds.width, bounds.height);
		}
		else
		{
			return source.getParent().getSize();
		}
	}
	
	/**
	 * Calculates how far the mouse has moved from where it started
	 * @return the result of that calculation
	 */
	private int getDragDistance(int larger, int smaller, int snapSize) {
		int halfway = snapSize/2;
		int drag = larger - smaller;
		drag += (drag < 0) ? -halfway : halfway;
		drag = (drag / snapSize) * snapSize;
		
		return drag;
	}
	
	/**
	 * Restore the component to its original state
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		if (!potentialDrag)
			return;
		
		source.removeMouseMotionListener(this);
		potentialDrag = false;
		
		if (changeCursor)
			source.setCursor(originalCursor);
		
		if (destination instanceof JComponent)
			((JComponent) destination).setAutoscrolls(autoscrolls);
		
		//lays out components on parent container
		if (autoLayout) {
			if (destination instanceof JComponent)
				((JComponent) destination).revalidate();
			else
				destination.validate();
		}
	}
}
