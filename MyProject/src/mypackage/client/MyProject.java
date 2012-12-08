package mypackage.client;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.examples.shapes.model.EllipticalShape;
import org.eclipse.gef.examples.shapes.model.RectangularShape;
import org.eclipse.gef.examples.shapes.model.ShapesDiagram;
import org.eclipse.gef.examples.shapes.parts.ShapesEditPartFactory;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class MyProject implements EntryPoint {
	@Override
	public void onModuleLoad() {
		ScrollingGraphicalViewer sgv = new ScrollingGraphicalViewer();
		
		Composite composite = new Composite(null, SWT.NONE);
		
		sgv.createControl(composite);
		sgv.setEditPartFactory(new ShapesEditPartFactory());
		sgv.setEditDomain(new EditDomain());
		sgv.setContents(createContent());
		Widget gwtWidget = composite.getGwtWidget();
		RootLayoutPanel.get().add(gwtWidget);
	}

	private Object createContent() {
		ShapesDiagram diagram = new ShapesDiagram();
		RectangularShape rs = new RectangularShape();
		rs.setSize(new Dimension(75, 75));
		rs.setLocation(new Point(10, 10));
		EllipticalShape es = new EllipticalShape();
		es.setSize(new Dimension(140, 70));
		es.setLocation(new Point(100, 100));
		diagram.addChild(rs);
		diagram.addChild(es);
		return diagram;
	}
}