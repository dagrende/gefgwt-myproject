package mypackage.images;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface MyImages extends ClientBundle {
	public static final MyImages INSTANCE =  GWT.create(MyImages.class);
	
	@Source("ellipse16.gif")
	ImageResource ellipse16();
	
	@Source("ellipse24.gif")
	ImageResource ellipse24();

	@Source("rectangle16.gif")
	ImageResource rectangle16();
	
	@Source("rectangle24.gif")
	ImageResource rectangle24();
}
