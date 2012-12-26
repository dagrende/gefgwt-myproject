package mypackage.client;

import mypackage.images.MyImages;

import org.eclipse.gef.ReflectionHelper;
import org.eclipse.gef.examples.shapes.model.Connection;
import org.eclipse.gef.examples.shapes.model.EllipticalShape;
import org.eclipse.gef.examples.shapes.model.RectangularShape;
import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteToolbar;
import org.eclipse.gef.palette.PanningSelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.requests.SimpleFactory;
import org.eclipse.gef.tools.ConnectionCreationTool;
import org.eclipse.gef.tools.MarqueeSelectionTool;
import org.eclipse.gef.tools.PanningSelectionTool;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

/**
 * Utility class that can create a GEF Palette.
 * 
 * @see #createPalette()
 * @author Elias Volanakis
 */
final class MyPaletteFactory {

	/** Preference ID used to persist the palette location. */
	private static final String PALETTE_DOCK_LOCATION = "ShapesEditorPaletteFactory.Location";
	/** Preference ID used to persist the palette size. */
	private static final String PALETTE_SIZE = "ShapesEditorPaletteFactory.Size";
	/** Preference ID used to persist the flyout palette's state. */
	private static final String PALETTE_STATE = "ShapesEditorPaletteFactory.State";

	/** Create the "Shapes" drawer. */
	private static PaletteContainer createShapesDrawer() {
		PaletteDrawer componentsDrawer = new PaletteDrawer("Shapes");

		CombinedTemplateCreationEntry component = new CombinedTemplateCreationEntry(
				"Ellipse", "Create an elliptical shape", EllipticalShape.class,
				new SimpleFactory(EllipticalShape.class),
				ImageDescriptor.createFromImage(new Image(null, MyImages.INSTANCE.ellipse16())), 
				ImageDescriptor.createFromImage(new Image(null, MyImages.INSTANCE.ellipse24())));
		componentsDrawer.add(component);

		component = new CombinedTemplateCreationEntry("Rectangle",
				"Create a rectangular shape", RectangularShape.class,
				new SimpleFactory(RectangularShape.class),
				ImageDescriptor.createFromImage(new Image(null, MyImages.INSTANCE.rectangle16())), 
				ImageDescriptor.createFromImage(new Image(null, MyImages.INSTANCE.rectangle24())));
		componentsDrawer.add(component);

		return componentsDrawer;
	}

	/**
	 * Creates the PaletteRoot and adds all palette elements. Use this factory
	 * method to create a new palette for your graphical editor.
	 * 
	 * @return a new PaletteRoot
	 */
	static PaletteRoot createPalette() {
		createInstantiators();

		PaletteRoot palette = new PaletteRoot();
		palette.add(createToolsGroup(palette));
		palette.add(createShapesDrawer());
		return palette;
	}

	/** Create the "Tools" group. */
	private static PaletteContainer createToolsGroup(PaletteRoot palette) {

		PaletteToolbar toolbar = new PaletteToolbar("Tools");

		// Add a selection tool to the group
		ToolEntry tool = new PanningSelectionToolEntry();
		tool.setSmallIcon(ImageDescriptor.createFromImage(new Image(null, MyImages.INSTANCE.arrow16())));
		tool.setLargeIcon(ImageDescriptor.createFromImage(new Image(null, MyImages.INSTANCE.arrow24())));
		toolbar.add(tool);
		palette.setDefaultEntry(tool);

		// Add a marquee tool to the group
		tool = new MarqueeToolEntry();
		tool.setSmallIcon(ImageDescriptor.createFromImage(new Image(null, MyImages.INSTANCE.marquee_nodes16())));
		tool.setLargeIcon(ImageDescriptor.createFromImage(new Image(null, MyImages.INSTANCE.marquee_nodes24())));
		toolbar.add(tool);

		// Add (solid-line) connection tool
		tool = new ConnectionCreationToolEntry("Solid connection",
				"Create a solid-line connection", new CreationFactory() {
					public Object getNewObject() {
						return null;
					}

					// see ShapeEditPart#createEditPolicies()
					// this is abused to transmit the desired line style
					public Object getObjectType() {
						return Connection.SOLID_CONNECTION;
					}
				}, ImageDescriptor.createFromImage(new Image(null, MyImages.INSTANCE.connection_s16())),
				ImageDescriptor.createFromImage(new Image(null, MyImages.INSTANCE.connection_s24())));
		toolbar.add(tool);

		// Add (dashed-line) connection tool
		tool = new ConnectionCreationToolEntry("Dashed connection",
				"Create a dashed-line connection", new CreationFactory() {
					public Object getNewObject() {
						return null;
					}

					// see ShapeEditPart#createEditPolicies()
					// this is abused to transmit the desired line style
					public Object getObjectType() {
						return Connection.DASHED_CONNECTION;
					}
		}, ImageDescriptor.createFromImage(new Image(null, MyImages.INSTANCE.connection_d16())),
		ImageDescriptor.createFromImage(new Image(null, MyImages.INSTANCE.connection_d24())));
		toolbar.add(tool);

		return toolbar;
	}

	/** Utility class. */
	private MyPaletteFactory() {
		// Utility class
	}

	private static void createInstantiators() {
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
		ReflectionHelper.registerHelper(ConnectionCreationTool.class, new ReflectionHelper.Instantiator() {
			@Override
			public Object newInstance(Class c) {
				return new ConnectionCreationTool();
			}
		});
		ReflectionHelper.registerHelper(MarqueeSelectionTool.class, new ReflectionHelper.Instantiator() {
			@Override
			public Object newInstance(Class c) {
				return new MarqueeSelectionTool();
			}
		});
		ReflectionHelper.registerHelper(PanningSelectionTool.class, new ReflectionHelper.Instantiator() {
			@Override
			public Object newInstance(Class c) {
				return new PanningSelectionTool();
			}
		});
	}

}