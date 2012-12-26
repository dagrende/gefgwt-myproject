package mypackage.images;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface MyImages extends ClientBundle {
	public static final MyImages INSTANCE =  GWT.create(MyImages.class);
	
	@Source("arrow16.gif")
	ImageResource arrow16();

	@Source("arrow24.gif")
	ImageResource arrow24();

	@Source("marquee_nodes16.gif")
	ImageResource marquee_nodes16();

	@Source("marquee_nodes24.gif")
	ImageResource marquee_nodes24();

	@Source("connection_d16.gif")
	ImageResource connection_d16();

	@Source("connection_d24.gif")
	ImageResource connection_d24();

	@Source("connection_s16.gif")
	ImageResource connection_s16();

	@Source("connection_s24.gif")
	ImageResource connection_s24();

	@Source("ellipse16.gif")
	ImageResource ellipse16();
	
	@Source("ellipse24.gif")
	ImageResource ellipse24();

	@Source("rectangle16.gif")
	ImageResource rectangle16();
	
	@Source("rectangle24.gif")
	ImageResource rectangle24();
}
