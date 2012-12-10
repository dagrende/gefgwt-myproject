package mypackage.client;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.ReflectionHelper;
import org.eclipse.gef.examples.shapes.PaletteHacks;
import org.eclipse.gef.examples.shapes.model.EllipticalShape;
import org.eclipse.gef.examples.shapes.model.RectangularShape;
import org.eclipse.gef.examples.shapes.model.ShapesDiagram;
import org.eclipse.gef.examples.shapes.parts.ShapesEditPartFactory;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gef.ui.palette.PaletteViewerProvider;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class MyProject implements EntryPoint {
	EditDomain editDomain = new EditDomain();

	@Override
	public void onModuleLoad() {
		createInstantiators();
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		
		// create tool palette to the left of the shapes editor and connect to it
		Composite paletteControl = new Composite(null, SWT.NONE);
		PaletteRoot palette = MyPaletteFactory.createPalette();
		PaletteViewerProvider paletteViewerProvider = new PaletteViewerProvider(editDomain);
		PaletteViewer paletteViewer = paletteViewerProvider.createPaletteViewer(paletteControl);
		paletteViewer.setContents(palette);
		paletteControl.setSize(100, 300);
		Widget paletteParentGwtWidget = paletteControl.getGwtWidget();
		horizontalPanel.add(paletteParentGwtWidget);
		
		Composite editorControl = new Composite(null, SWT.NONE);
		ScrollingGraphicalViewer sgv = new ScrollingGraphicalViewer();
		sgv.createControl(editorControl);
		sgv.setEditPartFactory(new ShapesEditPartFactory());
		sgv.setEditDomain(editDomain);
		sgv.setContents(createContent());
		editorControl.setSize(700, 300);
		Widget gwtWidget = editorControl.getGwtWidget();
		horizontalPanel.add(gwtWidget);
		
		RootLayoutPanel.get().add(horizontalPanel);
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

	private void createInstantiators() {
		ReflectionHelper.registerHelper(org.eclipse.gef.tools.CreationTool.class, new ReflectionHelper.Instantiator() {
			@Override
			public Object newInstance(Class c) {
				return new org.eclipse.gef.tools.CreationTool();
			}
		});
		ReflectionHelper.registerHelper(org.eclipse.gef.examples.shapes.model.EllipticalShape.class, new ReflectionHelper.Instantiator() {
			@Override
			public Object newInstance(Class c) {
				return new org.eclipse.gef.examples.shapes.model.EllipticalShape();
			}
		});
		ReflectionHelper.registerHelper(org.eclipse.gef.examples.shapes.model.RectangularShape.class, new ReflectionHelper.Instantiator() {
			@Override
			public Object newInstance(Class c) {
				return new org.eclipse.gef.examples.shapes.model.RectangularShape();
			}
		});
	}

}