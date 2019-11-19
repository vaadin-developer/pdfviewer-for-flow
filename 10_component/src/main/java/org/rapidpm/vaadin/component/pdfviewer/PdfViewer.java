package org.rapidpm.vaadin.component.pdfviewer;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.server.StreamRegistration;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.StreamResourceRegistry;
import com.vaadin.flow.server.VaadinSession;

import java.net.URI;

@Tag("pdf-browser-viewer")
@JsModule("@lrnwebcomponents/pdf-browser-viewer/pdf-browser-viewer.js")
@NpmPackage(value = "@lrnwebcomponents/pdf-browser-viewer", version = "2.2.0")
public class PdfViewer
    extends Component {

  public static final String NOT_SUPPORTED_MESSAGE = "not-supported-message";
  public static final String NOT_SUPPORTED_LINK_MESSAGE = "not-supported-link-message";
  public static final String CARD = "card";
  public static final String DOWNLOAD_LABEL = "downloadLabel";
  public static final String ELEVATION = "elevation";
  public static final String HEIGHT = "height";
  public static final String WIDTH = "width";
  public static final String FILE = "file";
  private StreamRegistration streamRegistration;

  public PdfViewer() { }

  public void setStreamResource(StreamResource streamResource) {
    unregister();
    streamRegistration = VaadinSession.getCurrent()
                                      .getResourceRegistry()
                                      .registerResource(streamResource);
    URI uri = StreamResourceRegistry.getURI(streamResource);
    final String file = uri.toASCIIString();
    setFile(file);
  }

  public void setFile(String file) {
    getElement().setAttribute(FILE, file);
  }

  public void setNotSupportedMessage(String message) {
    getElement().setAttribute(NOT_SUPPORTED_MESSAGE, message);
  }

  public void setNotSupportedLinkMessage(String message) {
    getElement().setAttribute(NOT_SUPPORTED_LINK_MESSAGE, message);
  }

  public void setCard(boolean card) {
    getElement().setAttribute(CARD, card);
  }

  public void setDownloadLabel(String label) {
    getElement().setAttribute(DOWNLOAD_LABEL, label);
  }

  public void setElevation(String elevation) {
    getElement().setAttribute(ELEVATION, elevation);
  }

  public void setHeight(String height) {
    getElement().setAttribute(HEIGHT, height);
    getElement().getStyle()
                .set(HEIGHT, height);
  }

  public void setWidth(String width) {
    getElement().setAttribute(WIDTH, width);
    getElement().getStyle()
                .set(WIDTH, width);
  }

  @Override
  protected void onDetach(DetachEvent detachEvent) {
    super.onDetach(detachEvent);
    unregister();
  }

  private void unregister() {
    if (streamRegistration != null) {
      streamRegistration.unregister();
      streamRegistration = null;
    }
  }
}